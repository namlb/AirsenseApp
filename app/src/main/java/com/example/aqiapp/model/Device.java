package com.example.aqiapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Device {

    @SerializedName("Altitude")
    private Double altitude;
    @SerializedName("Latitude")
    private Double latitude;
    @SerializedName("Longtitude")
    private Double longitude;
    @SerializedName("NodeId")
    private String nodeId;
    @SerializedName("ReverseGeocode")
    private String reverseGeocode;
    @SerializedName("Title")
    private Integer title;

    @Expose
    private List<Data> data = new ArrayList<>();

    public Device(Double altitude, Double latitude, Double longitude, String nodeId, String reverseGeocode, Integer title) {
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nodeId = nodeId;
        this.reverseGeocode = reverseGeocode;
        this.title = title;
    }

    public Device() {

    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getReverseGeocode() {
        return reverseGeocode;
    }

    public void setReverseGeocode(String reverseGeocode) {
        this.reverseGeocode = reverseGeocode;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String toString() {
        if(this.data.size()>0) {
            return this.nodeId +"-"+this.data.get(0).getTime();
        }
        return this.nodeId +"-";
    }
}