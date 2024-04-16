package it.pierosilvestri.end_to_end_uitesting.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UIText {
    data class DynamicString(val text: String): UIText()

    object Empty : UIText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UIText()

    fun asString(context: Context?): String {
        return when(this) {
            is Empty -> ""
            is DynamicString -> text
            is StringResource -> context?.getString(resId).orEmpty()
        }
    }
}