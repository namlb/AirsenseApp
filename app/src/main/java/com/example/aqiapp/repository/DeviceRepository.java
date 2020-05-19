package com.example.aqiapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aqiapp.api.DeviceApi;
import com.example.aqiapp.api.object.Data;
import com.example.aqiapp.api.object.Device;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeviceRepository {

    DeviceApi deviceApi;
    MutableLiveData<List<Device>> mutableLiveData = new MutableLiveData<>();

    public DeviceRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://airsense.vn:4000/airsense/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        deviceApi = retrofit.create(DeviceApi.class);
    }
    public MutableLiveData<List<Device>> getDevices() {
        final List<Device> devices = new ArrayList<>();
        Call<List<Device>> call = deviceApi.getDevices();
        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                for (Device device: response.body()) {
                    List<Data> data = DeviceRepository.this.getDataOfDevice(device.getNodeId());
                    device.setData(data);
                    devices.add(device);
                }
                mutableLiveData.setValue(devices);
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }

    public List<Data> getDataOfDevice(String nodeId) {
        final List<Data> data = new ArrayList<>();
        Call<List<Data>> call = deviceApi.getDataByNodeId(nodeId, null);
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if(response.body() != null && response.body().size()>0) {
                    data.add(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
        return data;
    }
}
