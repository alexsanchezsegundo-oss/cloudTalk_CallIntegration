package com.springboot.callDownloader.ctService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CTFirstResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)

    @JsonProperty("responseData")
    private ctResponse ctresponse;

    public ctResponse getResponseData() { return ctresponse; }
    public void setResponseData(ctResponse ctresponse) { this.ctresponse = ctresponse; }
}

