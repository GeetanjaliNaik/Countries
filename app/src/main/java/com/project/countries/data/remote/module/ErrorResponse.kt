package com.project.countries.data.remote.module

import com.google.gson.annotations.SerializedName
import java.util.ArrayList
/**
 * Created by Geeta on 04/02/19.
 */
data class ErrorResponse(
        @SerializedName("defaultUserMessage")
        var mDefaultUserMessage: String? = "",
        @SerializedName("developerMessage")
        var mDeveloperMessage: String? = "",
        @SerializedName("httpStatusCode")
        var mHttpStatusCode: String? = "",
        @SerializedName("userMessageGlobalisationCode")
        var mUserMessageGlobalisationCode: String? = "",
        @SerializedName("errors")
        var errors: List<Errors> = listOf()
) {
    data class Errors(
            @SerializedName("developerMessage")
            var developerMessage: String? = "",
            @SerializedName("defaultUserMessage")
            var defaultUserMessage: String? = "",
            @SerializedName("userMessageGlobalisationCode")
            var userMessageGlobalisationCode: String? = "",
            @SerializedName("parameterName")
            var parameterName: String? = ""
    )
}