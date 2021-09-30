package ru.alfabank.api.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.api.exam.service.GifControllerService;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class GifController {
    private final GifControllerService gifControllerService;

    @GetMapping("/")
    public ResponseEntity<String> getGif() {
        return new ResponseEntity<>(gifControllerService.getActualGif(), HttpStatus.OK);
    }
}
