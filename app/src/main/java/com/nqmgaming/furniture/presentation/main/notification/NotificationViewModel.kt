package com.nqmgaming.furniture.presentation.main.notification

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.model.notification.Notification
import com.nqmgaming.furniture.domain.repository.NotificationRepository
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    application: Application
) : AndroidViewModel(application) {
    private val userId = SharedPrefUtils.getInt(application, "userId")

    private val _notifications = MutableStateFlow(emptyList<Notification>())
    val notifications = _notifications

    init {
        getNotification()
    }

    private fun getNotification() {
        viewModelScope.launch {
            _notifications.value = notificationRepository.getNotifications(userId)
            Log.d("NotificationViewModel", "getNotification: ${_notifications.value}")
        }
    }
}