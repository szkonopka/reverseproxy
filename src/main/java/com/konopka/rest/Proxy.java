package com.konopka.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/system")
public class Proxy {
    private RestTemplate restRedirect = new RestTemplate();

    ResponseEntity<Map<String, String>> response;

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

    @RequestMapping(value = "", method=RequestMethod.GET)
    public Map<String, String> alpha()
    {
        /*
        response = restRedirect.exchange(
            protocolPrefix + getUrl + "/" + entityName, 
            HttpMethod.GET, 
            null,
            new ParameterizedTypeReference<Map<String, String>>(){});
        return response.getBody();
        */

        List<Map<String, String>>rest = restRedirect.getForObject(protocolPrefix + getUrl + "/" + entityName, List.class);
        return rest.get(0);
    }

    @RequestMapping(value = "", method=RequestMethod.POST)
    public Map<String, String> beta()
    {
        response = restRedirect.exchange(
            protocolPrefix + postUrl + "/" + entityName, 
            HttpMethod.GET, 
            null,
            new ParameterizedTypeReference<Map<String, String>>(){});
        //return new HashMap<String, String>();
        return response.getBody();
    }

    @RequestMapping(value = "", method=RequestMethod.PUT)
    public Map<String, String> gamma()
    {
        response = restRedirect.exchange(
            protocolPrefix + putUrl + "/" + entityName, 
            HttpMethod.GET, 
            null,
            new ParameterizedTypeReference<Map<String, String>>(){});
        return response.getBody();
    }
}