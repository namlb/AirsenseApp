package com.example.aqiapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.aqiapp.api.AqiApi;
import com.example.aqiapp.api.object.Aqi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AqiRepository {
    AqiApi aqiApi;
    MutableLiveData<List<Aqi>> currentApis = new MutableLiveData<>();
    public AqiRepository() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://103.1.238.170/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        aqiApi = retrofit.create(AqiApi.class);
    }

    public MutableLiveData<List<Aqi>> getCurrentAqi() {
        Call<List<Aqi>> call = aqiApi.getCurrentAqi();
        call.enqueue(new Callback<List<Aqi>>() {
            @Override
            public void onResponse(Call<List<Aqi>> call, Response<List<Aqi>> response) {
                if(response.body() != null) {
                    currentApis.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Aqi>> call, Throwable t) {
            }
        });
        return currentApis;
    }
}
