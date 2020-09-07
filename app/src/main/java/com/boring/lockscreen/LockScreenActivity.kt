package com.boring.lockscreen

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lockscreen.*


class LockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lockscreen)

        initializeLocationValue()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }


        lockscreen_layout.setOnTouchListener(
            object : OnTouchListener {
                private var x = 0f
                override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                    lockscreen_layout.performClick()
                    val x = motionEvent.x
                    val y = motionEvent.y
                    if (motionEvent.actionMasked.equals(MotionEvent.ACTION_MOVE)) {
                        location_y.setText(getString(R.string.test_location_value, "y", y))
                        location_x.setText(getString(R.string.test_location_value, "x", x))
                        return true
                    }
                    return false
                }
            }
        )
    }

    private fun initializeLocationValue() {
        location_x.setText(getString(R.string.test_location_value, "x", 0f))
        location_y.setText(getString(R.string.test_location_value, "y", 0f))
    }

    fun toast(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}