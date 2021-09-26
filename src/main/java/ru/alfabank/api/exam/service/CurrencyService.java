package ru.alfabank.api.exam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alfabank.api.exam.gateway.CurrencyGateway;
import ru.alfabank.api.exam.gateway.GifGateway;
import ru.alfabank.api.exam.model.GetGifInfo;
import ru.alfabank.api.exam.model.GetRatesInfo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyGateway currencyGateway;
    private final ObjectMapper mapper;

    public double getActualRates() {
        var rates = mapper.convertValue(currencyGateway.getActualRates("d01585797b65414a88870309e136d488"), GetRatesInfo.class);
        return Double.parseDouble(rates.getData().get("RUB").toString());
    }

    public double getHistoricalRates() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(Long.valueOf(1));
        DateTimeFormatter formatters = DateTimeFormatter.ISO_LOCAL_DATE;//.ofPattern("YYYY-MM-DD");
        String text = yesterday.format(formatters);
        log.info(text);
        var rates = mapper.convertValue(currencyGateway.getHistoricalRates(text,"d01585797b65414a88870309e136d488"), GetRatesInfo.class);
        return Double.parseDouble(rates.getData().get("RUB").toString());
    }
}
