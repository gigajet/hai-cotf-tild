package me.gigajet.haicottien.data

import com.google.gson.annotations.SerializedName

data class Changes(
    @SerializedName("total") val total: Int,
    @SerializedName("changes") val changes: List<Change>
) {
    companion object {
        val EMPTY = Changes(total = 0, changes = listOf())
    }
}

data class Change(
    @SerializedName("amount") val amount: Int,
    @SerializedName("reason") val reason: String,
    @SerializedName("date") val date: String,
)
