package com.nomencleture.employeewithsearch.networks
sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
    class Loading : NetworkResult<Nothing>()

}


sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
data class BaseResponse<T>(
    val result: Int,
    val message: String?,
    val errorMessage: String?,
    val data: T?
)

data class BaseResponse2<T>(
    val result: Int,
    val message: String?,
    val errorMessage: ArrayList<String>?,
    val data: T?
)