package ru.alfabank.api.exam.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.api.exam.model.GetRatesInfo;

@FeignClient(
        name = "currencyRatesGateway",
        url = "${currency.host}"
)
public interface CurrencyGateway {
    @GetMapping(value = "/latest.json")
    GetRatesInfo getActualRates(
            @RequestParam(value = "app_id", required = false, defaultValue = "${currency.id}") String appId
    );

    @GetMapping(value = "/historical/{currDate}.json")
    GetRatesInfo getHistoricalRates(
            @PathVariable("currDate") String currDate,
            @RequestParam(value = "app_id", required = false, defaultValue = "${currency.id}") String appId
    );
}
