package com.refridev.bankapp.data.network.datasource.recipient

import com.refridev.bankapp.data.model.response.RecipientListResponse
import com.refridev.bankapp.data.network.service.ApiServices
import javax.inject.Inject

class RecipientListDataSourceImpl @Inject constructor(private val recipientApiServices : ApiServices?) : RecipientListDataSource {

    override suspend fun getRecipientList(): List<RecipientListResponse> {
        return recipientApiServices?.getRecipientList() ?: listOf()
    }

}