package com.nqmgaming.furniture.presentation.main.checkout_success

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.PrimaryColor
import com.nqmgaming.furniture.core.theme.WhiteText
import com.nqmgaming.furniture.core.theme.merriweatherFont
import com.nqmgaming.furniture.core.theme.nunitoSansBoldFont
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@Composable
fun CheckoutSuccessScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = stringResource(id = R.string.success).uppercase(),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = merriweatherFont,
                color = BlackText
            )
        )
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_furnite),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_checkmark_circle_fill),
                contentDescription = stringResource(id = R.string.app_name)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.your_order_will_be_delivered_soon),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = nunitoSansFont,
                color = BlackText
            ),
            textAlign = TextAlign.Center
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryColor
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.track_your_order).uppercase(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = WhiteText
                    )
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    width = 1.dp,
                    color = PrimaryColor,
                    shape = RoundedCornerShape(10.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = WhiteText
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.HomeScreen.route){
                            popUpTo(Screen.MainScreen.route){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.back_to_home).uppercase(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = BlackText
                    )
                )
            }
        }
    }

}
