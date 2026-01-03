package com.springboot.callDownloader.ctService;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class ctResponse { 
    private List<Object> data;
    
    public List<Object> getData() { return data; }
    public void setData(List<Object> data) { this.data = data; }
}
