package com.test.homeautomation.screens.main;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.test.homeautomation.R;


public class PopupWindow extends Activity {

    android.widget.PopupWindow popupWindow;
    int width, height;
    boolean focusable = true; // lets taps outside the popup also dismiss it
    LinearLayout layout;

    public PopupWindow() {

    }

    public void init_popup(Context cxt) {
        View popupView = View.inflate(cxt, R.layout.add_device_popup, null);

        // create the popup window
        width = LinearLayout.LayoutParams.WRAP_CONTENT;
        height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout = new LinearLayout(cxt);
        popupWindow = new android.widget.PopupWindow(popupView, width, height, focusable);
    }

    public void show_popup(int x, int y) {
        popupWindow.showAtLocation(layout, Gravity.CENTER, x, y);
    }

    public void dismiss_popup() {
        popupWindow.dismiss();
    }


}
