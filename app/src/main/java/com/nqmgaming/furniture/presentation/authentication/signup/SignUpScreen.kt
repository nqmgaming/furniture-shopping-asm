package com.nqmgaming.furniture.presentation.authentication.signup

import android.widget.Toast
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.common.components.CustomTextField
import com.nqmgaming.furniture.common.components.LoadingDialog
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyLight
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.LineColor
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.WhiteText
import com.nqmgaming.furniture.ui.theme.merriweatherFont
import com.nqmgaming.furniture.ui.theme.nunitoSansBoldFont
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val focusManager = LocalFocusManager.current

    val name = viewModel.name.collectAsState(initial = "")
    val nameError by viewModel.nameError.collectAsState()

    val email = viewModel.email.collectAsState(initial = "")
    val emailError by viewModel.emailError.collectAsState()

    val password = viewModel.password.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()

    val confirmPassword = viewModel.confirmPassword.collectAsState()
    val confirmPasswordError by viewModel.confirmPasswordError.collectAsState()

    val navigateToAppScreen by viewModel.navigateToAppScreen.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    if (navigateToAppScreen) {
        navController.navigate(Screen.SplashScreen.route) {
            popUpTo(Screen.LoginScreen.route) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    var isPasswordVisualTransformation by remember {
        mutableStateOf(true)
    }
    var isConfirmPasswordVisualTransformation by remember {
        mutableStateOf(true)
    }
    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsState()


    if (isLoading) {
        LoadingDialog(message = message)
    }

    val errorDetails by viewModel.errorDetails.collectAsState()
    LaunchedEffect(errorDetails) {
        if (errorDetails.isNotBlank()) {
            Toast.makeText(context, errorDetails, Toast.LENGTH_SHORT).show()
        }
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
            text = stringResource(id = R.string.welcome).uppercase(),
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
                CustomTextField(
                    value = name.value,
                    onValueChange = { viewModel.onNameChange(it) },
                    placeholder = stringResource(id = R.string.name),
                    leadingIcon = Icons.Outlined.Person,
                    singleLine = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    unfocusedContainerColor = WhiteText,
                    focusedContainerColor = WhiteText,
                    errorDetail = nameError
                )

                CustomTextField(
                    value = email.value,
                    onValueChange = { viewModel.onEmailChange(it) },
                    placeholder = stringResource(id = R.string.email),
                    leadingIcon = Icons.Outlined.Email,
                    singleLine = true,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    unfocusedContainerColor = WhiteText,
                    focusedContainerColor = WhiteText,
                    errorDetail = emailError
                )

                CustomTextField(
                    value = password.value,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    placeholder = stringResource(id = R.string.password),
                    leadingIcon = Icons.Outlined.Lock,
                    singleLine = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    unfocusedContainerColor = WhiteText,
                    focusedContainerColor = WhiteText,
                    errorDetail = passwordError,
                    isPassword = isPasswordVisualTransformation,
                    onPasswordToggleClick = {
                        isPasswordVisualTransformation = !isPasswordVisualTransformation
                    }
                )

                CustomTextField(
                    value = confirmPassword.value,
                    onValueChange = { viewModel.onConfirmPasswordChange(it) },
                    placeholder = stringResource(id = R.string.confirm_password),
                    leadingIcon = Icons.Outlined.Lock,
                    singleLine = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    unfocusedContainerColor = WhiteText,
                    focusedContainerColor = WhiteText,
                    errorDetail = confirmPasswordError,
                    isPassword = isConfirmPasswordVisualTransformation,
                    onPasswordToggleClick = {
                        isConfirmPasswordVisualTransformation =
                            !isConfirmPasswordVisualTransformation
                    },
                    onDone = {
                        keyboardController?.hide().also {
                            focusManager.clearFocus()
                        }
                    }
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
                    onClick = {
                        viewModel.onSignUp().also {
                            keyboardController?.hide()
                        }
                    },
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
                        text = stringResource(id = R.string.sign_up),
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

                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = GreyText,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 0.5.sp,
                                    fontFamily = nunitoSansFont,
                                )
                            ) {
                                append(stringResource(id = R.string.already_have_an_account))
                            }
                            append(" ")
                            withStyle(
                                style = SpanStyle(
                                    color = BlackText,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.5.sp,
                                    fontFamily = nunitoSansBoldFont,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(stringResource(id = R.string.log_in).uppercase())
                            }

                        }
                    )
                }
            }

        }

    }
}
