package com.nqmgaming.furniture.util

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun TwiceBackHandler(
    enabled: Boolean = true,
    duration: Duration = 2.seconds,
    onFirstBack: () -> Unit,
    onBack: () -> Unit
) {
    val currentOnBack by rememberUpdatedState(onBack)
    val currentOnFirstBack by rememberUpdatedState(onFirstBack)
    var exit by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = exit) {
        if (exit) {
            delay(duration.inWholeMilliseconds)
            exit = false
        }
    }
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                if (exit) {
                    currentOnBack()
                } else {
                    exit = true
                    currentOnFirstBack()
                }
            }
        }
    }
    SideEffect {
        backCallback.isEnabled = enabled
    }
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}