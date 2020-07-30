package com.jy.freestyle2

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_exp.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ExpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp)
        supportActionBar?.setTitle("경험치 계산")
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // ----- 스피너 생성 및 초기화 -------------
        val spinnerItems = ArrayList<String>()

        for (i in 1..50) {
            val item = "Lv." + i
            spinnerItems.add(item)
        }

        val arrayAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        val spinnerLv = spinnerTargetLv;
        spinnerLv.adapter = arrayAdapter
        spinnerLv.setSelection(0)

        var currentLv = 1;

        spinnerLv?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentLv = position+1;
                Log.d("MainActivity", "Lv.${currentLv} 선택")
            }

        }
        //---------------------------------------------

        button.setOnClickListener {
            val currentExp = tvCurrentExp.text.toString()
            val bigBottleNum = tvBigBottleNum.text.toString()
            val normalBottleNum = tvNormalBottleNum.text.toString()
            val smallBottleNum = tvSmallBottleNum.text.toString()

            if(currentExp.length == 0) {
                toast("경험치를 입력해주세요")
            } else if(bigBottleNum.length == 0 || normalBottleNum.length == 0 || smallBottleNum.length == 0) {
                toast("생수 개수를 입력해주세요")
            } else {
                startActivity<ExpResultActivity>(
                    "currentLv" to currentLv,
                    "currentExp" to currentExp,
                    "bigBottleNum" to bigBottleNum,
                    "normalBottleNum" to normalBottleNum,
                    "smallBottleNum" to smallBottleNum
                )
            }

        }
    }
}