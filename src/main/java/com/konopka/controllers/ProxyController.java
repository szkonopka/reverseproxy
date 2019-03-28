package com.konopka.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.konopka.dtos.AlphaDto;
import com.konopka.dtos.BetaDto;
import com.konopka.dtos.GammaDto;

@RestController
@RequestMapping(value = "")
public class ProxyController {
    private RestTemplate restRedirect = new RestTemplate();

    @Value("${protocol}")
    private String protocolPrefix;

    @Value("${entities.name}")
    private String entityName;

    @Value("${destination.get}")
    private String getUrl;

    @Value("${destination.post}")
    private String postUrl;

    @Value("${destination.put}")
    private String putUrl;

    @GetMapping(value = "")
    public ResponseEntity<AlphaDto> alpha()
    {
        System.out.println(getUrl);
        System.out.println(getUrl);
        System.out.println(getUrl);
        System.out.println(getUrl);
        System.out.println(getUrl);
        System.out.println(getUrl);
        ResponseEntity<AlphaDto> alphaDto = restRedirect.exchange("http://localhost:1000/objects/0", HttpMethod.GET, null, AlphaDto.class);
        
        return alphaDto;
    }

    @PostMapping(value = "")
     public ResponseEntity<BetaDto> beta()
    {
        ResponseEntity<BetaDto> betaDto = restRedirect.exchange("http://localhost:1000/objects/0", HttpMethod.POST, null, BetaDto.class);

        return betaDto;
    }

    @PutMapping(value = "")
    public ResponseEntity<GammaDto> gamma()
    {
        ResponseEntity<GammaDto> gammaDto = restRedirect.exchange("http://localhost:1000/objects/0", HttpMethod.PUT, null, GammaDto.class);
        
        return gammaDto;
    }
}