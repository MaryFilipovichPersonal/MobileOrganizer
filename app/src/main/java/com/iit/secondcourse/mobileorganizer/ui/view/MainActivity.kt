package com.iit.secondcourse.mobileorganizer.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iit.secondcourse.mobileorganizer.databinding.ActivityMainBinding
import com.iit.secondcourse.mobileorganizer.ui.view.main.MainFragment
import com.iit.secondcourse.mobileorganizer.utils.MAIN_FRAGMENT

class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, MainFragment.newInstance(), MAIN_FRAGMENT)
            .commit()
    }

}