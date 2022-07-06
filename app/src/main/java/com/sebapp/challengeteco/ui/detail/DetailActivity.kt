package com.sebapp.challengeteco.ui.detail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.sebapp.challengeteco.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setWindowStyle()

        setDetailCharacter()

    }

    private fun setDetailCharacter() {

        with(intent) {
            val name = getStringExtra("NAME")
            val status = getStringExtra("STATUS")
            val species = getStringExtra("SPECIES")
            val gender = getStringExtra("GENDER")
            val image = getStringExtra("IMAGE")
            val origin = getStringExtra("ORIGIN")

            tv_Name.text = name
            tv_Status.text = status
            tv_Species.text = species
            tv_Gender.text = gender
            Glide.with(imageView_Photo)
                .load(image)
                .centerCrop()
                .into(imageView_Photo)
            tv_Origin.text = origin
        }

    }

    private fun setWindowStyle() {

        val window = window
        window.statusBarColor = Color.WHITE
        supportActionBar?.title = "Challenge Teco 2022"

    }
}