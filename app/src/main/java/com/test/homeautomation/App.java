package com.test.homeautomation;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public App() {
        instance = this;
    }

}
