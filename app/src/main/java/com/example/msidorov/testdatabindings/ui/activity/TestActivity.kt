package com.example.msidorov.testdatabindings.ui.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.msidorov.testdatabindings.R
import com.example.msidorov.testdatabindings.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
    }
}
