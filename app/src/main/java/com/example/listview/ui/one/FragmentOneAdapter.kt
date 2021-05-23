package com.example.listview.ui.one

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.listview.R
import com.example.listview.databinding.ListItemBinding
import com.example.listview.ui.model.Data

class FragmentOneAdapter(context: Context)
    : ArrayAdapter<Data>(context, 0 , ArrayList()) {

    private val dataList : ArrayList<Data> = ArrayList()
    private var onClickListener : OnItemClickListener? = null

    fun addList(list : List<Data>) {
        dataList.clear()
        dataList.addAll(list)
        this.notifyDataSetChanged()
    }

    fun addOnClickListener(clickListener: OnItemClickListener) {
        onClickListener = clickListener
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getView(position: Int, rootView: View?, parent: ViewGroup): View {
        val binding : ListItemBinding =
            if (rootView == null)
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            else
                DataBindingUtil.getBinding(rootView)!!

        val data : Data = dataList[position]

        if (data.isSelected){
            binding.root.setBackgroundColor(Color.LTGRAY)
        }else{
            binding.root.setBackgroundResource(0)
        }

        binding.root.setOnClickListener {
            if (onClickListener != null)
                onClickListener!!.onItemClick(position)
        }

        binding.message.text = data.message

        return binding.root
    }
}