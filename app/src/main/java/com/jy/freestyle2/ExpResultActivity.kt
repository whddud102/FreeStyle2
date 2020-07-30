package com.jy.freestyle2

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_exp_result.*
import java.text.DecimalFormat

class ExpResultActivity : AppCompatActivity() {
    val expArray = arrayOf(0, 50, 70, 90, 110, 130, 220, 260, 290, 330, 370,
        400, 440, 480, 520, 850, 910, 970, 1020, 1240, 1310,
        1370, 1440, 1510, 2740, 3320, 3450, 3580, 3710, 3850, 3980,
        8740, 9020, 9300, 9580, 9860, 15550, 15980, 16410, 16840, 17270,
        23470, 24040, 24620, 25190, 25760, 39320, 40170, 41030, 42730, 42730)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp_result)
        supportActionBar?.setTitle("경험치 계산")
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        var targetLv = 50
        val currentLv = intent.getIntExtra("currentLv", 1)
        val currentExp = intent.getStringExtra("currentExp")?.toInt()
        val bigBottleNum = intent.getStringExtra("bigBottleNum")?.toInt()
        val normalBottleNum = intent.getStringExtra("normalBottleNum")?.toInt()
        val smallBottleNum = intent.getStringExtra("smallBottleNum")?.toInt()

        val spinnerItems = ArrayList<String>()
        var arrayAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        val spinnerLv = spinnerTargetLv;

        val formatter = DecimalFormat("###,###")

        // 현재 나의 총 경험치양을 측정
        var startExp  = currentExp?.let { getStartExp(currentLv, it) };

        // 최대 도달 가능 레벨 계산
        setMaxLvTextView(startExp, bigBottleNum, normalBottleNum, smallBottleNum)

        if(currentLv == 50) {
            spinnerItems.add("Lv.50")
        } else {
            for (i in (currentLv+1)..50) {
                val item = "Lv." + i
                spinnerItems.add(item)
            }
        }

        spinnerLv.adapter = arrayAdapter
        spinnerLv.setSelection(spinnerItems.size-1)

        spinnerLv?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                targetLv =   spinnerLv.getItemAtPosition(position).toString().substring(3).toInt()
                //Log.d("MainActivity", "Lv.${targetLv} 선택")

                var targetExp = 0
                for(i in 1..(targetLv-1)) {
                    targetExp += expArray[i]
                }

                //  toast("나의 경험치 : ${startExp},\n목표 경험치 : ${targetExp}")
                val expResult = targetExp- startExp!!
                if(expResult < 0) {
                    tvExpResult.text = "0"
                    tvBottleNums.text = "0개"
                } else {
                    tvExpResult.text = formatter.format(expResult)

                    if(expResult % 1000 != 0) {
                        tvBottleNums.text = formatter.format((expResult / 1000) + 1) + "개"
                    } else {
                        tvBottleNums.text = formatter.format(expResult / 1000) + "개"
                    }
                }
            }
        }

    }

    fun getStartExp(currentLv : Int, currentExp : Int) : Int {
        var startExp = 0
        for(i in 1..(currentLv-1)) {
            startExp += expArray[i]
        }
        startExp += currentExp

        return startExp
    }

    fun setMaxLvTextView(startExp: Int?, bigBottleNum: Int?, normalBottleNum: Int?, smallBottleNum: Int?){
        var expSum = 0;
        val myExp = startExp!! + (bigBottleNum!! * 1000) + (normalBottleNum!! * 500) + (smallBottleNum!! * 100)

        for(i in 1.. 50) {
            expSum += expArray[i]   //  i+1렙까지 도달하는데 필요한 경험치 양
            if(myExp < expSum) {
                tvLvResult.text = "Lv." + (i)
                break
            }
        }
    }
}