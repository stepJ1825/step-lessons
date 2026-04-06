package by.step.methodtrace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class MethodTraceLogger {

    public void log(String className, String methodName,
            long executionTimeNanos, MethodTraceProperties properties,
            String customMessage, Object[] args, Object result) {

        Logger log = LoggerFactory.getLogger(className);

        // ИСПРАВЛЕНО: убрана двойная проверка
        long thresholdInNanos = properties.getThresholdMs() * 1_000_000;
        if (executionTimeNanos < thresholdInNanos) {
            return; // Пропускаем быстрые методы
        }

        long convertedTime = convertTime(executionTimeNanos, properties.getTimeUnit());

        StringBuilder message = new StringBuilder();

        // Кастомное сообщение или стандартное
        if (customMessage != null && !customMessage.isEmpty()) {
            message.append(customMessage).append(" - ");
        }

        message.append(String.format("Method %s.%s executed in %d %s",
                className, methodName, convertedTime, properties.getTimeUnit().name().toLowerCase()));

        // Добавляем параметры
        if (properties.isLogParameters() && args != null && args.length > 0) {
            message.append(" | Parameters: ").append(formatArguments(args, properties));
        }

        // Добавляем результат
        if (properties.isLogResult() && result != null) {
            message.append(" | Result: ").append(formatResult(result, properties));
        }

        // Логируем с нужным уровнем
        logAtLevel(log, properties.getLogLevel(), message.toString());
    }

    private long convertTime(long nanos, TimeUnit targetUnit) {
        return switch (targetUnit) {
            case NANOSECONDS -> nanos;
            case MICROSECONDS -> nanos / 1000;
            case MILLISECONDS -> nanos / 1_000_000;
            case SECONDS -> nanos / 1_000_000_000;
            default -> nanos / 1_000_000;
        };
    }

    private String formatArguments(Object[] args, MethodTraceProperties properties) {
        if (args == null || args.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) sb.append(", ");
            String argStr = String.valueOf(args[i]);
            if (argStr.length() > properties.getMaxStringLength()) {
                argStr = argStr.substring(0, properties.getMaxStringLength()) + "...";
            }
            sb.append(argStr);
        }
        sb.append("]");
        return sb.toString();
    }

    private String formatResult(Object result, MethodTraceProperties properties) {
        if (result == null) {
            return "null";
        }
        String resultStr = String.valueOf(result);
        if (resultStr.length() > properties.getMaxStringLength()) {
            resultStr = resultStr.substring(0, properties.getMaxStringLength()) + "...";
        }
        return resultStr;
    }

    private void logAtLevel(Logger log, MethodTraceProperties.LogLevel level, String message) {
        switch (level) {
            case TRACE -> log.trace(message);
            case DEBUG -> log.debug(message);
            case INFO -> log.info(message);
            case WARN -> log.warn(message);
            case ERROR -> log.error(message);
        }
    }
}