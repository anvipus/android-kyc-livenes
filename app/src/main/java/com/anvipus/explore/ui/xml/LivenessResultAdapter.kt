package com.anvipus.explore.ui.xml

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anvipus.core.utils.model.LivenessResult
import com.anvipus.core.view.SelfieWithKtpActivity
import com.anvipus.explore.databinding.ItemLiveDetectionResultBinding

class LivenessResultAdapter(val livenessResult: LivenessResult): RecyclerView.Adapter<LivenessResultAdapter.DetectionResultViewHolder>(){
    inner class DetectionResultViewHolder(val binding: ItemLiveDetectionResultBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetectionResultViewHolder {
        return DetectionResultViewHolder(
            ItemLiveDetectionResultBinding
                .inflate(LayoutInflater.from(parent.context),
                    parent,
                    false
                ))
    }

    override fun onBindViewHolder(holder: DetectionResultViewHolder, position: Int) {
        livenessResult.detectionResult?.get(position)?.let { item ->
            with(holder.binding){
                tvTitle.text = item.detectionMode.name
                tvTime.text = item.timeMilis?.toString()
                item.image?.let {
                    livenessResult.getBitmap(
                        holder.itemView.context,
                        item.detectionMode,
                        onError = { message, errorType ->
                            Log.e(SelfieWithKtpActivity.TAG, "Message: $message, Type: $errorType")
                        })
                }?.let {
                    ivResult.setImageBitmap(it)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return livenessResult.detectionResult?.size?: 0
    }
}