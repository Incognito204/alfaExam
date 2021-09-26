package ru.alfabank.api.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

public class FeignConfig {
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json"),
                new MediaType("text", "plain"),
                new MediaType("application", "octet-stream"),
                MediaType.TEXT_HTML
        ));
        return jsonConverter;
    }

    @Bean
    public HttpMessageConverter formHttpMessageConverter() {
        return new FormHttpMessageConverter();
    }
}
