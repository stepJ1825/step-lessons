//package by.step.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "by.step.repository")
//public class JpaConfig {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//
//        var em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setJpaVendorAdapter(jpaVendorAdapter);
//
//        // Если используете ТОЛЬКО XML-маппинги:
//        em.setPackagesToScan("by.step.model.jpa"); // для поиска классов
//        em.setMappingResources(
//                "by/step/resources/hibernate/BookJPA.hbm.xml",
//                "by/step/resources/hibernate/AuthorJPA.hbm.xml",
//                "by/step/resources/hibernate/GenreJPA.hbm.xml"
//        );
//
//        // Опционально: настройки Hibernate
//        var props = new Properties();
//        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        props.put("hibernate.hbm2ddl.auto", "validate"); // или "update" для dev
//        em.setJpaProperties(props);
//
//        return em;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        var adapter = new HibernateJpaVendorAdapter();
//        adapter.setShowSql(true);
//        return adapter;
//    }
//}