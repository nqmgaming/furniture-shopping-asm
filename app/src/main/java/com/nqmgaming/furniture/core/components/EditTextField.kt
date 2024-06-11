package com.nqmgaming.furniture.core.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    singleLine: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    unfocusedContainerColor: Color,
    focusedContainerColor: Color,
    isPassword: Boolean? = null,
    onPasswordToggleClick: (() -> Unit)? = null,
    errorDetail: String? = null,
    onDone: () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder, style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    letterSpacing = 0.5.sp,
                    fontFamily = nunitoSansFont
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon, contentDescription = placeholder
            )
        },
        visualTransformation = if (isPassword == true) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword != null) {
                var showPassword = remember { mutableStateOf(isPassword) }
                IconButton(onClick = {
                    showPassword.value = !showPassword.value
                    onPasswordToggleClick?.invoke()
                    Log.d("CustomTextField", "showPassword: ${showPassword.value}")
                }) {
                    Icon(
                        painter = painterResource(id = if (showPassword.value) R.drawable.ic_hide else R.drawable.ic_show),
                        contentDescription = stringResource(
                            id = if (showPassword.value) R.string.hide_password else R.string.show_password
                        )
                    )
                }
            }
        },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,

            ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = unfocusedContainerColor,
            unfocusedIndicatorColor = GreyText,
            focusedIndicatorColor = BlackText,
            unfocusedTrailingIconColor = GreyText,
            focusedTrailingIconColor = BlackText,
            focusedContainerColor = focusedContainerColor,
            errorContainerColor = unfocusedContainerColor,
            errorTextColor = Color.Red,
            errorIndicatorColor = Color.Red,
            errorLeadingIconColor = Color.Red,
            errorTrailingIconColor = Color.Red,
            errorPlaceholderColor = Color.Red
        ),
        isError = !errorDetail.isNullOrBlank(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        supportingText = {
            if (!errorDetail.isNullOrBlank()) {
                Text(
                    text = errorDetail,
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        fontFamily = nunitoSansFont
                    )
                )
            }
        }
    )
}