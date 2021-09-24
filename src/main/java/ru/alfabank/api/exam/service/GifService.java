package ru.alfabank.api.exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.api.exam.gateway.CurrencyGateway;

@Service
@RequiredArgsConstructor
public class GifService {

    private final CurrencyGateway currencyGateway;

    public String getGif() {
        currencyGateway.getActualRates("${currency.id}");
        return "";
    }
}
