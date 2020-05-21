package com.example.aqiapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.aqiapp.api.DeviceApi;
import com.example.aqiapp.api.object.Data;
import com.example.aqiapp.api.object.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeviceRepository {

    DeviceApi deviceApi;
    MutableLiveData<List<Device>> mutableLiveData = new MutableLiveData<>();

    public DeviceRepository() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://airsense.vn:4000/airsense/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        deviceApi = retrofit.create(DeviceApi.class);
    }
    public MutableLiveData<List<Device>> getDevices() {
        final List<Device> devices = new ArrayList<>();
        Call<List<Device>> call = deviceApi.getDevices();
        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if(response.body() != null) {
                    for (Device device: response.body()) {
                        devices.add(device);
                    }
                    mutableLiveData.setValue(devices);
                    for(int i = 0; i<mutableLiveData.getValue().size(); i++){
                        DeviceRepository.this.getDataOfDevice(mutableLiveData.getValue().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }

    public void getDataOfDevice(Device device) {
        final List<Data> data = new ArrayList<>();
        Call<List<Data>> call = deviceApi.getDataByNodeId(device.getNodeId());
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if(response.body() != null && response.body().size()>0) {
                    System.out.println("Time of data: "+response.body().get(0).getTime());
                    data.add(response.body().get(0));
                    device.setData(data);
                    System.out.println("fff");
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                System.out.println("fail");
            }
        });
    }
}
