package com.springboot.callDownloader.ctService;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.callDownloader.Model.AgentWrapper;
import com.springboot.callDownloader.Model.CallWrapper;
import com.springboot.callDownloader.Model.Model;
import com.springboot.callDownloader.Model.ModelRepository;
import com.springboot.callDownloader.Private.PrivateResources;

@Service
public class ctService {
    Model model = new Model();
    @Autowired
    private ModelRepository repository;
    @Autowired
    private PrivateResources ps;
    private final RestTemplate restTemplate = new RestTemplate();
/*
    private String syncAgentsData(String endpoint) {
        String url = "https://my.cloudtalk.io/api" + endpoint;
        String type = model.getType();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(ps.getApi_key(), ps.getApi_pass());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<CTFirstResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CTFirstResponse.class
            );
            List<?> wrappers = response.getBody().getResponseData().getData();
            List<Model> listaParaGuardar = rawData.stream()
                            .map(obj -> mapper.convertValue(obj, AgentWrapper.class))
                            .map(AgentWrapper::getAgent)
                            .toList();
        
            //repository.saveAll(listaParaGuardar);
            //return "Sincronizados " + listaParaGuardar.size() + " registros.";
            return listaParaGuardar.toString();

            

        }catch (Exception e) {
                return "Error: " + e.getMessage();
            }
    }
    private String syncCallData(String endpoint) {
        String url = "https://my.cloudtalk.io/api" + endpoint;
        String type = model.getType();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(ps.getApi_key(), ps.getApi_pass());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<CTFirstResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CTFirstResponse.class
            );
            List<?> wrappers = response.getBody().getResponseData().getData();


            //entering calls
            List<Model> listaParaGuardar = wrappers.stream()
                                                .<Model>map(obj -> ((CallWrapper) obj).getCall()) 
                                                .toList();
        
            //repository.saveAll(listaParaGuardar);
            //return "Sincronizados " + listaParaGuardar.size() + " registros.";
            return listaParaGuardar.toString();


        }catch (Exception e) {
                return "Error: " + e.getMessage();
            }
    }
*/
private String syncAgentsData(String endpoint) {
        String url = "https://my.cloudtalk.io/api" + endpoint;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(ps.getApi_key(), ps.getApi_pass());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CTFirstResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CTFirstResponse.class
            );
            
            List<Object> rawData = response.getBody().getResponseData().getData();

            // Convertimos cada mapa a AgentWrapper y luego extraemos el Agent (Model)
            List<Model> listaParaGuardar = rawData.stream()
                .map(obj -> mapper.convertValue(obj, AgentWrapper.class))
                .map(AgentWrapper::getAgent)
                .toList();
        
            repository.saveAll(listaParaGuardar);
            return "Agentes sincronizados: " + listaParaGuardar.size();

        } catch (Exception e) {
            return "Error en Agentes: " + e.getMessage();
        }
    }

    private String syncCallData(String endpoint) {
        String url = "https://my.cloudtalk.io/api" + endpoint;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(ps.getApi_key(), ps.getApi_pass());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CTFirstResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CTFirstResponse.class
            );
            
            List<Object> rawData = response.getBody().getResponseData().getData();

            // Convertimos cada mapa a CallWrapper y luego extraemos la Call (Model)
            List<Model> listaParaGuardar = rawData.stream()
                .map(obj -> mapper.convertValue(obj, CallWrapper.class))
                .map(CallWrapper::getCall) // Asegúrate de que el método sea getCall() o getCalls()
                .toList();
        
            repository.saveAll(listaParaGuardar);
            return "Llamadas sincronizadas: " + listaParaGuardar.size();

        } catch (Exception e) {
            return "Error en Llamadas: " + e.getMessage();
        }
    }
    public String getCalls() { 
        model.setType("C");
        return syncCallData("/calls/index.json"); }
    public String getAgents() { 
        model.setType("A");
        return syncAgentsData("/agents/index.json"); }
    public String getNumbers() { 
        model.setType("N");
        return syncCallData("/numbers/index.json"); }
    public String callToCSV(){
        //can be changed. We used a prestablished datetime variable for the from option
        model.setdateTimeFrom("2025-11-13T14:00:00");
        model.setDateTimeTo( LocalDate.now().toString() + "T14:28:00");
        String beginDate = model.getdateTimeFrom();
        String endDate = model.getDateTimeTo();
        String endp = UriComponentsBuilder.fromPath("/calls/index.json")
                                          .queryParam("date_from", beginDate)
                                          .queryParam("date_to", endDate)
                                          .toUriString();
        System.out.println("endp"  + endp);
        model.setType("C");
        return syncCallData(endp);
    }
}