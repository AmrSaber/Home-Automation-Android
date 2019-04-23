package com.test.homeautomation.screens.add_device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.test.homeautomation.R;
import com.test.homeautomation.api.ApiUtils;
import com.test.homeautomation.models.Device;
import com.test.homeautomation.models.requests.AddDeviceRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity {
    EditText addedDeviceName, addedDevicePin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_device_popup);

        addedDeviceName = this.findViewById(R.id.device_name_editText);
        addedDevicePin = this.findViewById(R.id.pin_id_editText);
    }

    public void submit_device(View view) {
        String deviceName = addedDeviceName.getText().toString();
        int pin = Integer.parseInt(addedDevicePin.getText().toString());

        Call<Device> add = ApiUtils
                .getApiService()
                .addDevice(new AddDeviceRequest(deviceName,pin));

        add.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Error",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }


                Toast.makeText(
                        getApplicationContext(),
                        "Device added successfully",
                        Toast.LENGTH_SHORT
                ).show();

                AddDeviceActivity.this.finish();
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Unable to submit post to API", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void dismiss_popup(View view){
        AddDeviceActivity.this.finish();
    }
}
