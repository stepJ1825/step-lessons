//package by.step;
//
//import by.step.config.ApplicationConfiguration;
//import by.step.controller.MainController;
//import by.step.repository.genre.CommonService;
//import by.step.repository.genre.GenreRepository;
//import by.step.repository.AuthorRepository;
//import by.step.repository.SomeRepository;
//import by.step.repository.genre.GenreService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class Main {
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//
//        //до запуска контекста можно указать профили
//        context.register(ApplicationConfiguration.class);
////        context.getEnvironment().setActiveProfiles("web", "prod");
//        context.refresh();
//
//        MainController mainController = context.getBean(MainController.class);
//        Object bookRepository = context.getBean("bookRepository");
//        System.out.println(bookRepository);
//        SomeRepository someRepository = context.getBean(SomeRepository.class);
//        System.out.println(someRepository);
//        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
//        System.out.println(authorRepository);
//        GenreRepository genreRepositoryBean = context.getBean(GenreRepository.class);
//        System.out.println(genreRepositoryBean);
//
////        context.getBean("genreService3");
////        context.getBean("genreService4");
////        context.getBean("genreService5");
//
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//
//        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
//
//        CommonService commonService = context.getBean(CommonService.class);
//
//        mainController.start();
//    }
//}
