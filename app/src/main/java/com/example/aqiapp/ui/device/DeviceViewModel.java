package com.example.aqiapp.ui.device;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aqiapp.api.object.Device;
import com.example.aqiapp.repository.DeviceRepository;

import java.util.List;

public class DeviceViewModel extends ViewModel {
    private MutableLiveData<List<Device>> devices;
    private DeviceRepository deviceRepository = new DeviceRepository();

    public MutableLiveData<List<Device>> getDevices() {
        if (devices == null) {
            devices = deviceRepository.getDevices();
        }
        return devices;
    }
}
