package com.example.pd07689_kot104_asm.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pd07689_kot104_asm.request.LoginRequest
import com.example.pd07689_kot104_asm.request.RegisterRequest
import com.example.pd07689_kot104_asm.request.service.RetrofitInstance
import com.example.pd07689_kot104_asm.response.Response
import kotlinx.coroutines.launch

class ViewModelAuthenticate:ViewModel() {

    private val _register = mutableStateOf<Response?>(null)
    val register: State<Response?> = _register

    fun registerViewModel(registerReq: RegisterRequest) {
        viewModelScope.launch {
            try {
                _register.value = RetrofitInstance.apiService.register(registerReq)
                Log.d("TAG", "thanhcong: ${_register.value}")
            } catch (e: Exception) {
                Log.d("TAG", "thatbai: ${e.message}")
            }
        }
    }

    private val _login = mutableStateOf<Response?>(null)
    val login: State<Response?> = _login

    fun loginViewModel(loginReq: LoginRequest) {
        viewModelScope.launch {
            try {
                _login.value = RetrofitInstance.apiService.login(loginReq)
                Log.d("TAG", "thanhcong: ${_login.value}")
            } catch (e: Exception) {
                Log.d("TAG", "thatbai: ${e.message}")
            }
        }
    }

    fun getUserIdFromPreferences(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("INFO", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", null)
    }
}