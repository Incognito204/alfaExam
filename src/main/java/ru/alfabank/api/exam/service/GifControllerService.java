package ru.alfabank.api.exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.api.exam.gateway.CurrencyGateway;
import ru.alfabank.api.exam.gateway.GifGateway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class GifControllerService {
    private final GifGateway gifGateway;
    private final CurrencyGateway currencyGateway;

    public String getActualGif() {
        return getActualRates() > getHistoricalRates() ? getGif("rich") : getGif("broke");
    }

    private double getActualRates() {
        var rates = currencyGateway.getActualRates("d01585797b65414a88870309e136d488");
        return rates.getRates().get("RUB");
    }

    private double getHistoricalRates() {
        var rates = currencyGateway.getHistoricalRates(
                LocalDate.now().minusDays(1L).format(DateTimeFormatter.ISO_LOCAL_DATE),
                "d01585797b65414a88870309e136d488");
        return rates.getRates().get("RUB");
    }

    private String getGif(String tag) {
        var rates = gifGateway.getGif("LohHyv8zZHYOZCK6PjKXBWjWocOpQEwI", tag);
        return rates.getData().get("url").toString();
    }
}
