package com.example.aqiapp.api;

import com.example.aqiapp.api.object.Aqi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AqiApi {
    @GET("getCurrentAqi")
    Call<List<Aqi>> getCurrentAqi();
}
