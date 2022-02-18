package com.example.androidtest.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.androidtest.R

class DetailsDialogFragment(val title:String, val description:String): DialogFragment() {
    lateinit var descriptionTv:TextView
    lateinit var titleTv:TextView
    lateinit var closeBtn:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView:View = inflater.inflate(R.layout.fragment_dialog_details, container, false)
        descriptionTv = rootView.findViewById(R.id.detail_tv)
        titleTv = rootView.findViewById(R.id.title_tv)
        closeBtn = rootView.findViewById(R.id.close_btn)
        closeBtn.setOnClickListener {
            this.dismiss()
        }
        titleTv.setText(title)
        descriptionTv.setText(description)

        return rootView
    }
}