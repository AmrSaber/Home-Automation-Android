package com.test.homeautomation.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.test.homeautomation.R;
import com.test.homeautomation.models.Device;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Device> devices = new ArrayList<>();

    PopupWindow add_device_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add_device_popup = new PopupWindow();
        setContentView(R.layout.activity_main);
        test();
        initRV();
    }

    private void test() {
        devices.add(new Device("d1", true, 1));
        devices.add(new Device("d2", false, 2));
        devices.add(new Device("d3", true, 3));
        devices.add(new Device("d4", false, 4));
        devices.add(new Device("d5", true, 5));
        devices.add(new Device("d6", false, 6));
        devices.add(new Device("d7", true, 7));
        devices.add(new Device("d8", false, 8));
        devices.add(new Device("d9", true, 9));
        devices.add(new Device("d10", false, 10));
        devices.add(new Device("d11", true, 11));
        devices.add(new Device("d12", false, 12));
        devices.add(new Device("d13", true, 13));
        devices.add(new Device("d14", false, 14));
        devices.add(new Device("d15", true, 15));
        devices.add(new Device("d16", false, 16));
        devices.add(new Device("d17", true, 17));
        devices.add(new Device("d18", false, 18));
    }

    private void initRV() {
        RecyclerView rv = findViewById(R.id.my_recycler_view);
        RVAdapter adapter = new RVAdapter(devices);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    public void add_device_popup(View view) {
        add_device_popup.init_popup(this);
        add_device_popup.show_popup(0, 0);
    }

    public void submit_device(View view) {
        //submit device
        add_device_popup.dismiss_popup();

    }
}
