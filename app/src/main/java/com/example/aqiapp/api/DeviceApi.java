package com.example.aqiapp.api;

import com.example.aqiapp.model.Data;
import com.example.aqiapp.model.Device;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeviceApi {

    @GET("devices")
    Call<List<Device>> getDevices();

    @GET("Data7day")
    Call<List<Data>> getDataByNodeId(@Query("NodeId") String nodeId);
}
