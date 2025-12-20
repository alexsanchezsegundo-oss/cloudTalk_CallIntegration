package com.springboot.callDownloader.ctService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.callDownloader.Model.AgentWrapper;
import com.springboot.callDownloader.Model.Model;
import com.springboot.callDownloader.Model.ModelRepository;
import com.springboot.callDownloader.Private.PrivateResources;

@Service
public class ctService {
    @Autowired
    private ModelRepository repository;

    @Autowired
    private PrivateResources ps;

    private final RestTemplate restTemplate = new RestTemplate();

    private String syncCalls(String endpoint) {
        String url = "https://my.cloudtalk.io/api" + endpoint;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(ps.getApi_key(), ps.getApi_pass());
        HttpEntity<String> entity = new HttpEntity<>(headers);
    try {
        ResponseEntity<CTFirstResponse> response = restTemplate.exchange(
            url, HttpMethod.GET, entity, CTFirstResponse.class
        );

        List<AgentWrapper> wrappers = response.getBody().getResponseData().getData();

        List<Model> listaParaGuardar = wrappers.stream()
                                            .map(AgentWrapper::getAgent)
                                            .toList();
        
        repository.saveAll(listaParaGuardar);
        return "Sincronizados " + listaParaGuardar.size() + " registros.";
    }catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCalls() { return syncCalls("/calls/index.json"); }
    public String getAgents() { return syncCalls("/agents/index.json"); }
    public String getNumbers() { return syncCalls("/numbers/index.json"); }
}