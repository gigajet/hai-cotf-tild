package me.gigajet.haicottien.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import me.gigajet.haicottien.data.Changes

/* This function is not responsible for CUTting the changes */
@Composable
fun NumColumn(
    changes: Changes,
    modifier: Modifier = Modifier,
    rowsPerColumn: Int = 12,
    selectedIndex: Int = -1, /* Specify the index for highlighting */
    onClickNumber: (newSelectedIndex: Int, change: Change) -> Unit = { _, _ -> },
    onClickTotal: () -> Unit = {},
) {
    Column(
        modifier = modifier.background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val tot = changes.total
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onClickTotal() }
        ) {
            Text(
                text = tot.toString(),
                modifier = Modifier,
                color = Color.Yellow,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
            )
        }
        val numCols = (changes.changes.size + rowsPerColumn - 1) / rowsPerColumn
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        ) {
            for (batch in 0 until numCols) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp)) {
                    for (j in 0 until rowsPerColumn) {
                        val i = batch * rowsPerColumn + j
                        if (i >= changes.changes.size) break
                        val c = changes.changes[i]
                        val color = if (i == selectedIndex) Color.Green else Color.Yellow
                        val fontWeight = if (i == selectedIndex) FontWeight.Bold else null
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onClickNumber(i, c)
                                }
                        ) {
                            Text(
                                text = c.amount.toString(),
                                modifier = Modifier,
                                color = color,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = fontWeight,
                            )
                        }
                    }
                }
            }
        }
    }
}