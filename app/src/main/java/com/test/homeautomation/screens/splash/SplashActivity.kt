package com.test.homeautomation.screens.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.homeautomation.R
import com.test.homeautomation.screens.main.MainActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    private var timer = Timer()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        timer.schedule(object : TimerTask() {
            override fun run() {
                this@SplashActivity.finish()
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
