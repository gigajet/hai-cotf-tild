package me.gigajet.haicottien.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.gigajet.haicottien.data.Change

@Composable
fun ChangeDetail(
    change: Change,
    modifier: Modifier = Modifier,
    isLeft: Boolean = true,
) {
    val side = if (isLeft) "(trái)" else "(phải)"
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${change.date} $side",
                modifier = Modifier,
                color = Color.Yellow,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = change.reason,
                modifier = Modifier,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}