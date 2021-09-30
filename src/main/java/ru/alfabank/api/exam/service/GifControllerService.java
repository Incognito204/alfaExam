package ru.alfabank.api.exam.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.api.exam.gateway.CurrencyGateway;
import ru.alfabank.api.exam.gateway.GifGateway;
import ru.alfabank.api.exam.model.exception.BadTokenException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class GifControllerService {
    private final GifGateway gifGateway;
    private final CurrencyGateway currencyGateway;
    private final ObjectMapper mapper;

    public String getActualGif(String curr_id) throws IOException {
        JsonNode data = mapper.readTree(getFileAsString("currencies.json"));
        if (!data.has(curr_id))
            throw new BadTokenException("Invalid currency token");
        return getActualRates(curr_id) > getHistoricalRates(curr_id) ? getGif("rich") : getGif("broke");

    }

    private double getActualRates(String curr_id) {
        var rates = currencyGateway.getActualRates("d01585797b65414a88870309e136d488");
        return rates.getRates().get(curr_id);
    }

    private double getHistoricalRates(String curr_id) {
        var rates = currencyGateway.getHistoricalRates(
                LocalDate.now().minusDays(1L).format(DateTimeFormatter.ISO_LOCAL_DATE),
                "d01585797b65414a88870309e136d488");
        return rates.getRates().get(curr_id);
    }

    private String getGif(String tag) {
        var rates = gifGateway.getGif("LohHyv8zZHYOZCK6PjKXBWjWocOpQEwI", tag);
        return rates.getData().get("url").toString();
    }

    private static String getFileAsString(String title) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/" + title)));
    }

}
