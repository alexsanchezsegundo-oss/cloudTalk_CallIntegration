package com.springboot.callDownloader.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentWrapper {
    @JsonProperty("Agent") 
    private Model agent; 

    public Model getAgent() { return agent; }
    public void setAgent(Model agent) { this.agent = agent; }
}
