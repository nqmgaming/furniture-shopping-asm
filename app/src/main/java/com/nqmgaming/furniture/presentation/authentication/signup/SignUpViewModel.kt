package com.nqmgaming.furniture.presentation.authentication.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
):ViewModel() {
    val navigateToAppScreen = MutableStateFlow(false)
    private val _name = MutableStateFlow<String>("")
    val name: Flow<String> = _name

    private val _email = MutableStateFlow<String>("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword

    private val _message = MutableStateFlow("")
    val message = _message

    fun onNameChange(name: String){
        _name.value = name
    }

    fun onEmailChange(email: String){
        _email.value = email
    }

    fun onPasswordChange(password: String){
        _password.value = password
    }

    fun onConfirmPasswordChange(confirmPassword: String){
        _confirmPassword.value = confirmPassword
    }
    fun onSignUp() {
        viewModelScope.launch {
            val result = signUpUseCase.execute(
                SignUpUseCase.Input(
                    email = _email.value,
                    password = _password.value,
                    name = _name.value
                )
            )
            when (result) {
                is SignUpUseCase.Output.Success -> {
                    _message.emit("Account created successfully!")
                    navigateToAppScreen.emit(true)
                    Log.d("SignUpViewModel", "onSignUp: Account created successfully!")
                }
                else -> {
                    _message.emit("Create account failed !")
                    Log.d("SignUpViewModel", "onSignUp: Create account failed !")

                }
            }
        }

    }
}