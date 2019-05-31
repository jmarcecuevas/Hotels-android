package com.marcecuevas.hotelsapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.ReviewDTO
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.fontVariable
import kotlinx.android.synthetic.main.fragment_hotel_detail.view.*
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsAdapter(val context: Context): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    var items: List<ReviewDTO> = emptyList()

    fun loadItems(newItems: List<ReviewDTO>){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        return CommentViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class CommentViewHolder(context: Context,itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.fullNameTextView.fontVariable(context,FontVariable.bold)
            itemView.goodComentTextView.fontVariable(context, FontVariable.bold)
            itemView.badComentTextView.fontVariable(context,FontVariable.bold)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: ReviewDTO){
            with(item){
                comments?.let {
                    itemView.fullNameTextView.text = user?.name

                    it.good?.let {
                        itemView.goodComentTextView.text = "\"$it\""
                    } ?: kotlin.run {
                        itemView.goodComentTextView.visibility = View.GONE
                    }

                    it.bad?.let {
                        itemView.badComentTextView.text = "\"$it\""
                    } ?: kotlin.run {
                        itemView.badComentTextView.visibility = View.GONE
                    }
                }
            }
        }
    }
}