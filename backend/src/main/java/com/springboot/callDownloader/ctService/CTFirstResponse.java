package com.springboot.callDownloader.ctService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CTFirstResponse<T> { 
    @JsonProperty("responseData")
    private ctResponse<T> responseData;

    public ctResponse<T> getResponseData() { return responseData; }
    public void setResponseData(ctResponse<T> responseData) { this.responseData = responseData; }
}
