package com.refridev.bankapp.data.network.datasource.recipient

import com.refridev.bankapp.data.model.response.RecipientListResponse
import com.refridev.bankapp.data.network.service.ApiServices
import javax.inject.Inject

class RecipientListDataSourceImpl @Inject constructor(private val recipientApiServices : ApiServices?) : RecipientListDataSource {

    override suspend fun getRecipientList(): List<RecipientListResponse> {

        return listOf(
            RecipientListResponse("Aditya Putra (BRI)", "BRI 0892389207231"),
            RecipientListResponse("Aditya Putra (Mandiri)", "Mandiri 875957832929"),
            RecipientListResponse("Aditya Putra (BNI)", "BNI 84932043024"),
            // Add more items as needed
        )
//        return recipientApiServices?.getRecipientList() ?: listOf()
    }

}