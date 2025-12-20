package com.springboot.callDownloader.ctService;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.callDownloader.Model.AgentWrapper;



@JsonIgnoreProperties(ignoreUnknown=true)
public class ctResponse {
    private List <AgentWrapper> data;
    public List<AgentWrapper> getData(){
        return data;
    }
    public void setData(List<AgentWrapper> data) { 
        this.data = data; 
    }

}
