package com.example.aqiapp.ui.device;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aqiapp.R;
import com.example.aqiapp.adapter.DeviceAdapter;
import com.example.aqiapp.model.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment{

    private DeviceViewModel deviceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.device_fragment, container, false);
        final ListView listView = root.findViewById(R.id.list);
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);


        List<Device> deviceList = new ArrayList<>();
        Device device1 = new Device();
        device1.setNodeId("12234");
        Device device2 = new Device();
        device2.setNodeId("12235");
        Device device3 = new Device();
        device3.setNodeId("12236");
        Device device4 = new Device();
        device4.setNodeId("12237");
        deviceList.add(device1);
        deviceList.add(device2);
        deviceList.add(device3);
        deviceList.add(device4);
        DeviceAdapter deviceAdapter = new DeviceAdapter(this.getContext(), R.layout.device_item, deviceList);
        listView.setAdapter(deviceAdapter);
//        deviceViewModel.getDevices().observe(getViewLifecycleOwner(), new Observer<List<Device>>() {
//            @Override
//            public void onChanged(List<Device> devices) {
//                final List<String> list = new ArrayList<>();
//                for (Device device: devices) {
//                    list.add(device.getNodeId());
//                }
//                ArrayAdapter<String> itemsAdapter =
//                        new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
//                listView.setAdapter(itemsAdapter);
//            }
//        });

        return root;
    }
}
