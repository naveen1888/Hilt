package com.rawat.hilt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawat.hilt.UiState
import com.rawat.hilt.models.Product
import com.rawat.hilt.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private val _productLiveData = MutableLiveData<UiState<List<Product>>>(UiState.Loading)
    val productLiveData: LiveData<UiState<List<Product>>> get() = _productLiveData

    fun fetchProductWithLiveData() {
        if (_productLiveData.value == UiState.Loading) {
            viewModelScope.launch(Dispatchers.Main) {
                productRepository.getProducts()
                    .flowOn(Dispatchers.IO) // above this all code will be executed on io and below on Main
                    .catch { throwable ->
                        val message: String? = if (throwable is retrofit2.HttpException) {
                            "Error message " + throwable.message + " Error code " + throwable.code()
                        } else {
                            throwable.message
                        }
                        _productLiveData.value = UiState.Error(message)
                    }.collect {
                        _productLiveData.value = UiState.Success(it) //Success case
                    }
            }
        }
    }
}