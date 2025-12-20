package com.springboot.callDownloader.ctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.callDownloader.Model.Model;
import com.springboot.callDownloader.Private.PrivateResources;

@Service
public class ctService {

    @Autowired
    private Model model;

    @Autowired
    private PrivateResources ps;

    private final RestTemplate restTemplate = new RestTemplate();

    private String fetchData(String endpoint) {
        String url = model.getUrl() + endpoint;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(ps.getApi_key(), ps.getApi_pass());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class
            );
            return response.getBody();
        } catch (Exception e) {
            return "Error al conectar con " + endpoint + ": " + e.getMessage();
        }
    }

    public String getCalls() { return fetchData("/calls/index.json"); }
    public String getAgents() { return fetchData("/agents/index.json"); }
    public String getNumbers() { return fetchData("/numbers/index.json"); }
}