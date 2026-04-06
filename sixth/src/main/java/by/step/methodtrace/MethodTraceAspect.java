package by.step.methodtrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
@ConditionalOnProperty(name = "method.trace.enabled", havingValue = "true", matchIfMissing = true)
public class MethodTraceAspect {

    private static final Logger log = LoggerFactory.getLogger(MethodTraceAspect.class);

    @Autowired
    private MethodTraceProperties properties;

    @Autowired
    private MethodTraceLogger logger;

    @Around("@annotation(by.step.methodtrace.TraceMethod)")
    public Object traceAnnotatedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        TraceMethod traceAnnotation = method.getAnnotation(TraceMethod.class);

        return trace(joinPoint, traceAnnotation.value(),
                traceAnnotation.thresholdMs(),
                traceAnnotation.hideParameters(),
                traceAnnotation.hideResult());
    }

    @Around("within(@org.springframework.stereotype.Service *) || " +
            "within(@org.springframework.stereotype.Repository *) || " +
            "within(@org.springframework.web.bind.annotation.RestController *)")
    public Object traceSpringBeans(ProceedingJoinPoint joinPoint) throws Throwable {
        // Если требуется аннотация, но её нет - пропускаем
        if (properties.isAnnotationRequired()) {
            return joinPoint.proceed();
        }

        return trace(joinPoint, null, -1, false, false);
    }

    private Object trace(ProceedingJoinPoint joinPoint, String customMessage,
            long customThreshold, boolean hideParams, boolean hideResult) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.nanoTime();

        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable throwable) {
            long executionTime = System.nanoTime() - startTime;
            logger.log(className, methodName + " (Exception)", executionTime,
                    properties, customMessage,
                    hideParams ? null : joinPoint.getArgs(),
                    throwable.getMessage());
            throw throwable;
        } finally {
            long executionTime = System.nanoTime() - startTime;

            // Проверяем кастомный порог для метода
            long threshold = customThreshold > 0 ? customThreshold : properties.getThresholdMs();
            long thresholdNanos = threshold * 1_000_000;

            if (executionTime >= thresholdNanos) {
                logger.log(className, methodName, executionTime, properties,
                        customMessage,
                        hideParams ? null : joinPoint.getArgs(),
                        hideResult ? "hidden" : result);
            }
        }
    }
}