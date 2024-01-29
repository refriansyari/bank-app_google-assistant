package com.refridev.bankapp.data.model.response

import com.google.gson.annotations.SerializedName

data class RecipientListResponse (

    @SerializedName("name")
    var recipientName: String? = null,

    @SerializedName("account")
    var accountNumber: String? = null,

)