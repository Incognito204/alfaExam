package ru.alfabank.api.exam.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alfabank.api.exam.model.GetRatesInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RunWith(SpringRunner.class)
@AutoConfigureWireMock
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyGatewayTest {

    @Autowired
    private CurrencyGateway currencyGateway;

    @Test
    void getActualRatesSuccess() throws IOException {
        stubFor(get(urlPathEqualTo("/latest.json"))
                .withQueryParam("app_id", equalTo("someID"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyGetSuccess.json"))));

        var response = currencyGateway.getActualRates("someID");

        assertEquals(response.getRates().get("RUB"), 72.76475);
    }

    @Test
    void getHistoricalRatesSuccess() throws IOException {
        stubFor(get(urlPathEqualTo("/historical/2021-01-01.json"))
                .withQueryParam("app_id", equalTo("someID"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(getFileAsString("currencyHistoricalGetSuccess.json"))));

        var response = currencyGateway.getHistoricalRates("2021-01-01", "someID");

        assertEquals(response.getRates().get("RUB"), 73.945);
    }

    private static String getFileAsString(String title) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/responses/" + title)));
    }
}