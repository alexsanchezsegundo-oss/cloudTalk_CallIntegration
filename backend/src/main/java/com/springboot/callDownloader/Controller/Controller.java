package com.springboot.callDownloader.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.springboot.callDownloader.Model.Model;
import com.springboot.callDownloader.Private.PrivateResources;



@RestController
public class Controller {
    Model model = new Model();
    PrivateResources ps = new PrivateResources();
    
    @GetMapping("/cloudtalk/calls")
    public String ctCalls(){
        String url = model.getUrl() ;
        url = url + "/calls/index.json";
        String api_key = ps.getApi_key();
        String api_pass = ps.getApi_pass();
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(api_key, api_pass);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            
            ResponseEntity<String> response = rt.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                String.class
            );

            return "response: " + response.getBody();

        } catch (RestClientException e) {

            return "Error while connecting with Cloudtalk: " + e.getMessage();
        }
        

    }
@GetMapping("/cloudtalk/agents")
    public String ctAgents(){
        String url = model.getUrl() ;
        url = url + "/agents/index.json";
        String api_key = ps.getApi_key();
        String api_pass = ps.getApi_pass();
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(api_key, api_pass);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            
            ResponseEntity<String> response = rt.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                String.class
            );

            return "response: " + response.getBody();

        } catch (RestClientException e) {

            return "Error while connecting with Cloudtalk: " + e.getMessage();
        }
        

    }
@GetMapping("/cloudtalk/numbers")
    public String ctNumbers(){
        String url = model.getUrl() ;
        url = url + "/numbers/index.json";
        String api_key = ps.getApi_key();
        String api_pass = ps.getApi_pass();
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(api_key, api_pass);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            
            ResponseEntity<String> response = rt.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                String.class
            );

            return "response: " + response.getBody();

        } catch (RestClientException e) {

            return "Error while connecting with Cloudtalk: " + e.getMessage();
        }
        

    }

    
}
