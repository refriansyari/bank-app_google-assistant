package com.refridev.bankapp.ui.recipient

import androidx.lifecycle.LiveData
import com.refridev.bankapp.base.BaseContract
import com.refridev.bankapp.base.Resource
import com.refridev.bankapp.data.model.response.RecipientListResponse

interface RecipientListContract {

    interface View : BaseContract.BaseView {
        fun setRecycleView()
        fun setDataAdapter(data: List<RecipientListResponse>?)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRecipientList()
        fun getRecipientListLiveData(): LiveData<Resource<List<RecipientListResponse>>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getRecipientList(): List<RecipientListResponse>
    }
}