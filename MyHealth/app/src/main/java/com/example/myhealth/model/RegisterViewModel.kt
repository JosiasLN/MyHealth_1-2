package com.example.myhealth.model

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.R
import com.example.myhealth.presentation.screens.login.LoginState
import com.example.myhealth.presentation.screens.register.RegisterState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    val state: MutableState<RegisterState> = mutableStateOf(RegisterState())

    fun register(
        name: String,
        email: String,
        edad: String,
        password: String,
        confirmPassword: String
    ){
        val errorMessage = if(name.isBlank() || email.isBlank() || edad.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            R.string.error_input_empty
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_a_valid_email
        }else if (password != confirmPassword){
            R.string.error_incorrectly_repeated_password
        }else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = it)
        }

        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value = state.value.copy(displayProgressBar = false)

        }
    }


    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}