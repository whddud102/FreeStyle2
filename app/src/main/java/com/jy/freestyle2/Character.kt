package com.jy.freestyle2

import java.io.Serializable

class Character(
    name : String,  // 이름
    position : String,  // 포지션
    contract : Boolean, // 계약 방법
    two_point : Int,
    three_point : Int,
    dunk :Int,
    lay_up : Int,
    pass : Int,
    dribble : Int,
    rebound : Int,
    block : Int,
    physical : Int,     // 몸싸움
    jump : Int,
    steal : Int,
    speed : Int,
    total : Int,
    // 특성1, 2, 3, 골드 스킬
    ability1 : String,
    ability2 : String,
    ability3 : String,
    gold_ability_name : String,
    gold_ability : String,
    extra_ability : String,
    hidden_b_minus : String,
    hidden_b : String,
    hidden_b_plus : String,
    hidden_a_minus : String,
    hidden_a : String,
    hidden_a_plus : String,
    hidden_s : String,
    img_thumb_resourceId : Int,
    img_resourceid : Int
) : Serializable {
    val name = name  // 이름
    val position = position  // 포지션
    val contract = contract  // 계약 방법
    val two_point = two_point
    val three_point = three_point
    val dunk = dunk
    val lay_up = lay_up
    val pass = pass
    val dribble = dribble
    val rebound = rebound
    val block = block
    val physical = physical   // 몸싸움
    val jump = jump
    val steal = steal
    val speed = speed
    val total = total
    // 특성1, 2, 3, 골드 스킬
    val ability1 = ability1
    val ability2 = ability2
    val ability3 = ability3
    val gold_ability_name = gold_ability_name
    val gold_ability = gold_ability
    val extra_ability = extra_ability
    val hidden_b_minus = hidden_b_minus
    val hidden_b = hidden_b
    val hidden_b_plus = hidden_b_plus
    val hidden_a_minus = hidden_a_minus
    val hidden_a = hidden_a
    val hidden_a_plus = hidden_a_plus
    val hidden_s = hidden_s
    val img_thumb_resourceId = img_thumb_resourceId
    val img_resourceid = img_resourceid
}
