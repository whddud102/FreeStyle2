package com.jy.freestyle2

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        viewButton.setOnClickListener {
            startActivity<ListActivity>()
        }

        comparisonButton.setOnClickListener {
            startActivity<ComparisonActivity>()
        }

        expButton.setOnClickListener {
            startActivity<ExpActivity>()
        }
    }
}