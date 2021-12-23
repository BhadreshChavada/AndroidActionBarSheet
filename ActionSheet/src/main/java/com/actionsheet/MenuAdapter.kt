package com.actionsheet

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
Created by Bhadresh on 22,December,2021
 */

public class MenuAdapter(
    private val mList: List<MenuList>,
    private val menuItemClickListener: MenuMainClickListener
) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.txtMenu)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.actionsheet_item_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.title

        holder.itemView.setOnClickListener {
            menuItemClickListener.menuItemClicked(position)
        }

        try {
            val color: Int = Color.parseColor(ItemsViewModel.color)
            holder.textView.setTextColor(color)
        } catch (e: Exception) {
            val color: Int = Color.parseColor("#1E82FF")
            holder.textView.setTextColor(color)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

}