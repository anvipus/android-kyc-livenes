package com.anvipus.core.utils

import android.content.Context
import android.content.Intent
import com.anvipus.core.utils.Constants.Companion.EXTRA_RESULT
import com.anvipus.core.utils.analyzer.DetectionMode
import com.anvipus.core.utils.model.LivenessResult
import com.anvipus.core.utils.model.SelfieWithKtpResult
import com.anvipus.core.view.SplashLivenessActivity

object Identifier {
    private var attempt = 0
    internal var lowMemoryThreshold: Int? = null

    internal var detectionMode = listOf(
        DetectionMode.HOLD_STILL,
        DetectionMode.OPEN_MOUTH,
        DetectionMode.BLINK,
        DetectionMode.SHAKE_HEAD,
        DetectionMode.SMILE
    )

    @JvmStatic
    fun setDetectionModeSequence(shuffle: Boolean, detectionMode: List<DetectionMode>){
        Identifier.detectionMode = detectionMode.run {
            if (shuffle) shuffled() else this
        }
    }

    @JvmStatic
    fun setLowMemoryThreshold(threshold: Int){
        lowMemoryThreshold = threshold
    }

    @JvmStatic
    fun getLivenessIntent(context: Context): Intent {
        attempt++
        return Intent(context, SplashLivenessActivity::class.java)
    }

    @JvmStatic
    fun getLivenessResult(intent: Intent?) =
        intent?.getParcelableExtra<LivenessResult>(EXTRA_RESULT)?.apply {
            attempt = Identifier.attempt
            if(isSuccess) Identifier.attempt = 0
        }

    @JvmStatic
    fun getSelfieResult(intent: Intent?) =
        intent?.getParcelableExtra(EXTRA_RESULT) as SelfieWithKtpResult?
}