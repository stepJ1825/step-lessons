Уровни логирования
От наиболее детального к наиболее важному
TRACE  // Самые детальные логи (вход/выход из методов)
DEBUG  // Отладочная информация (значения переменных)
INFO   // Основные события (запуск/остановка, операции)
WARN   // Предупреждения (проблемы, которые не критичны)
ERROR  // Ошибки (исключения, сбои)
FATAL  // Критические ошибки (приложение падает)

Настройка логирования в Spring Boot
Зависимости (уже есть в Spring Boot Starter)
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>

Расставляем логи
// Правильно
log.info("User {} added book {}", userId, bookId);
log.debug("Processing request: method={}, uri={}", method, uri);
log.error("Failed to process payment for order {}", orderId, exception);s
// Неправильно
log.info("User added book");  // Нет контекста
log.debug("Processing request");  // Непонятно какой
log.error(exception);  // Нет сообщения


Что логировать
	Входные параметры методов (на DEBUG)
	Результаты операций (на INFO)
	Исключения и ошибки (на ERROR с stacktrace)
	Время выполнения операций (на DEBUG/INFO)
	Изменение состояния системы (на INFO)
	
Чего НЕ логировать
	Пароли и секреты (токены, API keys)
	Персональные данные (email, телефон, паспорт)
	Огромные объекты (100+ полей)
	Бесконечные циклы (лог файл вырастет)
	

Документация SLF4J http://www.slf4j.org/
Документация Logback http://logback.qos.ch/documentation.html
Spring Boot Logging https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging

