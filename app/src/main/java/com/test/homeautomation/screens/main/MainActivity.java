package com.test.homeautomation.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.test.homeautomation.R;
import com.test.homeautomation.api.ApiUtils;
import com.test.homeautomation.models.Device;
import com.test.homeautomation.screens.main.recycler.RecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<com.test.homeautomation.models.Device> devices = new ArrayList<>();
    PopupWindow add_device_popup;
    RecyclerViewAdapter adapter;

    EditText addedDeviceName, addedDevicePin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add_device_popup = new PopupWindow();
        setContentView(R.layout.activity_main);

        addedDeviceName = this.findViewById(R.id.device_name_editText);
        addedDevicePin = this.findViewById(R.id.pin_id_editText);

        RecyclerView rv = findViewById(R.id.my_recycler_view);
        adapter = new RecyclerViewAdapter(devices);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        populateRecycler();
    }

    private void populateRecycler() {
        //get devices from server to RV
        Call<List<Device>> getDS = ApiUtils.getApiService().getDevices();
        getDS.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>>
                                           call, Response<List<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Error",
                            Toast.LENGTH_SHORT
                    ).show();

                }

                adapter.setDevices(response.body());

                Toast.makeText(
                        getApplicationContext(),
                        response.body().toString(),
                        Toast.LENGTH_SHORT
                ).show();

            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Unable to submit post to API", Toast.LENGTH_SHORT).show();
                Log.v("ERROR", t.getMessage());
                call.cancel();
            }
        });
    }

    public void add_device_popup(View view) {
        add_device_popup.init_popup(this);
        add_device_popup.show_popup(0, 0);
    }

    public void submit_device(View view) {
        String deviceName = addedDeviceName.getText().toString();
        int pin = Integer.parseInt(addedDevicePin.getText().toString());

        Call<Device> add = ApiUtils
                .getApiService()
                .addDevice(
                        deviceName,
                        pin
                );

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
                populateRecycler();
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Unable to submit post to API", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        add_device_popup.dismiss_popup();
    }
    public void dismiss_popup(View view){
        add_device_popup.dismiss_popup();
    }
}






