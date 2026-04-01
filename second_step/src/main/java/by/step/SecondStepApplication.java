package by.step;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // Включаем сканирование Feign клиентов
public class SecondStepApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecondStepApplication.class, args);

//        FeignCurrencyService feignCurrencyService = context.getBean(FeignCurrencyService.class);
//        List<CurrencyDto> currencies = feignCurrencyService.getCurrencies();

//        FeignLibraryService libraryService = context.getBean(FeignLibraryService.class);
//        String books = libraryService.getBooksInString();

//        RestTemplateLibraryService restTemplateLibraryService = context.getBean(RestTemplateLibraryService.class);
//        List<Book> allBooks = restTemplateLibraryService.getAllBooks();

//        WebClientNbrbCurrencyService webClientNbrbCurrencyService = context.getBean(WebClientNbrbCurrencyService.class);
//        List<CurrencyDto> allCurrenciesSync =
//                webClientNbrbCurrencyService.getAllCurrenciesSync();
//
//        Flux<CurrencyDto> allCurrenciesReactive = webClientNbrbCurrencyService.getAllCurrenciesReactive();
//        System.out.println(allCurrenciesReactive);

    }

}
