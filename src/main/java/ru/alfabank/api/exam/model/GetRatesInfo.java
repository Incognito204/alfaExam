package ru.alfabank.api.exam.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRatesInfo {

    GetRatesInfo(){}

    @JsonProperty("rates")
    JsonNode data;
}
