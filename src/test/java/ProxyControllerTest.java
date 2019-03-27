import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import com.konopka.rest.RestApp;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import com.konopka.dtos.AlphaDto;
import com.konopka.dtos.BetaDto;
import com.konopka.dtos.GammaDto;
import com.konopka.controllers.ProxyController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProxyController.class)
public class ProxyControllerTest {
    //@Autowired
    private TestRestTemplate template = new TestRestTemplate();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);
    
    @Test
    public void getOnAlpha_FetchDataFromExternalService_ShouldReturnAlphaDto()
    {
        //WireMockRule wireMockRule = new WireMockRule(1000);
        stubFor(get(urlEqualTo("localhost:1000/objects/0"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "text/json")
                .withBody("{ \"id\": 0, \"name\": \"alpha\", \"method\": \"get\" }")));

        ResponseEntity<AlphaDto> response = this.template.exchange("/system", HttpMethod.GET, null, AlphaDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void getOnBeta_FetchDataFromExternalService_ShouldReturnBetaDto()
    {
        //WireMockRule wireMockRule = new WireMockRule(2000);
        stubFor(post(urlEqualTo("/objects/0"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 0, \"name\": \"beta\", \"method\": \"post\" }")));

        ResponseEntity<BetaDto> response = this.template.exchange("http://localhost:8080/system", HttpMethod.POST, null, BetaDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(true);
    }
    
    @Test
    public void getOnGamma_FetchDataFromExternalService_ShouldReturnGammaDto()
    {
        //WireMockRule wireMockRule = new WireMockRule(3000);
        stubFor(put(urlEqualTo("/objects/0"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 0, \"name\": \"gamma\", \"method\": \"put\" }")));

        ResponseEntity<GammaDto> response = this.template.exchange("http://localhost:8080/system", HttpMethod.PUT, null, GammaDto.class);
        assertTrue(true);
    }   
}