package by.step.retentionandtarget.retentionpolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@interface InternalApi {
    String module();
}

@InternalApi(module = "payment")
public class PaymentProcessorWithClassPolicy {
    // Аннотация попадает в .class файл
    // Но НЕ доступна через Reflection во время выполнения
}

/*
RetentionPolicy.CLASS - значение по умолчанию
Использование:
    Обработка байт-кода
    Инструменты для модификации классов
    Анализ зависимостей
 */