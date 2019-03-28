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
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = RestApp.class)
@TestPropertySource("classpath:application.properties")
public class ProxyControllerTest {
    private TestRestTemplate template = new TestRestTemplate();
    private ProxyController proxy = new ProxyController();

    @Rule
    public WireMockRule alphaWireMockRule = new WireMockRule(1000);

    @Rule
    public WireMockRule betaWireMockRule = new WireMockRule(2000);

    @Rule
    public WireMockRule gammaWireMockRule = new WireMockRule(3000);
    
    @Test
    public void getOnAlpha_FetchDataFromExternalService_ShouldReturnAlphaDto()
    {
        alphaWireMockRule.stubFor(get(urlEqualTo("/objects/0"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 0, \"name\": \"alpha\", \"method\": \"get\" }")));

        ResponseEntity<AlphaDto> alphaDto = proxy.alpha();
        
        ResponseEntity<AlphaDto> response = this.template.exchange("http://localhost:8000", HttpMethod.GET, null, AlphaDto.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void getOnBeta_FetchDataFromExternalService_ShouldReturnBetaDto()
    {
        betaWireMockRule.stubFor(post(urlEqualTo("/objects/0"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 0, \"name\": \"beta\", \"method\": \"post\" }")));

        ResponseEntity<BetaDto> response = this.template.exchange("http://localhost:8000", HttpMethod.POST, null, BetaDto.class);
        
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
    
    @Test
    public void getOnGamma_FetchDataFromExternalService_ShouldReturnGammaDto()
    {
        gammaWireMockRule.stubFor(put(urlEqualTo("/objects/0"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 0, \"name\": \"gamma\", \"method\": \"put\" }")));

        ResponseEntity<GammaDto> response = this.template.exchange("http://localhost:8000", HttpMethod.PUT, null, GammaDto.class);
        
        System.out.println(response.getBody());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }   
}