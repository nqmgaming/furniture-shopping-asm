package com.nqmgaming.furniture.presentation.authentication.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.repository.AuthenticationRepository
import com.nqmgaming.furniture.domain.usecase.GetUserInfoUseCase
import com.nqmgaming.furniture.domain.usecase.LogInUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LogInUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    application: Application
) : AndroidViewModel(application) {
    val navigateToAppScreen = MutableStateFlow(false)
    private val _email = MutableStateFlow<String>("")
    val email = _email

    private val _emailError = MutableStateFlow("")
    val emailError = _emailError

    private val _password = MutableStateFlow<String>("")
    val password = _password

    private val _passwordError = MutableStateFlow("")
    val passwordError = _passwordError

    private val _message = MutableStateFlow("")
    val message = _message

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    private val _errorDetails = MutableStateFlow("")
    val errorDetails = _errorDetails

    fun onEmailChange(email: String) {
        if (email.isNotEmpty()) {
            _emailError.value = ""
        }
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        if (password.isNotEmpty()) {
            _passwordError.value = ""
        }
        _password.value = password
    }

    fun onLoginClick() {
        var isValid = true
        _email.value.validator().nonEmpty().validEmail()
            .addErrorCallback {
                _emailError.value = it
                isValid = false
            }
            .addSuccessCallback {
                _emailError.value = ""
            }
            .check()

        _password.value.validator().nonEmpty()
            .addErrorCallback {
                _passwordError.value = it
                isValid = false
            }
            .addSuccessCallback {
                _passwordError.value = ""
            }
            .check()

        if (isValid) {
            _isLoading.value = true
            Log.d("LoginViewModel", "Email: ${_email.value}, Password: ${_password.value}")
            try {
                Log.d("LoginViewModel", "Login Clicked")
                viewModelScope.launch {
                    _isLoading.emit(true)
                    Log.d("LoginViewModel", "Is Loading: ${_isLoading.value}")
                    try {
                        performLogin()
                    } catch (e: Exception) {
                        _isLoading.emit(false)
                        Log.d("LoginViewModel", "Error: ${e.message}")
                        _errorDetails.value = e.message.toString()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorDetails.value = e.message.toString()
            } finally {
                Log.d("LoginViewModel", "Finally")
                _isLoading.value = false
                Log.d("LoginViewModel", "Is Loading: ${_isLoading.value}")
            }
        }
    }

    private suspend fun performLogin() {
        _isLoading.emit(false)
        try {
            val userInfo = getUserInfoUseCase.execute(
                GetUserInfoUseCase.Input(
                    _email.value
                )
            )

            when (userInfo) {
                is GetUserInfoUseCase.Output.Success -> {

                    // Login
                    val result = loginUseCase.execute(
                        LogInUseCase.Input(
                            _email.value,
                            _password.value
                        )
                    )
                    when (result) {
                        is LogInUseCase.Output.Success -> {
                            _isLoading.emit(false)
                            SharedPrefUtils.saveUserDetails(
                                getApplication(),
                                userId = userInfo.user.userId,
                                email = userInfo.user.email,
                                name = userInfo.user.name
                            )
                            _message.value = "Login Successful"
                            navigateToAppScreen.value = true
                            Log.d("LoginViewModel", "Login Successful")
                        }

                        is LogInUseCase.Output.Failure -> {
                            _isLoading.emit(false)
                            Log.d("LoginViewModel", "Login Failed")
                            _message.value = "Login Failed"
                            _errorDetails.value = "Email or password is incorrect"
                            _passwordError.value = "Password is incorrect"
                        }
                    }

                }

                is GetUserInfoUseCase.Output.Failure -> {
                    _isLoading.emit(false)
                    _message.value = "Email or password is incorrect"
                    _emailError.value = "Email is not registered"
                    _passwordError.value = "Password is incorrect"
                }
            }
        } catch (e: Exception) {
            _isLoading.emit(false)
            _errorDetails.value = e.message.toString()
        }
    }

}

