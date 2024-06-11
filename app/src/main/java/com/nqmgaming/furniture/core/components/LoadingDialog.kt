package com.nqmgaming.furniture.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(
    message: String? = null
){
    // Define the dialog with specific dismiss properties
    Dialog(
        onDismissRequest = { /* Never will happen, because of the specific properties */ },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        // Define the dialog surface
        Surface(
            shape = RoundedCornerShape(16.dp) // Specify any shape you like
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                // Space between the indicator and the text
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                // Add some space out of the edges
                modifier = Modifier.padding(16.dp)
            ) {
                // Display circular progress indicator
                CircularProgressIndicator()

                // Display the text if it's not null and not blank
                if (!message.isNullOrBlank()) {
                    AnimateDottedText(
                        text = message,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}