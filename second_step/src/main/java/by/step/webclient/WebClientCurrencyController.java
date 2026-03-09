package by.step.webclient;

import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class WebClientCurrencyController {

    private final WebClientNbrbCurrencyService currencyService;

    @GetMapping("/sync")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        List<CurrencyDto> currencies = currencyService.getAllCurrenciesSync();
        return ResponseEntity.ok(currencies);
    }

    @GetMapping("/sync/code/{code}")
    public ResponseEntity<CurrencyDto> getCurrencyByCode(@PathVariable String code) {
        return currencyService.getCurrencyByCode(code)
                              .map(ResponseEntity::ok)
                              .defaultIfEmpty(ResponseEntity.notFound().build())
                              .block(); // для простоты в синхронном контроллере
    }
}