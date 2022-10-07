package com.makeup.makeupapptest.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makeup.makeupapptest.home.models.SortData
import com.makeup.makeupapptest.util.emit
import org.koin.core.component.KoinComponent

class SortingDataDialogViewModel : ViewModel(), KoinComponent {

    private val _sortData = MutableLiveData<List<SortData>>()
    val observeSortData
        get() = _sortData.emit()

    fun init() {
        getSortedListData()
    }

    private fun getSortedListData() {
        val data = getList()
        _sortData.postValue(data)
    }

    private fun getList(): List<SortData> {
        return listOf(
            SortData(1, "lipstick"),
            SortData(2, "foundation"),
            SortData(3, "eyeliner"),
            SortData(4, "eyeshadow"),
            SortData(5, "blush"),
            SortData(6, "bronzer"),
            SortData(7, "mascara"),
            SortData(8, "lip_liner"),
            SortData(9, "eyebrow"),
            SortData(10, "nail_polish")
        )
    }
}
