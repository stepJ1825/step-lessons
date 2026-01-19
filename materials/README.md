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

