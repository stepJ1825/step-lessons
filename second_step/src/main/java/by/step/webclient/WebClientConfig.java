package by.step.webclient;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    private static final String NBRB_BASE_URL = "https://api.nbrb.by";

    @Bean
    public WebClient nbrbWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(NBRB_BASE_URL)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "SecondStepApp/1.0")
                // Таймауты
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024) // 16 MB
                )
                // Логирование запросов/ответов (опционально, для отладки)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    // Фильтр для логирования запроса
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            // Можно добавить логирование: clientRequest.url(), method() и т.д.
            return Mono.just(clientRequest);
        });
    }

    // Фильтр для логирования ответа
    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            // Можно добавить логирование: clientResponse.statusCode(), headers() и т.д.
            return Mono.just(clientResponse);
        });
    }
}