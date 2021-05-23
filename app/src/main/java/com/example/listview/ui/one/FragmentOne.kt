package com.example.listview.ui.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.listview.databinding.FragmentOneBinding

class FragmentOne : Fragment(), OnItemClickListener {

    private lateinit var binding : FragmentOneBinding

    private val adapter : FragmentOneAdapter by lazy {
        FragmentOneAdapter(requireContext())
    }

    private lateinit var viewModel : OneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, OneViewModel.Factory()).
            get(OneViewModel::class.java)

        binding.listView.adapter = adapter
        adapter.addOnClickListener(this)

        viewModel.values.observe(viewLifecycleOwner) { values ->
            if (!values.isNullOrEmpty()){
                adapter.addList(values)
            }
        }

        viewModel.selectedItemsString.observe(viewLifecycleOwner) { string ->
            if(!string.isNullOrBlank()){
                binding.selectedItems.text = string
            }
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.itemHasBeenClicked(position)
    }
}