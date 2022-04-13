package com.example.worklah.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.R
import com.example.worklah.data.Inbox

class InboxAdapter(
    private val context: Context?,
    private val dataset: MutableList<Inbox>
) : RecyclerView.Adapter<InboxAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.inbox_card, viewGroup, false)

        return ItemViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.messageImage.setImageResource(dataset[position].messageImage)
        viewHolder.messageTitle.text = dataset[position].messageTitle
        viewHolder.messageContent.text = dataset[position].messageContent
        viewHolder.messageDate.text = dataset[position].messageDate
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageImage: ImageView = view.findViewById(R.id.list_item_image)
        val messageTitle: TextView = view.findViewById(R.id.list_item_title)
        val messageContent: TextView = view.findViewById(R.id.list_item_content)
        val messageDate: TextView = view.findViewById(R.id.list_item_date)
    }
}