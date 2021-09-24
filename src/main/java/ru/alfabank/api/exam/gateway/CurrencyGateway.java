package ru.alfabank.api.exam.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.api.exam.config.FeignConfig;

@FeignClient(
        name = "currencyRatesGateway",
        url = "${currency.host}",
        configuration = FeignConfig.class
)
public interface CurrencyGateway {
    @GetMapping(value = "/latest.json")
    ResponseEntity getActualRates (
            @RequestParam(value = "app_id", required = false, defaultValue = "${currency.id}") String appId
    );

    @GetMapping(value = "/historical.json/{currDate}")
    Object getHistoricalRates (
            @PathVariable("currDate") String currDate,
            @RequestParam(value = "app_id", required = false, defaultValue = "${currency.id}") String appId
    );
}
