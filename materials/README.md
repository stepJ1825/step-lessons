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

//TODO - Spring + default constructors FAILS
//TODO - SPeL in @Value
//TODO - задача на занятие 26.01 - удалить xml config



