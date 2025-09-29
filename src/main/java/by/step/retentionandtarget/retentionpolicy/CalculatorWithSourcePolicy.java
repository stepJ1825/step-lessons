package by.step.retentionandtarget.retentionpolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@interface Debuggable {
    String value();
}

@Debuggable("temp value")
public class CalculatorWithSourcePolicy {
    // Аннотация @Debuggable будет видна только:
    // - Компилятору
    // - Инструментам анализа кода
    // - В IDE при просмотре исходного кода
}

/*
Использование:
    Для компилятора (@Override, @SuppressWarnings)
    Генерации кода (Lombok)
    Статического анализа
 */