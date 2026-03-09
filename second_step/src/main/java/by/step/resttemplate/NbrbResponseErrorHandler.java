package by.step.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

@Slf4j
public class NbrbResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            log.warn("Клиентская ошибка при вызове NBRB API: {}", response.getStatusCode());
            throw new HttpClientErrorException(response.getStatusCode(), "Некорректный запрос к API валют");
        }
        if (response.getStatusCode().is5xxServerError()) {
            log.error("Серверная ошибка при вызове NBRB API: {}", response.getStatusCode());
            throw new HttpServerErrorException(response.getStatusCode(), "Внешний сервис валют недоступен");
        }
        // Для остальных случаев — делегируем стандартному обработчику
        super.handleError(response);
    }
}
