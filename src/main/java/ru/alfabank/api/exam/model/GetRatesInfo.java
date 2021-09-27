package ru.alfabank.api.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class GetRatesInfo {
    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;
}
