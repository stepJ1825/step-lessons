package by.step.webclient;


import by.step.common.CurrencyApiException;
import by.step.common.CurrencyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebClientNbrbCurrencyService {

    private final WebClient nbrbWebClient;

    /**
     * Синхронный метод: получает список валют и блокирует поток до получения результата.
     * Подходит для простых сценариев и контроллеров.
     */
    public List<CurrencyDto> getAllCurrenciesSync() {
        try {
            return nbrbWebClient.get()
                                .uri("/exrates/currencies")
                                .retrieve()
                                .bodyToFlux(CurrencyDto.class)
                                .collectList()
                                .block(Duration.ofSeconds(10)); // таймаут ожидания
        } catch (WebClientResponseException e) {
            log.error("HTTP ошибка при получении валют: {}", e.getStatusCode(), e);
            throw new CurrencyApiException("Ошибка внешнего API: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Неожиданная ошибка при получении валют", e);
            throw new CurrencyApiException("Сервис валют недоступен", e);
        }
    }

    /**
     * Асинхронный/реактивный метод: возвращает Flux для реактивной обработки.
     */
    public Flux<CurrencyDto> getAllCurrenciesReactive() {
        return nbrbWebClient.get()
                            .uri("/exrates/currencies")
                            .retrieve()
                            .bodyToFlux(CurrencyDto.class)
                            .onErrorResume(e -> {
                                log.error("Ошибка при получении валют", e);
                                return Flux.empty(); // или вернуть fallback-данные
                            });
    }

    /**
     * Получение валюты по коду (пример с параметром запроса)
     */
    public Mono<CurrencyDto> getCurrencyByCode(String code) {
        return nbrbWebClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/exrates/currencies")
                                    .queryParam("periodicity", 0) // пример параметра
                                    .build())
                            .retrieve()
                            .bodyToFlux(CurrencyDto.class)
                            .filter(currency -> currency.getCode().equalsIgnoreCase(code))
                            .next();
    }
}
