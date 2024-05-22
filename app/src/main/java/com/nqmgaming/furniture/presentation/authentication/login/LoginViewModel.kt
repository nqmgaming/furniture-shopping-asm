package com.nqmgaming.furniture.presentation.authentication.login

import androidx.lifecycle.ViewModel
import com.nqmgaming.furniture.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): ViewModel(){

}