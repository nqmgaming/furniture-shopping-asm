package com.nqmgaming.furniture.presentation.main.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.domain.model.notification.Notification
import com.nqmgaming.furniture.presentation.main.notification.components.NotificationItem
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.gelasioFont

@Composable
fun NotificationScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
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
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(
                        id = R.string.search
                    ),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.notifications).uppercase(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 25.sp,
                            fontFamily = gelasioFont,
                            color = PrimaryColor
                        )
                    )
                }
            }

        }

        repeat(10){
            item {
                NotificationItem(
                    notification = Notification(
                        userId = "1",
                        productId = "1",
                        title = "Title",
                        message = "Message",
                        image = " https://res.cloudinary.com/ddqearyoe/image/upload/v1716285544/furniture/wynjcugyplftedzguo7e.png"

                    )

                )
            }
        }
    }
}
