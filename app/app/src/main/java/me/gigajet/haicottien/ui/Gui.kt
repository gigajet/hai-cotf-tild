package me.gigajet.haicottien.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import me.gigajet.haicottien.data.Changes

/* This function is not responsible for CUTting the changes */
@Composable
fun Gui(
    leftChanges: Changes,
    rightChanges: Changes,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
) {
    val (leftSelectedIndex, setLeftSelectedIndex) = remember { mutableIntStateOf(-1) }
    val (rightSelectedIndex, setRightSelectedIndex) = remember { mutableIntStateOf(-1) }
    val wdp = LocalConfiguration.current.screenWidthDp
    val hdp = LocalConfiguration.current.screenHeightDp
    val lc = leftChanges.changes.getOrNull(leftSelectedIndex)
    val rc = rightChanges.changes.getOrNull(rightSelectedIndex)
    val halfWdp = (wdp / 2).dp
    val detailHeightDp = (hdp * 35 / 100).dp
    Scaffold(
        bottomBar = {
            lc?.let { lc ->
                ChangeDetail(
                    change = lc,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(detailHeightDp),
                    isLeft = true,
                )
            } ?: rc?.let { rc ->
                ChangeDetail(
                    change = rc,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(detailHeightDp),
                    isLeft = false,
                )
            }
        },
        modifier = modifier,
        containerColor = Color.Black,
    ) { innerPadding ->
        Row(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.Black)
                .padding(innerPadding),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        ) {

            Column(
                modifier = modifier.width(halfWdp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                NumColumn(
                    changes = leftChanges,
                    modifier = Modifier.width(halfWdp),
                    selectedIndex = leftSelectedIndex,
                    onClickNumber = { i, c ->
                        Log.d("int3", "onClickLeft $i $c")
                        setLeftSelectedIndex(i)
                        setRightSelectedIndex(-1)
                    },
                    onClickTotal = {
                        onRefresh()
                    }
                )
            }
            Column(
                modifier = modifier.width(halfWdp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                NumColumn(
                    changes = rightChanges,
                    modifier = Modifier,
                    selectedIndex = rightSelectedIndex,
                    onClickNumber = { i, c ->
                        Log.d("int3", "onClickRight $i $c")
                        setLeftSelectedIndex(-1)
                        setRightSelectedIndex(i)
                    }, onClickTotal = {
                        onRefresh()
                    }
                )
            }
        }
    }

}