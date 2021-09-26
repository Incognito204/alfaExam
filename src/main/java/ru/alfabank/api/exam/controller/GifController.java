package ru.alfabank.api.exam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.api.exam.service.CurrencyService;
import ru.alfabank.api.exam.service.GifService;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class GifController {

    private final GifService gifService;
    private final CurrencyService currencyService;

    @GetMapping("/")
    public ResponseEntity<String> getGif() {
        return currencyService.getActualRates()>currencyService.getHistoricalRates() ? new ResponseEntity<>(gifService.getRichGif(), HttpStatus.OK) : new ResponseEntity<>(gifService.getBrokeGif(), HttpStatus.OK);
    }
}
