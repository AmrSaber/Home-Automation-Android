package com.test.homeautomation.screens.main.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.test.homeautomation.R;
import com.test.homeautomation.api.ApiUtils;
import com.test.homeautomation.models.Device;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("WeakerAccess")
public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private Switch deviceSwitch;
    private Device currentDevice;

    public ViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.deviceName_text_view);
        deviceSwitch = itemView.findViewById(R.id.device_state_switch);
    }

    public void bind(Device newDevice) {
        this.currentDevice = newDevice;

        this.name.setText(newDevice.getName());
        this.deviceSwitch.setOnCheckedChangeListener(null);
        this.deviceSwitch.setChecked(newDevice.getState() == 1);

        this.deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int newState = isChecked ? 1 : 0;
                Call<Device> updateDevice = ApiUtils.getApiService()
                        .updateState(currentDevice.id, newState);
                updateDevice.enqueue(new Callback<Device>() {
                    @Override
                    public void onResponse(Call<Device> call, Response<Device> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(
                                    ViewHolder.this.itemView.getContext(),
                                    "Could not update device " + currentDevice.name,
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Device> call, Throwable t) {
                        Toast.makeText(
                                ViewHolder.this.itemView.getContext(),
                                "Could not update device " + currentDevice.name,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
            }
        });

    }
}
