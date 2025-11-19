package com.anvipus.core.utils.analyzer

import com.anvipus.core.utils.model.LivenessResult

interface LivenessDetectionListener {
    fun onFaceStatusChanged(faceStatus: FaceStatus)
    fun onStartDetection(detectionMode: DetectionMode)
    fun onLiveDetectionSuccess(livenessResult: LivenessResult)
    fun onLiveDetectionFailure(exception: Exception)
}