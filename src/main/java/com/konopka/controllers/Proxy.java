package com.konopka.rest;

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
@RequestMapping(value = "/system")
public class Proxy {
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
        AlphaDto alphaDto = restRedirect.getForObject(
            protocolPrefix + getUrl + "/" + entityName + "/0", 
            AlphaDto.class
        );
        
        return new ResponseEntity<AlphaDto>(alphaDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
     public ResponseEntity<BetaDto> beta()
    {
        BetaDto betaDto = restRedirect.getForObject(
            protocolPrefix + postUrl + "/" + entityName + "/0", 
            BetaDto.class
        );

        return new ResponseEntity<BetaDto>(betaDto, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<GammaDto> gamma()
    {
        GammaDto gammaDto = restRedirect.getForObject(
            protocolPrefix + putUrl + "/" + entityName + "/0", 
            GammaDto.class
        );
        
        return new ResponseEntity<GammaDto>(gammaDto, HttpStatus.OK);
    }
}