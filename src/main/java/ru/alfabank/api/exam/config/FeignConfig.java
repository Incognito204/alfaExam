package ru.alfabank.api.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

public class FeignConfig {
    @Bean
    public HttpMessageConverter formHttpMessageConverter() {
        return new FormHttpMessageConverter();
    }
}
