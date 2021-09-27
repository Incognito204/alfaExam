package ru.alfabank.api.exam.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class GetGifInfo {

   // @NotNull Long status;

   // @NotNull String message;

    GetGifInfo(){}

    @JsonProperty("data")
    JsonNode data;
}