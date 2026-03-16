package by.step;

import by.step.common.Book;
import by.step.common.BookSimpleDto;
import by.step.common.CurrencyDto;
import by.step.feign.FeignCurrencyService;
import by.step.feign.FeignLibraryService;
import by.step.resttemplate.RestTemplateLibraryService;
import by.step.webclient.WebClientNbrbCurrencyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
@EnableFeignClients // Включаем сканирование Feign клиентов
public class SecondStepApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecondStepApplication.class, args);

//        FeignCurrencyService feignCurrencyService = context.getBean(FeignCurrencyService.class);
//        List<CurrencyDto> currencies = feignCurrencyService.getCurrencies();

        FeignLibraryService libraryService = context.getBean(FeignLibraryService.class);
        String books = libraryService.getBooksInString();

//        RestTemplateLibraryService restTemplateLibraryService = context.getBean(RestTemplateLibraryService.class);
//        List<Book> allBooks = restTemplateLibraryService.getAllBooks();

//        WebClientNbrbCurrencyService webClientNbrbCurrencyService = context.getBean(WebClientNbrbCurrencyService.class);
//        List<CurrencyDto> allCurrenciesSync =
//                webClientNbrbCurrencyService.getAllCurrenciesSync();
//
//        Flux<CurrencyDto> allCurrenciesReactive = webClientNbrbCurrencyService.getAllCurrenciesReactive();
//        System.out.println(allCurrenciesReactive);

        System.out.println();

    }

}
