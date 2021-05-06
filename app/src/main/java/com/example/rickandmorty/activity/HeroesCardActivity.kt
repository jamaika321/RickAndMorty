package com.example.rickandmorty.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.pojo.Heroes
import com.squareup.picasso.Picasso


class HeroesCardActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes_card)
    }

    override fun onStart() {
        super.onStart()

        val heroesList: Heroes? = intent.extras?.getParcelable("heroes")
        Log.d("SecondActivity", heroesList?.name.toString())

        val tvName : TextView? = findViewById(R.id.tv_name)
        val tvStatus : TextView? = findViewById(R.id.tv_status)
        val tvSpecies : TextView? = findViewById(R.id.tv_species)
        val tvGender : TextView? = findViewById(R.id.tv_gender)
        val ivAvatar = findViewById<ImageView>(R.id.iv_character)
        tvName?.text = heroesList?.name
        tvStatus?.text = heroesList?.status
        if(heroesList?.status == "Alive")
        {
            tvStatus?.setTextColor(resources.getColor(R.color.green))
        }else{
            tvStatus?.setTextColor(resources.getColor(R.color.red))
        }
        tvSpecies?.text = heroesList?.species
        tvGender?.text = heroesList?.gender
        Picasso.get().load(heroesList?.image?.toUri()).into(ivAvatar)




    }


}