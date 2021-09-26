package ru.alfabank.api.exam.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.api.exam.config.FeignConfig;
import ru.alfabank.api.exam.model.GetGifInfo;

@FeignClient(
        name = "gifGateway",
        url = "${gif.host}",
        configuration = FeignConfig.class
)
public interface GifGateway {
    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    Object getRichGif (
            @RequestParam(value = "api_key", required = false, defaultValue = "${gif.id}") String appId,
            @RequestParam(value = "q", defaultValue = "rich") String q
     );

    @GetMapping(value = "/search")
    Object getBrokeGif (
            @RequestParam(value = "api_key", required = false, defaultValue = "${gif.id}") String appId,
            @RequestParam(value = "q", defaultValue = "broke") String q
    );
}
