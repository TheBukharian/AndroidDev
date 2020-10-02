package com.example.androiddevfest.Options


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevfest.BuildConfig
import com.example.androiddevfest.ChatActivity
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.yalantis.phoenix.PullToRefreshView
import kotlinx.android.synthetic.main.activity_faq.*
import kotlinx.android.synthetic.main.question_item.view.*


class Faq : AppCompatActivity() {

    lateinit var saveData: SaveData
    lateinit var Question : String
    lateinit var Name : String

    val adapterFaq = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        saveData = SaveData(this)
        if (saveData.loadDarkModeState()){
            darkSet()
        }else{
            darkOff()
        }
        lightBtnfaq.setOnClickListener {
            if (!saveData.loadDarkModeState()) {
                saveData.setDarkNodeState(true)
                darkSet()
            }else{
                saveData.setDarkNodeState(false)
                darkOff()
            }
        }

        val mPullToRefreshView = findViewById<PullToRefreshView>(R.id.pull_to_refresh)
        mPullToRefreshView.setOnRefreshListener(PullToRefreshView.OnRefreshListener {
            mPullToRefreshView.postDelayed(Runnable { mPullToRefreshView.setRefreshing(false) },1000)
        })





        shareBtnfaq.setOnClickListener{
            try{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GDG APP by TheBukharian")
                var shareMessage = "\nLet`s try this GDG application:\n\n"
                shareMessage = shareMessage+ "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID+ "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share with "))
            }
            catch (e: Exception){
                Log.d("MainActivity", "Couldn` t load the web site")
            }
        }

        floatingActionButton.setOnClickListener {
            sendQuestionOperation()
        }



        fetchQuestions()
    }
    private fun darkSet(){
        setTheme(R.style.DarkTheme)
        lightBtnfaq.setImageResource(R.drawable.darklight)
        actBarfaq.setBackgroundResource(R.drawable.act_darkoption)
        faqBack.setBackgroundResource(R.drawable.gradient_dark)



    }
    private fun darkOff(){
        setTheme(R.style.AppTheme)
        lightBtnfaq.setImageResource(R.drawable.light)
        actBarfaq.setBackgroundColor(resources.getColor(R.color.LinearBackground))
        faqBack.setBackgroundResource(R.drawable.gradient_2)


    }
    private fun fetchQuestions() {

        val ref = FirebaseDatabase.getInstance().getReference("/questions")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val data = it.getValue(QuestionsData::class.java)

                    if (data != null) {
                        adapterFaq.add(Questions(data))
                        Log.d("question", "$it")

                    }
                }
                adapterFaq.setOnItemClickListener { item, view ->
                    val questionItem = item as Questions
                    val intent=Intent(view.context,ChatActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                questionsRecycler.adapter = adapterFaq
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("GOOOOOOOSSSS", "ERROR DATABASE")
            }
        })
    }
    private fun sendQuestionOperation(){
        val inflater = layoutInflater
        val inflate_view=inflater.inflate(R.layout.ask_dialog, null)

        val sendBtn =inflate_view.findViewById<Button>(R.id.sendBtn)
        val sendName= inflate_view.findViewById<EditText>(R.id.nameEditText)
        val sendQuestion=inflate_view.findViewById<EditText>(R.id.messageEdit)



            val alertDialog= AlertDialog.Builder(this)
            alertDialog.setView(inflate_view)
            alertDialog.setCancelable(true)

            val dialog=alertDialog.create()
            dialog.show()

            sendBtn.setOnClickListener {
                if(!TextUtils.isEmpty(sendName.text.toString()) && !TextUtils.isEmpty(sendQuestion.text.toString())){
                    Question = sendQuestion.text.toString()
                    Name = sendName.text.toString()
                        sendInfo()
                        dialog.hide()
                        adapterFaq.clear()
                        fetchQuestions()
                }else{
                    Toast.makeText(this, "Fill the lines above!", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun sendInfo() {
        var oorder = saveData.getCount()
        val ref = FirebaseDatabase.getInstance().getReference("/questions").push()
        val questionText = QuestionsData(Question, Name)
        saveData.setCount(++oorder)



        ref.setValue(questionText).addOnSuccessListener {
            Toast.makeText(this, "Question has been sent", Toast.LENGTH_LONG).show()

        }

    }
}

class Questions(val dataa: QuestionsData): Item<GroupieViewHolder>() {
    lateinit var saveData: SaveData


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        saveData = SaveData(viewHolder.itemView.context.applicationContext)


        viewHolder.itemView.faqTxt.text = dataa.question
        viewHolder.itemView.faqNum.text = saveData.getCount().toString()


    }

    override fun getLayout(): Int {
        return R.layout.question_item
    }
}

class QuestionsData(val question: String, val name: String){
    constructor():this("", "")

}