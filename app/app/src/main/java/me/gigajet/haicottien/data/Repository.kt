package me.gigajet.haicottien.data

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder

class Repository(
    private val sp: SharedPreferences,
    private val api: ChangeApi,
) {
    private val gson = GsonBuilder().create()

    suspend fun getChanges(year: Int, month: Int): Pair<Changes, Changes> {
        try {
            val leftChanges = api.getLeftColAtMonth(year, month)
            val rightChanges = api.getRightColAtMonth(year, month)
            val leftJson = gson.toJson(leftChanges)
            val rightJson = gson.toJson(rightChanges)
            sp.edit()
                .putString(KEY_LEFT_CHANGES, leftJson)
                .putString(KEY_RIGHT_CHANGES, rightJson)
                .apply()
            return leftChanges to rightChanges
        } catch (e: Exception) {
            Log.e("hai-cotf-tild", "Repository.getChanges ${e.message}", e)
            val leftJson = sp.getString(KEY_LEFT_CHANGES, null)
            val rightJson = sp.getString(KEY_RIGHT_CHANGES, null)
            val leftChanges =
                leftJson?.let { gson.fromJson(it, Changes::class.java) } ?: Changes.EMPTY
            val rightChanges =
                rightJson?.let { gson.fromJson(it, Changes::class.java) } ?: Changes.EMPTY
            return leftChanges to rightChanges
        }
    }

    companion object {
        const val KEY_LEFT_CHANGES = "left_changes"
        const val KEY_RIGHT_CHANGES = "right_changes"
    }
}