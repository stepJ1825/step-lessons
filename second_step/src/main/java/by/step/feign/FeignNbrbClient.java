package by.step.feign;

import by.step.common.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "nbrb-service", url = "https://api.nbrb.by")
public interface FeignNbrbClient {
    @GetMapping("/exrates/currencies")
    List<CurrencyDto> getCurrencies();
}
