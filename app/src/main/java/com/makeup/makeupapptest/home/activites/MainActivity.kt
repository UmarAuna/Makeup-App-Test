package com.makeup.makeupapptest.home.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.makeup.makeupapptest.R
import com.makeup.makeupapptest.databinding.ActivityMainBinding
import com.makeup.makeupapptest.home.viewmodels.MainActivityViewModel
import com.makeup.makeupapptest.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.observeTitle.observe(this) { title ->
            binding.layoutAppBar.navTitleTextView.text = title
        }

        viewModel.observeShowBackButton.observe(this) {
            binding.layoutAppBar.navBackImageView.isVisible = it
        }

        viewModel.observeShowSortButton.observe(this) {
            binding.layoutAppBar.navButton.isVisible = it
        }
        viewModel.observeSortButtonCLicked.observe(this) {
            binding.layoutAppBar.navButton.setOnClickListener {
                findNavController(R.id.home_nav_host_fragment).navigate(R.id.sortingDialog)
            }
        }

        binding.layoutAppBar.navBackImageView.setOnClickListener {
            super.onBackPressed()
        }
    }
}
