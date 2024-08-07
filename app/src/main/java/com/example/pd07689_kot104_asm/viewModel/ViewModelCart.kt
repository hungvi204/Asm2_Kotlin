package com.example.pd07689_kot104_asm.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pd07689_kot104_asm.request.CartRequest
import com.example.pd07689_kot104_asm.request.service.RetrofitInstance
import com.example.pd07689_kot104_asm.response.CartResponse
import kotlinx.coroutines.launch

class ViewModelCart: ViewModel() {

    private val _listCart = mutableStateOf<List<CartResponse>>(emptyList())
    val listCart: State<List<CartResponse>> = _listCart

    fun listCartViewModel(idUser: String?) {
        viewModelScope.launch {
            try {
                _listCart.value = RetrofitInstance.apiService.getCart(idUser)
                Log.d("TAG", "thanhcong: ${_listCart.value}")
            } catch (e: Exception) {
                Log.d("TAG", "thatbai: ${e.message}")
            }
        }
    }

    fun addToCart(idUser: String, productId: String, quantity: Int = 1) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.addCart(
                    CartRequest(idUser, productId, quantity)
                )
                if (response != null) {
                    listCartViewModel(idUser) // Refresh cart list
                }
                Log.d("TAG", "Add to cart successful: ${response}")
            } catch (e: Exception) {
                Log.d("TAG", "Add to cart failed: ${e.message}")
            }
        }
    }

    fun descreaseCart(idUser: String, productId: String, quantity: Int = 1) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.descreaseCart(
                    CartRequest(idUser, productId, quantity)
                )
                if (response != null) {
                    listCartViewModel(idUser) // Refresh cart list
                }
                Log.d("TAG", "Add to cart successful: ${response}")
            } catch (e: Exception) {
                Log.d("TAG", "Add to cart failed: ${e.message}")
            }
        }
    }

    fun deleteCartById(idUser: String, productId: String, quantity: Int = 1) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.deleteCartById(
                    CartRequest(idUser, productId, quantity)
                )
                if (response != null) {
                    listCartViewModel(idUser) // Refresh cart list
                }
                Log.d("TAG", "Add to cart successful: ${response}")
            } catch (e: Exception) {
                Log.d("TAG", "Add to cart failed: ${e.message}")
            }
        }
    }
}