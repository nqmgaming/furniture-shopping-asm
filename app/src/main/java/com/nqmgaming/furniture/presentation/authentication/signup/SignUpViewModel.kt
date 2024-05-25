package com.nqmgaming.furniture.presentation.authentication.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.usecase.user.CreateUserUseCase
import com.nqmgaming.furniture.domain.usecase.user.GetUserInfoUseCase
import com.nqmgaming.furniture.domain.usecase.user.SignUpUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    application: Application
) : AndroidViewModel(application) {
    val navigateToAppScreen = MutableStateFlow(false)

    private val _name = MutableStateFlow<String>("")
    val name: Flow<String> = _name

    private val _nameError = MutableStateFlow("")
    val nameError = _nameError

    private val _email = MutableStateFlow<String>("")
    val email: Flow<String> = _email

    private val _emailError = MutableStateFlow("")
    val emailError = _emailError

    private val _password = MutableStateFlow("")
    val password = _password

    private val _passwordError = MutableStateFlow("")
    val passwordError = _passwordError

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword

    private val _confirmPasswordError = MutableStateFlow("")
    val confirmPasswordError = _confirmPasswordError

    private val _message = MutableStateFlow("")
    val message = _message

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    private val _errorDetails = MutableStateFlow("")
    val errorDetails = _errorDetails

    fun onNameChange(name: String) {
        if (name.isNotEmpty()) {
            _nameError.value = ""
        }
        _name.value = name
    }

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

    fun onConfirmPasswordChange(confirmPassword: String) {
        if (confirmPassword.isNotEmpty()) {
            _confirmPasswordError.value = ""
        }
        _confirmPassword.value = confirmPassword
    }

    fun onSignUp() {
        var isValid = true
        _name.value.validator().nonEmpty().noNumbers()
            .addErrorCallback {
                _nameError.value = it
                isValid = false
            }
            .addSuccessCallback {
                _nameError.value = ""
            }
            .check()

        _email.value.validator().nonEmpty().validEmail()
            .addErrorCallback {
                _emailError.value = it
                isValid = false
            }
            .addSuccessCallback {
                _emailError.value = ""
            }
            .check()

        _password.value.validator().nonEmpty().atleastOneNumber().atleastOneSpecialCharacters()
            .addErrorCallback {
                _passwordError.value = it
                isValid = false
            }
            .addSuccessCallback {
                _passwordError.value = ""
            }
            .check()

        _confirmPassword.value.validator().nonEmpty().atleastOneNumber()
            .atleastOneSpecialCharacters()
            .addErrorCallback {
                _confirmPasswordError.value = it
                isValid = false
            }
            .addSuccessCallback {
                _confirmPasswordError.value = ""
            }
            .check()

        if (_password.value != _confirmPassword.value) {
            _confirmPasswordError.value = "Password does not match"
            isValid = false
        }


        if (isValid) {
            _isLoading.value = true
            viewModelScope.launch {
                try {
                    val result = signUpUseCase.execute(
                        SignUpUseCase.Input(
                            email = _email.value.trim(),
                            password = _password.value,
                            name = _name.value.trim()
                        )
                    )
                    when (result) {
                        is SignUpUseCase.Output.Success -> {
                            val user = createUserUseCase.execute(
                                CreateUserUseCase.Input(
                                    email = _email.value.trim(),
                                    name = _name.value.trim()
                                )
                            )
                            when (user) {
                                is CreateUserUseCase.Output.Success -> {
                                    val userInfo = getUserInfoUseCase.execute(
                                        GetUserInfoUseCase.Input(
                                            email = _email.value.trim()
                                        )
                                    )
                                   when (userInfo) {
                                            is GetUserInfoUseCase.Output.Success -> {
                                                SharedPrefUtils.saveUserDetails(
                                                    getApplication(),
                                                    userId = userInfo.user.userId,
                                                    email = userInfo.user.email,
                                                    name = userInfo.user.name
                                                )
                                                navigateToAppScreen.emit(true)
                                                _isLoading.emit(false)
                                            }
                                            GetUserInfoUseCase.Output.Failure -> {
                                                _message.emit("Create account failed !")
                                                _errorDetails.emit("Email or password is incorrecttt")
                                                _isLoading.emit(false)
                                                Log.d("SignUpViewModel", "onSignUp: Create account failed !")
                                            }
                                        }
                                }
                                is CreateUserUseCase.Output.Error -> {
                                    _message.emit("Create account failed !")
                                    _errorDetails.emit("Email or password is incorrecttt")
                                    _isLoading.emit(false)
                                    Log.d("SignUpViewModel", "onSignUp: Create account failed !")
                                }
                            }
                        }

                        else -> {
                            _message.emit("Create account failed !")
                            // Add error details here
                            _errorDetails.emit("Email or password is incorrecttt")
                            _isLoading.emit(false)
                            Log.d("SignUpViewModel", "onSignUp: Create account failed !")
                        }
                    }
                } catch (e: Exception) {
                    _isLoading.emit(false)
                    // Handle the exception here
                    _message.emit("An error occurred: ${e.message}")
                    when {
                        e.message?.contains("duplicate key value violates unique constraint") == true -> {
                            _message.emit("Email has already been registered")
                            _errorDetails.emit("Email has already been registered")
                        }

                        e.message?.contains("Supabase rate limit") == true -> {
                            _message.emit("Server error: rate limit exceeded")
                            _errorDetails.emit(e.message ?: "Unknown error")
                        }

                        else -> {
                            _message.emit("An unknown error occurred")
                            _errorDetails.emit(e.message ?: "Unknown error")
                        }
                    }
                    Log.e("SignUpViewModel", "onSignUp: Exception", e)
                }
            }
        }
    }
}