package com.example.worklah.oldActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worklah.R
import com.example.worklah.adapter.InboxAdapter
import com.example.worklah.data.Inbox

class InboxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)

        var newInboxList = mutableListOf<Inbox>()
        var earlierInboxList = mutableListOf<Inbox>()

        val recyclerView1 = findViewById<RecyclerView>(R.id.recycler_view_new)
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.adapter = InboxAdapter(this, newInboxList)
        recyclerView1.setHasFixedSize(true)

        val recyclerView2 = findViewById<RecyclerView>(R.id.recycler_view_earlier)
        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.adapter = InboxAdapter(this, earlierInboxList)
        recyclerView2.setHasFixedSize(true)


        //livedata
        /*
        val markAllAsRead = findViewById<TextView>(R.id.mark_all_as_read)
        markAllAsRead.setOnClickListener {
            newMessageList.clear()
        }
        */

    }
}