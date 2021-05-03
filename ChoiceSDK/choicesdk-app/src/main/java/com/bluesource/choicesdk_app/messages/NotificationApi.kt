package com.bluesource.choicesdk_app.messages

import retrofit2.http.POST
import retrofit2.http.Query


interface NotificationApi {

    @POST("/sendPushNotification")
    suspend fun triggerNotification(@Query("serviceType") serviceType: String, @Query("token") token: String): String

}