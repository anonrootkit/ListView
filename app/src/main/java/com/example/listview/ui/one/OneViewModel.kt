package com.example.listview.ui.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listview.ui.model.Data

class OneViewModel : ViewModel() {

    private val _values = MutableLiveData<List<Data>>()
    val values : LiveData<List<Data>>
        get() = _values

    private val _selectedItemsString = MutableLiveData<String>()
    val selectedItemsString : LiveData<String>
        get() = _selectedItemsString

    private val originalValues = ArrayList<Data>().apply {
        add(Data("Hello"))
        add(Data("World"))
        add(Data("Ankit"))
        add(Data("Abhishek"))
        add(Data("Ansh"))
        add(Data("World"))
        add(Data("Again"))
        add(Data("What"))
        add(Data("Sup"))
        add(Data("Qwerty"))
        add(Data("ArrayList"))
        add(Data("Hello"))
        add(Data("World"))
    }

    init {
        _values.value = originalValues
    }

    fun itemHasBeenClicked(position: Int) {
        val currentValues = _values.value ?: return
        val newList = ArrayList<Data>().apply { addAll(currentValues) }

        currentValues.mapIndexed { index, data ->
            if (position == index){
                newList[position].isSelected = !data.isSelected
            }
        }

        val selectedItemsList = ArrayList<String>()
        newList.map {
            if (it.isSelected)
                selectedItemsList.add(it.message)
        }

        _selectedItemsString.value = selectedItemsList.toString()

        _values.value = newList
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OneViewModel() as T
        }
    }
}