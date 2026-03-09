package by.step.feign;

import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignCurrencyService {
    private final FeignNbrbClient nbrClient;

    public void getCurrencies() {
        List<CurrencyDto> currencies = nbrClient.getCurrencies();
        System.out.println(currencies);
    }
}
