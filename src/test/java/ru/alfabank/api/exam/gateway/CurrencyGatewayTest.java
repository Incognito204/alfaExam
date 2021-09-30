package ru.alfabank.api.exam.gateway;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alfabank.api.exam.StubsSetup.*;


@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CurrencyGatewayTest {

    @Autowired
    private CurrencyGateway currencyGateway;

    @Test
    void getActualRatesSuccess() throws IOException {
        getRatesSuccess();

        var response = currencyGateway.getActualRates("someID");

        assertEquals(response.getDisclaimer(), "Usage subject to terms: https://openexchangerates.org/terms");
        assertEquals(response.getLicense(), "https://openexchangerates.org/license");
        assertEquals(response.getTimestamp(), 1632675610L);
        assertEquals(response.getBase(), "USD");
        assertEquals(response.getRates().get("RUB"), 72.76475);
    }

    @Test
    void badAppIdRequest() throws IOException {
        getBadAppIdRequest();
        var exception = assertThrows(FeignException.class, () -> currencyGateway.getActualRates("someID1"));
        assertTrue(exception.getMessage().contains("invalid_app_id"));
    }

    @Test
    void getHistoricalRatesSuccess() throws IOException {
        getHistoricalSuccess();
        var response = currencyGateway.getHistoricalRates("2021-01-01", "someID");

        assertEquals(response.getDisclaimer(), "Usage subject to terms: https://openexchangerates.org/terms");
        assertEquals(response.getLicense(), "https://openexchangerates.org/license");
        assertEquals(response.getTimestamp(), 1609545595L);
        assertEquals(response.getBase(), "USD");
        assertEquals(response.getRates().get("RUB"), 73.945);
    }

    @Test
    void badHistoricalAppIdRequest() throws IOException {
        getBadHistoricalAppIdRequest();
        var exception = assertThrows(FeignException.class, () -> currencyGateway.getHistoricalRates("2021-01-01", "someID1"));
        assertTrue(exception.getMessage().contains("invalid_app_id"));
    }

    @Test
    void invalidDateRequest() throws IOException {
        getHistoricalInvalidDateRequest();
        var exception = assertThrows(FeignException.class, () -> currencyGateway.getHistoricalRates("2021-08-32", "someID"));
        assertTrue(exception.getMessage().contains("invalid_date"));

    }

}