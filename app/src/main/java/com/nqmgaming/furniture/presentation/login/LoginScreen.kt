package com.nqmgaming.furniture.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyLight
import com.nqmgaming.furniture.ui.theme.LineColor
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.WhiteText
import com.nqmgaming.furniture.ui.theme.gelasioFont
import com.nqmgaming.furniture.ui.theme.merriweatherFont
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun LoginScreen() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isPasswordVisualTransformation by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .background(color = LineColor)
                    .height(1.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier.weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .background(color = LineColor)
                    .height(1.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.hello),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 45.sp,
                fontFamily = merriweatherFont,
                color = GreyLight
            )
        )
        Text(
            text = stringResource(id = R.string.welcome_back).uppercase(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 45.sp,
                letterSpacing = 0.5.sp,
                fontFamily = merriweatherFont,
                color = BlackText
            )
        )

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 50.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = WhiteText
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it.lowercase() },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.email),
                            style = TextStyle(
                                color = GreyLight,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                letterSpacing = 0.5.sp,
                                fontFamily = nunitoSansFont
                            )
                        )

                    },
                    leadingIcon = {
                        Image(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = stringResource(id = R.string.email)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = WhiteText,
                        focusedContainerColor = WhiteText,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password),
                            style = TextStyle(
                                color = GreyLight,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                letterSpacing = 0.5.sp,
                                fontFamily = nunitoSansFont
                            )
                        )

                    },
                    leadingIcon = {
                        Image(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = stringResource(id = R.string.password)
                        )
                    },
                    trailingIcon = {
                        if (isPasswordVisualTransformation) {
                            IconButton(onClick = {
                                isPasswordVisualTransformation = !isPasswordVisualTransformation
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_hide),
                                    contentDescription = stringResource(
                                        id = R.string.hide_password
                                    )
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isPasswordVisualTransformation = !isPasswordVisualTransformation
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_show),
                                    contentDescription = stringResource(
                                        id = R.string.show_password
                                    )
                                )
                            }
                        }

                    },
                    visualTransformation = if (isPasswordVisualTransformation) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = WhiteText,
                        focusedContainerColor = WhiteText,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )

                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = TextStyle(
                        color = GreyLight,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        letterSpacing = 0.5.sp,
                        fontFamily = nunitoSansFont,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(280.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,
                        contentColor = WhiteText
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.log_in),
                        style = TextStyle(
                            color = WhiteText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp,
                            letterSpacing = 0.5.sp,
                            fontFamily = nunitoSansFont,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(vertical = 20.dp)) {
                    Text(
                        text = stringResource(id = R.string.sign_up).uppercase(),
                        style = TextStyle(
                            color = BlackText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp,
                            letterSpacing = 0.5.sp,
                            fontFamily = nunitoSansFont,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}