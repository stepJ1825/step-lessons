package by.step.resttemplate;

import by.step.common.CurrencyApiException;
import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestTemplateNbrbCurrencyService {

    private final RestTemplate nbrbRestTemplate;

    /**
     * Получение списка всех валют (синхронный вызов).
     */
    public List<CurrencyDto> getAllCurrencies() {
        try {
            ResponseEntity<List<CurrencyDto>> response = nbrbRestTemplate.exchange(
                    "/exrates/currencies",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CurrencyDto>>() {
                    }
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();

        } catch (HttpClientErrorException e) {
            log.error("HTTP ошибка клиента при получении валют: {}", e.getStatusCode(), e);
            throw new CurrencyApiException("Некорректный запрос к API валют: " + e.getMessage(), e);

        } catch (HttpServerErrorException e) {
            log.error("HTTP ошибка сервера при получении валют: {}", e.getStatusCode(), e);
            throw new CurrencyApiException("Сервис валют временно недоступен", e);

        } catch (Exception e) {
            log.error("Неожиданная ошибка при получении валют", e);
            throw new CurrencyApiException("Ошибка связи с внешним сервисом", e);
        }
    }

    /**
     * Получение валюты по коду (пример с фильтрацией на стороне клиента). NBRB API не поддерживает фильтрацию по коду
     * напрямую в этом эндпоинте.
     */
    public CurrencyDto getCurrencyByCode(String code) {
        List<CurrencyDto> currencies = getAllCurrencies();
        return currencies.stream()
                         .filter(c -> c.getCode() != null && c.getCode().equalsIgnoreCase(code))
                         .findFirst()
                         .orElseThrow(() -> new CurrencyApiException("Валюта с кодом " + code + " не найдена", null));
    }

}
