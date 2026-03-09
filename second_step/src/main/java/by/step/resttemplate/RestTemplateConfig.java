package by.step.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    private static final String NBRB_BASE_URL = "https://api.nbrb.by";
    public static final String LIBRARY_BASE_URL = "http://localhost:8082/";

    @Bean("nbrbRestTemplate")
    public RestTemplate nbrbRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(NBRB_BASE_URL)
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(15))
                .requestFactory(() -> {
                    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
                    factory.setConnectTimeout(10_000);
                    factory.setReadTimeout(15_000);
                    return factory;
                })
                .additionalMessageConverters(Collections.singletonList(mappingJackson2HttpMessageConverter()))
                .errorHandler(new NbrbResponseErrorHandler())
                .build();
    }

    @Bean("libraryRestTemplate")
    public RestTemplate libraryRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(LIBRARY_BASE_URL)
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(15))
                .requestFactory(() -> {
                    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
                    factory.setConnectTimeout(10_000);
                    factory.setReadTimeout(15_000);
                    return factory;
                })
                .additionalMessageConverters(Collections.singletonList(mappingJackson2HttpMessageConverter()))
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}