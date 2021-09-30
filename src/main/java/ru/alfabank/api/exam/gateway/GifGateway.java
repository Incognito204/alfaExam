package ru.alfabank.api.exam.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.api.exam.model.GetGifInfo;

@FeignClient(
        name = "gifGateway",
        url = "${gif.host}"
)
public interface GifGateway {
    @GetMapping(value = "/random")
    GetGifInfo getGif(
            @RequestParam(value = "api_key", required = false, defaultValue = "${gif.id}") String appId,
            @RequestParam(value = "tag") String tag
    );
}
