package com.kemalakkus.newsapp_retrofit_room_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.kemalakkus.newsapp_retrofit_room_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nav= Navigation.findNavController(this,R.id.newsNavHostFragment)
        binding.bottomNav.setupWithNavController(nav)




    }

}