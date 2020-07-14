package com.broadcom.android.tv.debug.tic

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class MainActivity : Activity() {
    val handler = Handler()
    lateinit var mTextView: TextView
    var counter = 0
    private val runnableCode: Runnable = object : Runnable {
        override fun run() {
            mTextView.text = counter.toString()
            counter++
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView = findViewById(R.id.textView)
    }

    override fun onResume() {
        super.onResume()
        counter = 0
        handler.post(runnableCode)
    }

    override fun onPause() {
        handler.removeCallbacks(runnableCode)
        super.onPause()
    }
}