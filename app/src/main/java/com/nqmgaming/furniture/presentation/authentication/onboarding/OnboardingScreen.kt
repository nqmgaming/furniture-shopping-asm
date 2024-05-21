package com.nqmgaming.furniture.presentation.authentication.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.GreyTileText
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.WhiteText
import com.nqmgaming.furniture.ui.theme.gelasioFont
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun OnboardingScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.boarding_bg),
            contentDescription = "Boarding Background Image",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 130.dp)
        ) {
            Text(
                text = stringResource(id = R.string.make_your).uppercase(),
                style = TextStyle(
                    color = GreyTileText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 30.sp,
                    letterSpacing = 2.sp,
                    fontFamily = gelasioFont
                )
            )
            Text(
                text = stringResource(id = R.string.home_beautiful).uppercase(),
                style = TextStyle(
                    color = BlackText,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 30.sp,
                    letterSpacing = 2.sp,
                    fontFamily = gelasioFont
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = stringResource(id = R.string.intro),
                style = TextStyle(
                    color = GreyText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 35.sp,
                    letterSpacing = 0.5.sp,
                    fontFamily = nunitoSansFont,
                    textAlign = TextAlign.Justify
                ),
                modifier = Modifier.padding(top = 25.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(54.dp),
                    shape = RoundedCornerShape(10.dp),
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
                        text = stringResource(id = R.string.get_started).uppercase(),
                        style = TextStyle(
                            color = WhiteText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 22.sp,
                            letterSpacing = 0.5.sp,
                            fontFamily = gelasioFont,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}
