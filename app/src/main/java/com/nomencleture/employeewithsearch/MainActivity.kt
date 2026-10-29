package com.nomencleture.employeewithsearch

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nomencleture.employeewithsearch.networks.UiState
import com.nomencleture.employeewithsearch.viewModels.AllEmployeesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val AllEmpVM: AllEmployeesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        driverDetails()


    }
    private fun driverDetails() {


        AllEmpVM.getEmpData()

        lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                AllEmpVM.allEmpData.collect { state ->

                    when (state) {

                        is UiState.Loading -> {
                        }

                        is UiState.Success -> {
                            Log.d("LogALL",state.data.toString())
                        }

                        is UiState.Error -> {

                        }
                    }
                }
            }
        }

    }

}