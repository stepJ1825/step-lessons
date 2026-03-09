package by.step;

import by.step.common.Book;
import by.step.resttemplate.RestTemplateLibraryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableFeignClients // Включаем сканирование Feign клиентов
public class SecondStepApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecondStepApplication.class, args);

        //        MyService myService = context.getBean(MyService.class);
        //        myService.doSomethingElse();
        //
        //        NbrbCurrencyService nbrbCurrencyService = context.getBean(NbrbCurrencyService.class);
        //        List<CurrencyDto> allCurrenciesSync = nbrbCurrencyService.getAllCurrenciesSync();
        //        System.out.println(allCurrenciesSync);
        //        Flux<CurrencyDto> allCurrenciesReactive = nbrbCurrencyService.getAllCurrenciesReactive();
        //        allCurrenciesReactive.subscribe(System.out::println);

        //        RestTemplateCurrencyController bean = context.getBean(RestTemplateCurrencyController.class);
        //        ResponseEntity<List<CurrencyDto>> allCurrencies = bean.getAllCurrencies();
        //        System.out.println(allCurrencies);

        RestTemplateLibraryService RestTemplateLibraryService = context.getBean(RestTemplateLibraryService.class);
        List<Book> allBooks = RestTemplateLibraryService.getAllBooks();

    }

}
