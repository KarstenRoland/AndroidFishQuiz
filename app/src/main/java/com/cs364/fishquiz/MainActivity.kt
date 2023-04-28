package com.cs364.fishquiz

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs364.fishquiz.ui.data.FishDatabase
import com.cs364.fishquiz.ui.data.FishRepository
import com.cs364.fishquiz.ui.main.SectionsPagerAdapter
import com.cs364.fishquiz.databinding.ActivityMainBinding
import com.cs364.fishquiz.ui.ViewModelFactory
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.main.FishDBViewModel
//import com.cs364.fishquiz.ui.main.MyApplicationTheme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelDB: FishDBViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        //    var mbDB = FishDatabase.getInstance(this)
          //  var repo = FishRepository(mbDB.fishDao())
            //var viewModelDB by remember { mutableStateOf(FishDBViewModel(repo)) }
            //val result by viewModelDB.getAllFish().collectAsState(initial = emptyList())
            viewModelDB = viewModel(factory = ViewModelFactory.Factory)
            var res = viewModelDB.fishUiState.value.fishList[1].common_name
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}