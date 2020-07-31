package com.example.aqiapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aqiapp.R;
import com.example.aqiapp.model.Device;

import java.util.List;

public class DeviceAdapter extends BaseAdapter {

    private List<Device> devices;
    private Context context;
    private int positionSelect = -1;
    private int idLayout;

    public DeviceAdapter(Context context, int idLayout, List<Device> devices) {
        this.devices = devices;
        this.context = context;
        this.idLayout = idLayout;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout, parent, false);
        }

        TextView nodeId = (TextView) convertView.findViewById(R.id.nodeId);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.status);
        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.idLinearLayout);
        final Device device = devices.get(position);

        if (devices != null && !devices.isEmpty()) {
            nodeId.setText(device.getNodeId());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (positionSelect == position) {
            linearLayout.setBackgroundColor(Color.BLUE);
        } else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
}
