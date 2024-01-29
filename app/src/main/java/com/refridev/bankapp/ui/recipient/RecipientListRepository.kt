package com.refridev.bankapp.ui.recipient

import com.refridev.bankapp.data.model.response.RecipientListResponse
import com.refridev.bankapp.data.network.datasource.recipient.RecipientListDataSource
import javax.inject.Inject

class RecipientListRepository @Inject constructor (private val recipientListDataSource: RecipientListDataSource) : RecipientListContract.Repository {

    override suspend fun getRecipientList(): List<RecipientListResponse> {
        return recipientListDataSource.getRecipientList()
    }

    override fun logResponse(msg: String?) {}
}