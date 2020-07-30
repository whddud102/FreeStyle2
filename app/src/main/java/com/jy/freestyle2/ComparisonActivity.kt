package com.jy.freestyle2

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_comparison.*
import org.jetbrains.anko.startActivityForResult
import kotlin.math.abs

class ComparisonActivity : AppCompatActivity() {
    var leftCharacterSelected: Boolean = false
    var rightCharacterSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparison)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setTitle("스탯 비교")

        imageViewLeft.setOnClickListener {
            startActivityForResult<ListActivity>(0, "ComparisonMode" to true, "orientation" to "left")
        }

        imageViewRight.setOnClickListener {
            startActivityForResult<ListActivity>(1, "ComparisonMode" to true, "orientation" to "right")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val character = (data?.getSerializableExtra("character")) as Character

            if (leftCharacterSelected && rightCharacterSelected) {
                // 오른쪽 초기화
                clear_Difference()
            }

            // 왼쪽 캐릭 선택됨
            if (requestCode == 0) {

                fill_Left_Info(character)
                leftCharacterSelected = true

                // 오른쪽 캐릭 선택됨
            } else {
                fill_Right_Info(character)
                rightCharacterSelected = true
            }

            // 두 캐릭 모두 선택된 경우 속성 차이값을 계산
            if (leftCharacterSelected && rightCharacterSelected) {
                calculateDifference()
            }
        }

    }

    private fun fill_Right_Info(character: Character) {
        imageViewRight.setImageResource(character.img_thumb_resourceId)
        nameRight.text = character.name
        positionRight.text = character.position

        if (character.contract) {
            contractRight.text = "상시계약"
        } else {
            contractRight.text = "기간한정"
        }

        twoPointRight.text = character.two_point.toString()
        threePointRight.text = character.three_point.toString()
        dunkRight.text = character.dunk.toString()
        layUpRight.text = character.lay_up.toString()
        passRight.text = character.pass.toString()
        dribbleRight.text = character.dribble.toString()
        reboundRight.text = character.rebound.toString()
        blockRight.text = character.block.toString()
        physicalRight.text = character.physical.toString()
        jumpRight.text = character.jump.toString()
        stealRight.text = character.steal.toString()
        speedRight.text = character.speed.toString()
        totalRight.text = character.total.toString()
        ability1Right.text = character.ability1
        ability2Right.text = character.ability2
        ability3Right.text = character.ability3
        goldAbilityRight.text = "[" + character.gold_ability_name + "]"
    }

    private fun fill_Left_Info(character: Character) {
        imageViewLeft.setImageResource(character.img_thumb_resourceId)
        nameLeft.text = character.name

        positionLeft.text = character.position

        if (character.contract) {
            contractLeft.text = "상시계약"
        } else {
            contractLeft.text = "기간한정"
        }

        twoPointLeft.text = character.two_point.toString()
        threePointLeft.text = character.three_point.toString()
        dunkLeft.text = character.dunk.toString()
        layUpLeft.text = character.lay_up.toString()
        passLeft.text = character.pass.toString()
        dribbleLeft.text = character.dribble.toString()
        reboundLeft.text = character.rebound.toString()
        blockLeft.text = character.block.toString()
        physicalLeft.text = character.physical.toString()
        jumpLeft.text = character.jump.toString()
        stealLeft.text = character.steal.toString()
        speedLeft.text = character.speed.toString()
        totalLeft.text = character.total.toString()
        ability1Left.text = character.ability1
        ability2Left.text = character.ability2
        ability3Left.text = character.ability3
        goldAbilityLeft.text = "[" + character.gold_ability_name + "]"
    }

    private fun clear_Difference() {
        for (i in 4..16) {
            val subLayout = LinearLayout_Comparison.getChildAt(i) as LinearLayout

            for (k in 1..2) {
                val textView = subLayout.getChildAt(k) as TextView
                val endIndex = textView.text.toString().indexOf('(') - 1

                textView.text = textView.text.toString().substring(0, endIndex)
            }
        }
    }

    private fun calculateDifference() {
        for (i in 4..16) {
            val subLayout = LinearLayout_Comparison.getChildAt(i) as LinearLayout
            val leftTextView = subLayout.getChildAt(1) as TextView
            val rightTextView = subLayout.getChildAt(2) as TextView
            val leftValue = leftTextView.text.toString().toInt()
            val rightValue = rightTextView.text.toString().toInt()
            val difference = leftValue - rightValue
            val absDifference = abs(difference)



            // 중요!!! 텍스트를 center로 정렬시 칸이 부족한 경우 자동으로 띄어쓰기를 기준으로 줄바꿈을 해줌
            // 띄어쓰기를 안할시 뒷글자 부터 자릿수를 초과하는만큼 아랫줄로 넘어감
            if (difference > 0) {
                leftTextView.text = leftTextView.text.toString() + " (+${absDifference})"
                rightTextView.text = rightTextView.text.toString() + " (-${absDifference})"
                leftTextView.setTextColor(Color.RED)
                rightTextView.setTextColor(Color.BLUE)
            } else if (difference < 0) {
                leftTextView.text = leftTextView.text.toString() + " (-${absDifference})"
                rightTextView.text = rightTextView.text.toString() + " (+${absDifference})"
                leftTextView.setTextColor(Color.BLUE)
                rightTextView.setTextColor(Color.RED)
            } else {
                leftTextView.text = leftTextView.text.toString() + " (+0)"
                rightTextView.text = rightTextView.text.toString() + " (+0)"
                leftTextView.setTextColor(Color.BLACK)
                rightTextView.setTextColor(Color.BLACK)
            }
        }
    }
}