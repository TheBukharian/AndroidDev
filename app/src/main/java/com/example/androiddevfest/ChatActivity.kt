package com.example.androiddevfest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val adapterFaq = GroupAdapter<GroupieViewHolder>()
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        adapterFaq.add(ChatItem())
        chatRecycler.adapter = adapterFaq



    }

    fun sendAnswer(){
        // to do
    }
}

class ChatItem: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {




    }

    override fun getLayout(): Int {
        return R.layout.chat_item
    }
}
class ChatData(val text: String, val uid: String){
    constructor():this("", "")

}