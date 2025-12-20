package com.springboot.callDownloader.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nombre_bd")


public class Model {
    @Id
    @Column(unique = true, nullable = false)
    private int idAgent;
    @Column(name = "nameAgent")
    private String nameAgent;
    @Column(name = "dateTime")
    private String dateTime;
    @Column(name = "downloadUrl")
    private String downloadUrl;
    @Column(name = "extension")
    private int extension;
    @Column(name = "call_id")
    private int callId;
    String url = "https://my.cloudtalk.io/api/";

    //API key and password are in another private .java (in order to maintain safety). These few lines below describes what contains that PrivateResources class 
    /*
    String api_key = "YOUR_API_KEY_HERE";
    String api_pass = "YOUR_API_PASS_HERE";
    public String getApi_key() {
        return api_key;
    }
    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
    public String getApi_pass() {
        return api_pass;
    }
    public void setApi_pass(String api_pass) {
        this.api_pass = api_pass;
    } 
    */

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getCallId() {
        return callId;
    }
    public void setCallId(int callId) {
        this.callId = callId;
    }
    public int getExtension() {
        return extension;
    }
    public void setExtension(int extension) {
        this.extension = extension;
    }
    public int getIdAgent() {
        return idAgent;
    }
    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }
    public String getNameAgent() {
        return nameAgent;
    }
    public void setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
    }
    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getDownloadUrl() {
        return downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }




}