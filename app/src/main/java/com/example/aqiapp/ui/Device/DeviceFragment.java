package com.example.aqiapp.ui.Device;

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
import com.example.aqiapp.api.object.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment{

    private DeviceViewModel deviceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final ListView listView = root.findViewById(R.id.list);
        deviceViewModel.getDevices().observe(getViewLifecycleOwner(), new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                final List<String> list = new ArrayList<>();
                for (Device device: devices) {
                    list.add(device.getNodeId());
                    System.out.println(device.toString());
                }
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
                listView.setAdapter(itemsAdapter);
            }
        });

        return root;
    }
}
