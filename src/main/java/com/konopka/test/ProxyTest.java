import org.junit.runner.RunWith;
import org.springframework.test.context.SpringBootTest;
import com.github.tomakehurst.wiremock.client.WireMock.*;
import com.konopka.dtos.AlphaDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyTest {
    private TestRestTemplate template = new TestRestTemplate();
    private WireMockRule wireMockRule = new WireMockRule(1000);

    @Test
    public void getOnAlpha_FetchDataFromExternalService_ShouldReturnAlphaDto()
    {
        stubFor(get(urlEqualTo("/system"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value()
                .withHeader("Content-Type", TEXT_PLAIN_VALUE))
                .withBody("test"))
        );

        ResponseEntity<AlphaDto> response = this.template.getForEntity("http://localhost:8000/system", AlphaDto.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getOnBeta_FetchDataFromExternalService_ShouldReturnBetaDto()
    {

    }
    
    @Test
    public void getOnGamma_FetchDataFromExternalService_ShouldReturnGammaDto()
    {

    }
}