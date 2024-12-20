package com.example.viewreciept.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.viewreciept.R
import com.example.viewreciept.databinding.ActivityMainBinding
import com.example.viewreciept.ui.view.fragment.main.BookmarkFragment
import com.example.viewreciept.ui.view.fragment.main.MealSearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //BookmarkManager.loadBookmarkItems(applicationContext)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_search -> openFragment(MealSearchFragment())
                R.id.nav_favorites -> openFragment(BookmarkFragment())
//                R.id.nav_areas -> openFragment(AreasFragment())
//                R.id.nav_profile -> openFragment(ProfileFragment())
            }
            true
        }
        openFragment(MealSearchFragment())
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}