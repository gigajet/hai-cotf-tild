@file:Suppress("DEPRECATION")

package me.gigajet.haicottien.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.gigajet.haicottien.data.Change
import me.gigajet.haicottien.data.Changes

@Preview
@Composable
fun NumColumnTest() {
    val changes = Changes(
        total = 54,
        changes = listOf(
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
        )
    )
    NumColumn(changes = changes, selectedIndex = 2)
}

@Preview
@Composable
fun LongNumColumnTest() {
    NumColumn(changes = SampleData.longChanges, selectedIndex = 14)
}

@Preview
@Composable
fun GuiTest() {
    Gui(
        leftChanges = SampleData.longChanges,
        rightChanges = SampleData.longChanges,
    )
}

internal object SampleData {
    val longChanges = Changes(
        total = 1542,
        changes = listOf(
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 45,
                reason = "Go to Chinatown with A",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = -3,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
            Change(
                amount = 12,
                reason = "Làm Unicode cố định sự huyền bí",
                date = "T2 06/01/2025"
            ),
        )
    )
}