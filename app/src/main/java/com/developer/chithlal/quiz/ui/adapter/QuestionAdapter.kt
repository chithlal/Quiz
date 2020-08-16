package com.developer.chithlal.quiz.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.chithlal.quiz.R
import com.developer.chithlal.quiz.databinding.CardOptionBinding
import com.developer.chithlal.quiz.model.Option
import com.developer.chithlal.quiz.util.DefaultConfig

@Suppress("DEPRECATION")
class QuestionAdapter(
    val optionList: List<Option>,
    val qType: DefaultConfig.Companion.QUESTION_TYPE,
    val context: Context,
    val answer: Answer?
):
    RecyclerView.Adapter<QuestionAdapter.OptionViewHolder>(),View.OnTouchListener {
    private var scqSelection = -1
    private var mcqSet = HashSet<Int>()
    private var currentPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
       val binding = CardOptionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OptionViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return optionList.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {

        val option = optionList.get(position)
        val prevAnswer = answer?.answerList?.contains(position)
        if (qType == DefaultConfig.Companion.QUESTION_TYPE.MCQ) {

            if (mcqSet.contains(position) || (prevAnswer!=null && prevAnswer)) {
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
            }
            else{
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorLight))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorLight))
            }
        } else {
            if (scqSelection == position|| (prevAnswer!=null && prevAnswer)){
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
            }

            else{
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorLight))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorLight))
            }

        }
        holder.option.loadData(option.value, "text/html", "UTF-8")



        holder.mBiding.webViewWraper.setOnClickListener{

            updateView(holder,position)
            notifyDataSetChanged()
        }
    }

    private fun updateView(holder: OptionViewHolder,position: Int) {

        if(qType==DefaultConfig.Companion.QUESTION_TYPE.MCQ){
            if(mcqSet.contains(position)){
                mcqSet.remove(position)
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorLight))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorLight))
            }
            else {
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
                mcqSet.add(position)

            }

        }
        else{
            if(scqSelection == position)
            {
                scqSelection = -1
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorLight))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorLight))
            }
            else{
                holder.option.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
                holder.root.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
                scqSelection = position

            }

        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    class OptionViewHolder(val mBiding:CardOptionBinding): RecyclerView.ViewHolder(mBiding.root) {
        val root = mBiding.root
        val option = mBiding.webViewOption
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        v?.performClick()
        return true
    }

    // Create Answer object with current selection
    fun getCurrentAnswer():Answer?{
        val list= ArrayList<Int>()
        var newAnswer = answer
        if(qType == DefaultConfig.Companion.QUESTION_TYPE.MCQ){
            val it = mcqSet.iterator()

            while (it.hasNext()) {
                if(newAnswer == null)
                list.add(it.next())
                else {

                    if(!newAnswer.answerList.contains(it.next()))
                        {
                            newAnswer.answerList.add(it.next())
                        }
                }
            }
            if (newAnswer == null)
            newAnswer = Answer(-1,list)
        }
        else{
            if(newAnswer!= null){
                if (!newAnswer.answerList.contains(scqSelection))
                {
                    newAnswer.answerList.clear()
                    newAnswer.answerList.add(scqSelection)
                }
            }
            else {
                list.add(scqSelection)
                newAnswer=Answer(-1,list)
            }
        }
       return newAnswer
    }
}
class Answer(var qNo:Int,var answerList:ArrayList<Int>)