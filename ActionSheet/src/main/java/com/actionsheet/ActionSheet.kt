package com.actionsheet

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog


object ActionSheet {


    fun showActionSheet(
        mActivity: Activity,
        menuList: List<MenuList>,
        menuItemClickListener: MenuClickListener,
        closeButtonText: String = "Close",
        closeButtonTextColor: String = "#FF0000",
    ) {
        try {
            val sheetView: View = mActivity.layoutInflater
                .inflate(R.layout.layout_action_sheet, null)
            val mBottomDialogNotificationAction = BottomSheetDialog(mActivity)
            mBottomDialogNotificationAction.setContentView(sheetView)
            mBottomDialogNotificationAction.show()

            // Remove default white color background
            val bottomSheet =
                mBottomDialogNotificationAction.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            bottomSheet!!.background = null


            val recyclerview = sheetView.findViewById<RecyclerView>(R.id.recyclerview)
            val closeView = sheetView.findViewById<LinearLayout>(R.id.llClose)
            val closeText = sheetView.findViewById<TextView>(R.id.txtClose)
            closeText.setText(closeButtonText)
            val color: Int = Color.parseColor(closeButtonTextColor)
            closeText.setTextColor(color)

            // this creates a vertical layout Manager
            val layoutManager = LinearLayoutManager(mActivity)
            recyclerview.layoutManager = layoutManager

            val dividerItemDecoration = DividerItemDecoration(
                recyclerview.context,
                layoutManager.orientation
            )
            recyclerview.addItemDecoration(dividerItemDecoration)


            // This will pass the ArrayList to our Adapter
            val adapter = MenuAdapter(menuList, object : MenuMainClickListener {
                override fun menuItemClicked(index: Int) {
                    mBottomDialogNotificationAction.dismiss()
                    menuItemClickListener.menuItemClicked(index)
                }
            })

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter

            closeView.setOnClickListener {
                mBottomDialogNotificationAction.dismiss()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}