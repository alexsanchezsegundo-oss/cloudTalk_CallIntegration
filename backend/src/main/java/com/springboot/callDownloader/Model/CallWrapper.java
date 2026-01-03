package com.springboot.callDownloader.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallWrapper {
    @JsonProperty("Call")
    private Model call;

    public Model getCall() { return call; }
    public void setCall(Model call) { this.call = call; }
}