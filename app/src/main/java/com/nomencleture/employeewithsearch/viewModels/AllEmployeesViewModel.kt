package com.nomencleture.employeewithsearch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomencleture.employeewithsearch.models.EmployeeModels
import com.nomencleture.employeewithsearch.networks.ApiService
import com.nomencleture.employeewithsearch.networks.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class AllEmployeesViewModel @Inject constructor(
    private val repository: AllEmployeesRepository
) : ViewModel() {

    val allEmpData = repository.allEmpData

    fun getEmpData() {
        viewModelScope.launch {
            repository.allEmpData()
        }
    }
}
@Singleton
class AllEmployeesRepository @Inject constructor(
    private val apiServices: ApiService
) {

    private val _allEmpData = MutableStateFlow<UiState<EmployeeModels>>(UiState.Loading)
    val allEmpData: StateFlow<UiState<EmployeeModels>> = _allEmpData

    suspend fun allEmpData() {
        _allEmpData.value = UiState.Loading
        try {
            val response = apiServices.getAllEmployees()
            if (response.isSuccessful && response.body() != null) {
                _allEmpData.value = UiState.Success(response.body()!!)
            } else {
                _allEmpData.value = UiState.Error(response.message())
            }
        } catch (e: Exception) {
            _allEmpData.value = UiState.Error(e.message ?: "Unknown error")
        }
    }
}