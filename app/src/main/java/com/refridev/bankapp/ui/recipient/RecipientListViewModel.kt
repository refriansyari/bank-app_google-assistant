package com.refridev.bankapp.ui.recipient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refridev.bankapp.base.Resource
import com.refridev.bankapp.data.model.response.RecipientListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RecipientListViewModel @Inject constructor(private val repository: RecipientListRepository) : ViewModel(),
RecipientListContract.ViewModel{

    private val recipientLiveData = MutableLiveData<Resource<List<RecipientListResponse>>>()


    override fun getRecipientList(){
        recipientLiveData.value = Resource.Loading()

        viewModelScope.launch (Dispatchers.IO){
            try{
                val response = repository.getRecipientList()
                viewModelScope.launch (Dispatchers.Main){
                    recipientLiveData.value = response.let{Resource.Success(it)}
                }
            } catch (e: Exception){
                Log.e("VMTest", "Exception: ${e.message}", e)
                viewModelScope.launch(Dispatchers.Main){
                    recipientLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getRecipientListLiveData(): LiveData<Resource<List<RecipientListResponse>>> = recipientLiveData

    override fun logResponse(msg: String?) {}

}