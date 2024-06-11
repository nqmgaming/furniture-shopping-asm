package com.nqmgaming.furniture.presentation.main.notification.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.domain.model.notification.Notification
import com.nqmgaming.furniture.core.theme.BackgroundCategory
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notification: Notification
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()

        ) {
            AsyncImage(
                model = notification.image,
                contentDescription = notification.title,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .clip(RoundedCornerShape(10.dp))
                ,
                contentScale = ContentScale.Crop,
                clipToBounds = true
            )
            Log.d("NotificationItem", "NotificationItem: ${notification.image}")
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = notification.title,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = nunitoSansFont,
                        color = BlackText
                    ),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = notification.message,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = nunitoSansFont,
                        color = GreyText
                    ),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "New",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = nunitoSansFont,
                        color = Color.Green.copy(
                            alpha = 0.8f
                        )
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(BackgroundCategory)
                .height(1.dp)
        ) {
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}
