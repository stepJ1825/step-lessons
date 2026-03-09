package by.step.feign;

import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignCurrencyService {
    private final FeignNbrbClient nbrClient;

    public List<CurrencyDto> getCurrencies() {
       return nbrClient.getCurrencies();
    }
}
