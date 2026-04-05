SPEL
https://docs.spring.io/spring-framework/docs/3.0.x/reference/expressions.html
https://www.baeldung.com/spring-expression-language

БОООЛЬШОЙ плейлист по Spring
https://www.youtube.com/watch?v=BmBr5diz8WA&list=PLQ8VAv2lQOsR4S0n96k8gtalxHpxJTCsm

### 14.01.2026

1. IoC container, BeanDefinitions
2. Bean Scopes
3. Bean lifecycle
4. Init/Destroy callbacks
5. BeanFactoryPostProcessor
6. application.properties
7. property-placeholder
8. ${} - EL,  #{} - SPEL


PropertySourcesPlaceholderConfigurer -> PlaceholderConfigurerSupport -> PropertyResourceConfigurer 
-> BeanFactoryPostProcessor.postProcessBeanFactory

ConfigurableListableBeanFactory -> ListableBeanFactory -> BeanFactory

PropertySourcesPlaceholderConfigurer.postProcessBeanFactory - настройка бинов после их создания

PlaceholderConfigurerSupport.doProcessProperties
{...
visitor.visitBeanDefinition(bd); //подстановка значений
...}

### 19.01.2026

BeanFactoryPostProcessor.postProcessBeanFactory

CustomBeanFactoryPostProcessor

### 21.01.2026

Annotated based configuration.
<context:annotation-config/> для внедрения соответствующих BPP

Для чего использовать BPP.postProcessBeforeInitialization:
- Подготовка бина к инициализации.
- Валидация состояния перед инициализацией.
- Установка дополнительных свойств, которые должны быть доступны внутри метода инициализации. 
- Spring использует этот метод для обработки @PostConstruct и @PreDestroy через InitDestroyAnnotationBeanPostProcessor

Для чего использовать BPP.postProcessAfterInitialization
- Оборачивание бина в прокси (например, для AOP, транзакций, кэширования).
- Регистрация бина в каком-то внешнем реестре.
- Логирование готового к работе объекта.
- Пример: AnnotationAwareAspectJAutoProxyCreator (часть AOP) создаёт прокси именно здесь.

Custom BPP
@Transactional
@Auditing

@Autowired - аннотация Spring, полагается на тип бина, 
        либо для нескольких кандидатов нужно полагаться на имя поля (=id бина в контексте)
@Resource - аннотация Java EE, полагается на имя бина

### 26.01.2026
Spring + default constructors FAILS:
- Чтобы вызвать такую ошибку с XML конфигурацией, необходимо создать бин через XML, а в классе допустить 
ошибку и не указать соответствующий конструктор.

SPeL in @Value:
- Для поля data в классе BookRepositoryJSON SPeL в @Value работает.
- Для поля df использовать @Value нельзя, так как из properties данные приходят в виде текста \
и преобразовать String в SimpleDateFormat напрямую нельзя.

### 28.01.2026
Scope prototype - способы установки:
- Через аннотацию @Scope (на классе): @Scope("prototype") // или @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
- Через @Bean в Java Configuration: '@Bean @Scope("prototype")' или '@Bean @Scope(BeanDefinition.SCOPE_SINGLETON)'
- В XML-конфигурации: <bean id="myPrototypeBean" class="com.example.MyPrototypeBean" scope="prototype"/>


@Configuration
@PropertySource("classpath:application.properties")
@Import(WebConfig.class)
@ComponentScan(...)
@ImportResource("classpath:application.xml")

@Bean

by.step2.config.WebConfig.java

#### Отличия работы профилей и загрузки свойств: Spring Boot vs. Обычный Spring
| Функциональность                                               | Spring Boot                                       | Обычный Spring (Spring Framework)                                                   |
|----------------------------------------------------------------|---------------------------------------------------|-------------------------------------------------------------------------------------|
| **Автоматическая загрузка `application.properties`**           | ✅ Всегда загружается                              | ❌ Только если указан явно                                                           |
| **Автоматическая загрузка `application-{profile}.properties`** | ✅ Да, при активации профиля                       | ❌ Нет                                                                               |
| **Переопределение свойств из профильного файла**               | ✅ Автоматически (профильный файл имеет приоритет) | ❌ Только при ручной настройке нескольких `PropertySource`                           |
| **Активация профиля через `-Dspring.profiles.active`**         | ✅ Поддерживается                                  | ✅ Поддерживается                                                                    |
| **Влияние профиля на загрузку свойств**                        | ✅ Прямое: триггер для загрузки файлов             | ❌ Косвенное: профиль влияет только на бины, не на свойства                          |
| **Простота настройки "база + профиль"**                        | ✅ Достаточно создать файлы — всё работает         | ❌ Требуется ручная конфигурация через `@Profile` + `@PropertySource` или XML        |
| **Поддержка в XML через `<beans profile="...">`**              | ✅ (если используется XML)                         | ✅ Да                                                                                |
| **Поддержка `@PropertySource` с `${spring.profiles.active}`**  | ✅ Стабильно работает                              | ⚠️ Работает **только если профиль задан через системное свойство**, а не программно |

### 09.02.2026

1.
@RequestMapping
@GetMapping, @PostMapping ...
@PathVariable
@RequestBody
@RequestParam
request.getHeader("...");
2.
DispatcherServlet.doDispatch -> argumentResolvers
3.
@SessionAttributes({"..."}), @SessionAttribute("...")
4.
model.addAttribute(...)   vs    modelAndView.addObject(...)
5.
Forward Include Redirect

### 11.02.2026
 1. argumentResolvers: DispatcherServlet.handlerAdapters.RequestMappingHadnlerAdapter.argumentResolvers
 2. Сделаны формы для отправки POST/PUT запросов

### 23.02.2026
Финальный проект
https://docs.google.com/document/d/1dq_4BzR2ssX8uuX1LNpHAzPhNIolsvzzDEWNj_zsM9E/edit?usp=sharing
* Микросервисы
* Логирование

### 25.02.2026
+ XML JPA Config
+ @Id в @Entity без первичного ключа в БД - протестировать
+ GenerationType.IDENTITY и др

### 02.03.2026
+ разнести spring-jdbc и spring-jpa конфиги

### 04.03.2026
">= :minRating" почему ">=4.9" возвращает 0 строк
        В Java: Значение 4.9f на самом деле хранится как приблизительно 4.900000095367431640625.
        В Postgres:
        Если колонка имеет тип REAL или DOUBLE PRECISION: там тоже хранится приближенное значение (например, 4.9000000000000004).
        Если колонка имеет тип NUMERIC (или DECIMAL): там хранится точное значение 4.9.
        SELECT rating, rating::text FROM books WHERE rating >= 4.8 AND rating <= 5.0;
        Решения: перейти на BigDecimal или добавить погрешность

findAuthorsBySurname????? 
        CREATE_IF_NOT_FOUND - поведение по умолчанию

List<Book> searchByTitleKeyword(@Param("keyword") String keyword); - регистронезависимый поиск
        сделать через CONCAT
        сделать через ILIKE
        сделать через имя метода с ContainingIgnoreCase

### 18.03.2026
+ Service Discovery, EUREKA

### 25.03.2026
+ транзакции в Spring
+ TRANSACTIONAL за 7 минут
  https://www.youtube.com/watch?v=2E8FKi4oC0o

### 30.03.2026
+ Авторизация (sign in) и выход (sign out) в/из системы.

### 01.04.2026
+ mapstruct
+ логгирование


### FUTURE...
TODO - ControllerAdvice
TODO - Написать свой Spring Boot Starter
TODO - пагинация со стороны контроллера
TODO - SQL инъекции
TODO - CallableStatement демонстрация
TODO - destroy method с удалением таблица flyway_schema_history

### Почитать/посмотреть
https://habr.com/ru/articles/983344/
https://www.youtube.com/watch?v=4tSyz_v9w7Q

https://spring.io/guides/gs/spring-boot-docker
dockerfile https://habr.com/ru/companies/ruvds/articles/439980/
postgres in docker https://habr.com/ru/articles/578744/
https://spring.io/guides/gs/service-registration-and-discovery
совместимость версий https://spring.io/projects/spring-cloud

TESTCONTAINERS
https://www.youtube.com/watch?v=QJHrc1K3CcQ

https://medium.com/@reachout.rajesh/service-discovery-using-eureka-in-docker-containers-with-net-core-fdccfbd015fb

