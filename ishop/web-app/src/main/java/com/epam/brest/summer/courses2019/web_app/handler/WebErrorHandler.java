package com.epam.brest.summer.courses2019.web_app.handler;

import com.epam.brest.summer.courses2019.model.exception.ExceptionResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;

/**
 * The Web error handler catches all errors in the {@code com.epam.brest.summer.courses2019.web_app} package.
 */

@ControllerAdvice("com.epam.brest.summer.courses2019.web_app")
public class WebErrorHandler {

    private final MappingJackson2HttpMessageConverter messageConverter;

    public WebErrorHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    /**
     * Handle http client error exception.
     *
     * @param clientException is {@code HttpClientErrorException}.
     * @param model model contains information for view rendering
     * @return the response entity with message of exception.
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public final String httpClientException(HttpClientErrorException clientException, Model model) {

        model.addAttribute("error", clientException.getMessage());
        model.addAttribute("response", getExceptionResponse(clientException.getResponseBodyAsString()));
        return "error";
    }

    /**
     * Handle http server error exception.
     *
     * @param serverException is {@code HttpServerErrorException}.
     * @param model model contains information for view rendering
     * @return the response entity with message of exception.
     */
    @ExceptionHandler(HttpServerErrorException.class)
    public final String httpServerErrorException(HttpServerErrorException serverException, Model model){

        model.addAttribute("error", serverException.getMessage());
        model.addAttribute("response", getExceptionResponse(serverException.getResponseBodyAsString()));
        return "error";
    }

    /**
     * Handle resource access exception.
     *
     * @param resourceException is {@code ResourceAccessException}.
     * @param model model contains information for view rendering
     * @return the response entity with message of exception.
     */
    @ExceptionHandler(value = ResourceAccessException.class)
    public final String resourceAccessException(ResourceAccessException resourceException, Model model) {

        model.addAttribute("error", resourceException.getMessage());
        return "resource_exc";
    }

    private Object getExceptionResponse(String json) {

        try {

            return messageConverter.getObjectMapper().readValue(json, ExceptionResponse.class);

        } catch (IOException e) {

            return new ExceptionResponse("No response!");
        }
    }
}
