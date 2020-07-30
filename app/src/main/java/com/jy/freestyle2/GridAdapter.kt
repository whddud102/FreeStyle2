package com.jy.freestyle2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class GridAdapter(characters: List<Character>) : BaseAdapter() {
    val characters = characters

        override fun getItemId(position: Int): Long {
        return 0
         }

    override fun getCount(): Int {
        return characters.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh : ViewHolder
        val character: Character = characters[position]

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.thumb, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        Glide.with(view).load(character.img_thumb_resourceId).into(vh.thumb_imageView)
        vh.thumb_textView.text = character.name

        if (character.position == "C") {
            vh.thumb_textView.setBackgroundResource(R.color.colorAccent)
        } else if (character.position == "PF") {
            vh.thumb_textView.setBackgroundResource(R.color.colorBlue)
        } else if (character.position == "SF") {
            vh.thumb_textView.setBackgroundResource(R.color.colorSky)
        } else if (character.position == "SG") {
            vh.thumb_textView.setBackgroundResource(R.color.colorGold)
        } else if (character.position == "CF") {
            vh.thumb_textView.setBackgroundResource(R.color.colorPurple)
        } else if(character.position == "CG") {
            vh.thumb_textView.setBackgroundResource(R.color.colorCG)
        } else {
            vh.thumb_textView.setBackgroundResource(R.color.colorGreen)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return characters[position];
    }

    class ViewHolder(view : View) {
        val thumb_imageView : ImageView = view.findViewById(R.id.thumbImageView)
        val thumb_textView : TextView = view.findViewById(R.id.thumbTextView)
    }
}