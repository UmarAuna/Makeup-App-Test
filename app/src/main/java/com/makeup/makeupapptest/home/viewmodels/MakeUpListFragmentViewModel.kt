package com.makeup.makeupapptest.home.viewmodels

import android.content.Context
import androidx.lifecycle.*
import com.makeup.makeupapptest.R
import com.makeup.makeupapptest.home.fragments.MakeUpListFragmentArgs
import com.makeup.makeupapptest.home.models.ProductListItem
import com.makeup.makeupapptest.repository.local.MakeUpDAO
import com.makeup.makeupapptest.repository.repo.MakeUpRepository
import com.makeup.makeupapptest.util.NetworkManager
import com.makeup.makeupapptest.util.Resource
import com.makeup.makeupapptest.util.emit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MakeUpListFragmentViewModel : ViewModel(), KoinComponent {

    private val makeUpRepo: MakeUpRepository by inject()
    private val context: Context by inject()
    private val networkManager: NetworkManager by inject()
    private val makeUpDAO: MakeUpDAO by inject()

    private val _makeupList = MutableLiveData<Resource<List<ProductListItem>>>()
    val observeMakeupList
        get() = _makeupList.emit()

    private lateinit var args: MakeUpListFragmentArgs

    val observeSortedData
        get() = Transformations.map(makeUpDAO.getSortedData(args.sort?.productType ?: "")) {
            it
        }

    val observedCachedMakeUp
        get() = Transformations.map(makeUpDAO.getAllData()) {
            it
        }

    fun sortedData(args: MakeUpListFragmentArgs) {
        this.args = args
    }

    fun init() {
        getMakeupList()
    }

    private fun getMakeupList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _makeupList.postValue(Resource.loading(null))
                if (networkManager.isConnected(context)) {
                    makeUpRepo.getMakeUp().let {
                        if (it.isSuccessful) {
                            _makeupList.postValue(Resource.success(it.body()))
                        } else {
                            _makeupList.postValue(Resource.error(it.message().toString(), null))
                        }
                    }
                } else {
                    _makeupList.postValue(Resource.error(context.getString(R.string.internet_not_available), null))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ProductListItem>>> {
        return _makeupList
    }
}
