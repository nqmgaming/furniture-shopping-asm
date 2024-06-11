package com.nqmgaming.furniture.presentation.main.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.R.drawable
import com.nqmgaming.furniture.core.components.AlertDialogComponent
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.main.profile.componets.ItemProfile
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyLight
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.PrimaryColor
import com.nqmgaming.furniture.core.theme.WhiteText
import com.nqmgaming.furniture.core.theme.gelasioFont
import com.nqmgaming.furniture.core.theme.nunitoSansFont
import com.nqmgaming.furniture.util.SharedPrefUtils

@Composable
fun ProfileScreen(navController: NavController = rememberNavController()) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = drawable.ic_search),
                    contentDescription = stringResource(
                        id = R.string.search
                    ),
                    modifier = Modifier.weight(1f)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(10f)
                        .padding(5.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.profile).uppercase(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 25.sp,
                            fontFamily = gelasioFont,
                            color = PrimaryColor
                        )
                    )
                }
                Icon(
                    painter = painterResource(id = drawable.ic_logout),
                    contentDescription = stringResource(
                        id = R.string.cart
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .clickable {
                            openAlertDialog.value = true
                        }
                )
            }

        }

        item {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.avatar_sample),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(4.dp, Color.Yellow),
                            CircleShape
                        ),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = "Bruno Pham", style = TextStyle(
                            fontFamily = nunitoSansFont,
                            color = BlackText,
                            fontSize = 20.sp,
                            lineHeight = 27.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "bruno203@gmail.com", style = TextStyle(
                            fontFamily = nunitoSansFont,
                            color = BlackText,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }

            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            ItemProfile(
                title = "My Orders",
                subtitle = "Check your orders",
                onClick = {
                    navController.navigate(Screen.OrderScreen.route)
                }
            )
        }
        item {
            ItemProfile(
                title = "Shipping Addresses",
                subtitle = "Manage your shipping addresses",
                onClick = { }
            )
        }
        item {
            ItemProfile(
                title = "Payment Methods",
                subtitle = "Manage your payment methods",
                onClick = { }
            )
        }
        item {
            ItemProfile(
                title = "My reviews",
                subtitle = "Check your reviews",
                onClick = { }
            )
        }
        item {
            ItemProfile(
                title = "Settings",
                subtitle = "Notification, Password, FAQ, Contact",
                onClick = { }
            )
        }
    }

    if (openAlertDialog.value) {
        AlertDialogComponent(
            onDismissRequest = {
                openAlertDialog.value = false
            },
            onConfirmation = {
                openAlertDialog.value = false
                SharedPrefUtils.clear(context)
                navController.navigate(Screen.OnboardingScreen.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            dialogTitle = R.string.log_out,
            dialogText = R.string.logout_message,
            icon = Icons.AutoMirrored.Filled.Logout
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}