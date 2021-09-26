package ru.alfabank.api.exam.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alfabank.api.exam.gateway.CurrencyGateway;
import ru.alfabank.api.exam.gateway.GifGateway;
import ru.alfabank.api.exam.model.GetGifInfo;
import ru.alfabank.api.exam.model.GetRatesInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifService {

    private final GifGateway gifGateway;
    private final ObjectMapper mapper;

    public String getRichGif() {
        var rates = mapper.convertValue(gifGateway.getRichGif("LohHyv8zZHYOZCK6PjKXBWjWocOpQEwI", "rich"), GetGifInfo.class);
        return rates.getData().get(0).get("url").toString();
    }

    public String getBrokeGif() {
        var rates = mapper.convertValue(gifGateway.getRichGif("LohHyv8zZHYOZCK6PjKXBWjWocOpQEwI", "broke"), GetGifInfo.class);
        return rates.getData().get(0).get("url").toString();
    }
}
