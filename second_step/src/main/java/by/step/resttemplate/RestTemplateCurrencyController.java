package by.step.resttemplate;


import by.step.common.CurrencyApiException;
import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class RestTemplateCurrencyController {

    private final RestTemplateNbrbCurrencyService currencyService;

    @GetMapping("/rest-template")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        return ResponseEntity.ok(currencies);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CurrencyDto> getCurrencyByCode(@PathVariable String code) {
        try {
            CurrencyDto currency = currencyService.getCurrencyByCode(code);
            return ResponseEntity.ok(currency);
        } catch (CurrencyApiException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
