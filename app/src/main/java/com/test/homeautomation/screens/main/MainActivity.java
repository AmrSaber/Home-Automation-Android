package com.test.homeautomation.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.github.nkzawa.socketio.client.Socket;
import com.test.homeautomation.R;
import com.test.homeautomation.api.ApiUtils;
import com.test.homeautomation.models.Device;
import com.test.homeautomation.screens.add_device.AddDeviceActivity;
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
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        addedDeviceName = this.findViewById(R.id.device_name_editText);
        addedDevicePin = this.findViewById(R.id.pin_id_editText);

        RecyclerView rv = findViewById(R.id.my_recycler_view);
        adapter = new RecyclerViewAdapter(devices);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected  void onStart(){
        super.onStart();

        Call<List<Device>> getDS = ApiUtils.getApiService().getDevices();
        getDS.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>>
                                           call, Response<List<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Unable to call API",
                            Toast.LENGTH_SHORT
                    ).show();

                }
                adapter.setDevices(response.body());
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Unable to call API", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void add_device_popup(View view) {
        startActivity(new Intent(MainActivity.this, AddDeviceActivity.class));
    }



}






