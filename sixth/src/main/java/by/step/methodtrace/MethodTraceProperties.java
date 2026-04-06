package by.step.methodtrace;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "method.trace")
public class MethodTraceProperties {

    /**
     * Включить трейсинг методов
     */
    private boolean enabled = true;

    /**
     * Минимальное время выполнения в миллисекундах для логирования
     * (методы быстрее этого порога не логируются)
     */
    private long thresholdMs = 0;

    /**
     * Единица измерения времени в логах
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /**
     * Логировать только методы, у которых есть аннотация @TraceMethod
     */
    private boolean annotationRequired = true;

    /**
     * Уровень логирования
     */
    private LogLevel logLevel = LogLevel.DEBUG;

    /**
     * Логировать параметры метода
     */
    private boolean logParameters = false;

    /**
     * Логировать результат выполнения
     */
    private boolean logResult = false;

    /**
     * Максимальная длина строки для параметров/результата
     */
    private int maxStringLength = 100;

    public enum LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR
    }

    // Геттеры и сеттеры
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public long getThresholdMs() { return thresholdMs; }
    public void setThresholdMs(long thresholdMs) { this.thresholdMs = thresholdMs; }

    public TimeUnit getTimeUnit() { return timeUnit; }
    public void setTimeUnit(TimeUnit timeUnit) { this.timeUnit = timeUnit; }

    public boolean isAnnotationRequired() { return annotationRequired; }
    public void setAnnotationRequired(boolean annotationRequired) { this.annotationRequired = annotationRequired; }

    public LogLevel getLogLevel() { return logLevel; }
    public void setLogLevel(LogLevel logLevel) { this.logLevel = logLevel; }

    public boolean isLogParameters() { return logParameters; }
    public void setLogParameters(boolean logParameters) { this.logParameters = logParameters; }

    public boolean isLogResult() { return logResult; }
    public void setLogResult(boolean logResult) { this.logResult = logResult; }

    public int getMaxStringLength() { return maxStringLength; }
    public void setMaxStringLength(int maxStringLength) { this.maxStringLength = maxStringLength; }
}