package com.anvipus.core.utils.model

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import com.anvipus.core.common.ResultErrorType
import com.anvipus.core.utils.BitmapUtils
import kotlinx.parcelize.Parcelize
import kotlin.collections.forEach

@Parcelize
data class SelfieWithKtpResult(
    val isSuccess: Boolean,
    val errorMessage: String?,
    val errorType: ResultErrorType?,
    val imageUri: Uri,
    val detailFacesUri: List<Uri>
): Parcelable {
    fun getBitmap(context: Context, onError: (String, ResultErrorType) -> Unit): Bitmap? {
        return BitmapUtils.getBitmapFromContentUri(
            context.contentResolver,
            imageUri,
            onError = onError
        )
    }

    fun getListFaceBitmap(
        context: Context,
        onError: (String, ResultErrorType) -> Unit
    ): List<Bitmap> {
        val result = mutableListOf<Bitmap>()
        detailFacesUri.forEach {
            BitmapUtils.getBitmapFromContentUri(context.contentResolver, it, onError = onError)
                ?.let { bitmap ->
                    result.add(bitmap)
                }
        }
        return result
    }
}