package com.test.homeautomation.screens;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.test.homeautomation.R;
import com.test.homeautomation.models.Device;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    private ArrayList <Device> devices;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private Switch deviceSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.deviceName_text_view);
            deviceSwitch = itemView.findViewById(R.id.device_state_switch);
        }
    }

    public RVAdapter(ArrayList <Device> devices) {
        this.devices = devices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_status_row, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.name.setText(devices.get(i).getName());
        viewHolder.deviceSwitch.setChecked(devices.get(i).getState());
        viewHolder.deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                } else {
                    //do something when unchecked
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

}

