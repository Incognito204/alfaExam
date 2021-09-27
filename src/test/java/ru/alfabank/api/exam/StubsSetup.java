package ru.alfabank.api.exam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class StubsSetup {
    public static void getRatesSuccess() throws IOException {
        stubFor(get(urlPathEqualTo("/latest.json"))
                .withQueryParam("app_id", equalTo("someID"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyGetSuccess.json"))));
    }

    public static void getBadAppIdRequest() throws IOException {
        stubFor(get(urlPathEqualTo("/latest.json"))
                .withQueryParam("app_id", equalTo("someID1"))
                .willReturn(aResponse().withStatus(401).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyBadAppIdResponse.json"))));
    }

    public static void getHistoricalSuccess() throws IOException {
        stubFor(get(urlPathEqualTo("/historical/2021-01-01.json"))
                .withQueryParam("app_id", equalTo("someID"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyHistoricalGetSuccess.json"))));
    }

    public static void getBadHistoricalAppIdRequest() throws IOException {
        stubFor(get(urlPathEqualTo("/historical/2021-01-01.json"))
                .withQueryParam("app_id", equalTo("someID1"))
                .willReturn(aResponse().withStatus(401).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyBadAppIdResponse.json"))));
    }

    public static void getHistoricalInvalidDateRequest() throws IOException {
        stubFor(get(urlPathEqualTo("/historical/2021-08-32.json"))
                .withQueryParam("app_id", equalTo("someID"))
                .willReturn(aResponse().withStatus(400).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyHistoricalInvalidDateResponse.json"))));
    }


    private static String getFileAsString(String title) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/responses/" + title)));
    }
}
