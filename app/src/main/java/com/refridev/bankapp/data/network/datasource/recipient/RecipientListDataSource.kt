package com.refridev.bankapp.data.network.datasource.recipient

import com.refridev.bankapp.data.model.response.RecipientListResponse

interface RecipientListDataSource {

    suspend fun getRecipientList(): List<RecipientListResponse>

}