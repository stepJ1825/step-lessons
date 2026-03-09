package by.step.webclient;

import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class ReactiveCurrencyController {

    private final WebClientNbrbCurrencyService currencyService;

    @GetMapping("/reactive")
    public Flux<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrenciesReactive();
    }
}
