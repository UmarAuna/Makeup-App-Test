package com.makeup.makeupapptest.home.viewmodels

import androidx.lifecycle.ViewModel
import com.makeup.makeupapptest.util.EventLiveData
import com.makeup.makeupapptest.util.OneTimeLiveData
import com.makeup.makeupapptest.util.emit
import org.koin.core.component.KoinComponent

class MainActivityViewModel : ViewModel(), KoinComponent {

    private val _title = OneTimeLiveData<String>()
    val observeTitle
        get() = _title.emit()

    private val _showBackButton = OneTimeLiveData<Boolean>()
    val observeShowBackButton
        get() = _showBackButton.emit()

    private val _showSortButton = OneTimeLiveData<Boolean>()
    val observeShowSortButton
        get() = _showSortButton.emit()

    private val _sortButtonClicked = EventLiveData()
    val observeSortButtonCLicked
        get() = _sortButtonClicked.emit()

    fun setTitle(value: String) {
        _title.postValue(value)
    }

    fun showBackButton(value: Boolean) {
        _showBackButton.postValue(value)
    }

    fun showSortButton(value: Boolean) {
        _showSortButton.postValue(value)
    }

    fun sortButtonClicked() {
        _sortButtonClicked.post()
    }
}
