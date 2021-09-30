package ru.alfabank.api.exam.gateway;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alfabank.api.exam.StubsSetup.getGifBadAppIdRequest;
import static ru.alfabank.api.exam.StubsSetup.getGifSuccessRequest;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GifGatewayTest {

    @Autowired
    GifGateway gifGateway;

    @Test
    void getGifSuccess() throws IOException {
        getGifSuccessRequest();

        var response = gifGateway.getGif("someID", "rich");

        assertEquals(response.getMeta().get("status"), "200");
        assertEquals(response.getMeta().get("msg"), "OK");
        assertEquals(response.getMeta().get("response_id"), "2faf39cdd5c2c6a93bf11a466765929fabf559e7");
        assertEquals(response.getData().get("type").toString(), "\"gif\"");
        assertEquals(response.getData().get("id").toString(), "\"8mqypkr5zOeQsrn1ZX\"");
        assertEquals(response.getData().get("url").toString(), "\"https://giphy.com/gifs/rich-equestrian-plies-8mqypkr5zOeQsrn1ZX\"");
    }

    @Test
    void badGifAppId() throws IOException {
        getGifBadAppIdRequest();
        var exception = assertThrows(FeignException.class, () -> gifGateway.getGif("someID1", "rich"));
        assertTrue(exception.getMessage().contains("Invalid authentication credentials"));
    }
}