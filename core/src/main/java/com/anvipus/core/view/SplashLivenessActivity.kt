package com.anvipus.core.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.anvipus.core.R
import com.anvipus.core.utils.Constants.Companion.EXTRA_RESULT
import com.anvipus.core.utils.model.LivenessResult

class SplashLivenessActivity : AppCompatActivity() {
    private val cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        if (isGranted)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, LivenessDetectionActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
                })
                finish()
            },2000)
        else {
            val result = LivenessResult(false, "Camera Permission Denied")
            setResult(RESULT_OK,Intent().putExtra(EXTRA_RESULT,result))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_liveness)
        cameraPermission.launch(Manifest.permission.CAMERA)
    }
}