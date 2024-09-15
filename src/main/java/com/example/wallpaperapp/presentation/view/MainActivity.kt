package com.example.wallpaperapp.presentation.view

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.presentation.adapter.ImagesRecyclerViewAdapter
import com.example.wallpaperapp.databinding.ActivityMainBinding
import com.example.wallpaperapp.domain.entity.WallpaperLink
import com.example.wallpaperapp.presentation.WallPaperUiState
import com.example.wallpaperapp.presentation.adapter.ItemOnClickListener
import com.example.wallpaperapp.presentation.viewmodel.WallpaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val wallpaperViewModel: WallpaperViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        setupViews()
        collectUiState()
        wallpaperViewModel.fetchWallpapers()
    }

    fun setupViews() {
        binding.imagesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ImagesRecyclerViewAdapter(emptyList(), this@MainActivity::onClickImage)
        }
    }

    fun collectUiState() {
        lifecycleScope.launch(Dispatchers.Main) {
            wallpaperViewModel.wallpaperList.collect() { wallpaperUiState ->
                when (wallpaperUiState) {
                    is WallPaperUiState.Loading -> {
                        // progress load
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(
                            this@MainActivity,
                            "Wallpapers are currently Loading",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is WallPaperUiState.EmptyList -> {
                        // empty
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(
                            this@MainActivity,
                            "Wallpapers are currently Empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("MAINACTIVITY", "Wallpapers are currently empty")
                    }

                    is WallPaperUiState.Success -> {
                        // update recyclerview with wallpapers
                        binding.progressBar.visibility = View.GONE
                        populateDataInRecyclerView(wallpaperUiState.data)
                    }

                    is WallPaperUiState.Error -> {
                        Toast.makeText(this@MainActivity, "Error occured", Toast.LENGTH_SHORT)
                            .show()
                        // taost messager -> eror
                    }
                }
            }
        }
    }

    fun populateDataInRecyclerView(list: List<WallpaperLink>) {
        // 1. Update WallpaperAdapter with the list
        // 2. update recyclerview with that adapter
        val wallpaperAdapter = ImagesRecyclerViewAdapter(list, this::onClickImage)
        binding.imagesRecyclerView.adapter = wallpaperAdapter
        binding.imagesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    fun onClickImage(wallpaperLink: String) {
        // Create a new instance of WallpaperManager
           val wallpaperManager = WallpaperManager.getInstance(this)

           // Download the image using Glide and set it as wallpaper
            Glide.with(this)
            .asBitmap()
            .load(wallpaperLink)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    try {
                        // Set the wallpaper
                        wallpaperManager.setBitmap(resource)

                        // Show a toast to confirm the change
                        Toast.makeText(this@MainActivity, "Wallpaper changed successfully", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        // Handle the exception
                        Toast.makeText(this@MainActivity, "Failed to change wallpaper", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}
