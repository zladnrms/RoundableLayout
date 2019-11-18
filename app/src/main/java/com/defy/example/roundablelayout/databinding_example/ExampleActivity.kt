package com.defy.example.roundablelayout.databinding_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.defy.example.roundablelayout.R
import com.defy.example.roundablelayout.databinding.ActivityExampleBinding

class ExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleBinding
    private lateinit var articleViewModel: ExampleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        articleViewModel = ViewModelProviders.of(this).get(ExampleViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_example
        )
        binding.viewModel = articleViewModel
        binding.lifecycleOwner = this
    }
}
