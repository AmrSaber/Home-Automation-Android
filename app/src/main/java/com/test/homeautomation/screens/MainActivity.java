package com.test.homeautomation.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.test.homeautomation.R;

public class MainActivity extends AppCompatActivity {

    PopupWindow add_device_popup ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add_device_popup =  new PopupWindow();
        setContentView(R.layout.activity_main);
    }


    public void add_device_popup(View view){
        add_device_popup.init_popup(this);
        add_device_popup.show_popup(0, 0);
    }

    public void submit_device(View view){
        //submit device
        add_device_popup.dismiss_popup();

    }


}
