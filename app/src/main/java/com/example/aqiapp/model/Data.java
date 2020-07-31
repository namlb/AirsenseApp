package com.example.aqiapp.model;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Hum")
    private Double hum;
    @SerializedName("NodeId")
    private String nodeId;
    @SerializedName("PM1")
    private Double pM1;
    @SerializedName("PM10")
    private Double pM10;
    @SerializedName("PM2.5")
    private Double pM25;
    @SerializedName("Tem")
    private Double tem;
    @SerializedName("Time")
    private Integer time;

    public Double getHum() {
        return hum;
    }

    public void setHum(Double hum) {
        this.hum = hum;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Double getPM1() {
        return pM1;
    }

    public void setPM1(Double pM1) {
        this.pM1 = pM1;
    }

    public Double getPM10() {
        return pM10;
    }

    public void setPM10(Double pM10) {
        this.pM10 = pM10;
    }

    public Double getPM25() {
        return pM25;
    }

    public void setPM25(Double pM25) {
        this.pM25 = pM25;
    }

    public Double getTem() {
        return tem;
    }

    public void setTem(Double tem) {
        this.tem = tem;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}