package com.fullstack.catawiki.api

import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("message")
    var message: String = ""

    constructor(message: String) {
        this.message = message
    }
}