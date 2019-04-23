package com.test.homeautomation.screens.main.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.test.homeautomation.R;
import com.test.homeautomation.models.Device;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Device> devices;

    public RecyclerViewAdapter(ArrayList<Device> devices) {
        this.devices = devices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_status_row, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(devices.get(i));
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public void setDevices(List<Device> newDevicesList) {
        this.devices.clear();
        this.devices.addAll(newDevicesList);
        this.notifyDataSetChanged();
    }

}

