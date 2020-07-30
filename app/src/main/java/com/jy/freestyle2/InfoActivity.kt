package com.jy.freestyle2

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_info.*


class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val character = intent.getSerializableExtra("character") as Character
        supportActionBar?.setTitle(character.name)

        Glide.with(this).load(character.img_resourceid).into(thumbImageView)
        //imageView.setImageResource(character.img_resourceid)
        name.text = character.name
        position.text = character.position

        if(character.position == "C") {
            name.setBackgroundResource(R.color.colorAccent)
        } else if (character.position == "PF") {
            name.setBackgroundResource(R.color.colorBlue)
        } else if (character.position == "SF") {
            name.setBackgroundResource(R.color.colorSky)
        } else if (character.position == "SG") {
            name.setBackgroundResource(R.color.colorGold)
        } else if(character.position == "CF"){
            name.setBackgroundResource(R.color.colorPurple)
        } else if(character.position == "CG") {
            name.setBackgroundResource(R.color.colorCG)
        } else {
            name.setBackgroundResource(R.color.colorGreen)
        }

        if(character.contract) {
            contract.text = "상시계약"
        } else {
            contract.text = "기간한정"
        }

        twoPoint.text = character.two_point.toString()
        threePoint.text = character.three_point.toString()
        dunk.text = character.dunk.toString()
        lay_up.text = character.lay_up.toString()
        pass.text = character.pass.toString()
        dribble.text = character.dribble.toString()
        rebound.text = character.rebound.toString()
        block.text = character.block.toString()
        physical.text = character.physical.toString()
        jump.text = character.jump.toString()
        steal.text = character.steal.toString()
        speed.text = character.speed.toString()
        total.text = character.total.toString()

        ability1.text = character.ability1
        if(ability1.text.length >= 4) {
            ability1.textSize = 16F
        }

        ability2.text = character.ability2
        if(ability2.text.length >= 4) {
            ability2.textSize = 16F
        }

        ability3.text = character.ability3
        if(ability3.text.length >= 4) {
            ability3.textSize = 16F
        }

        var goldAbility_text = "[ " + character.gold_ability_name + " ]"
        if(character.gold_ability != "") {
            goldAbility_text += ("\n" + character.gold_ability)
        }
        gold_ability.text = goldAbility_text

        extra_ability.text = character.extra_ability
        b_minus.text = character.hidden_b_minus
        b.text = character.hidden_b
        b_plus.text = character.hidden_b_plus
        a_minus.text = character.hidden_a_minus
        a.text = character.hidden_a
        a_plus.text = character.hidden_a_plus
        s.text = character.hidden_s
    }
}