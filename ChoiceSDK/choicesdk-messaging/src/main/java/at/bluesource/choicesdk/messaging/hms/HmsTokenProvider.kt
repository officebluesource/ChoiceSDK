package at.bluesource.choicesdk.messaging.hms

import android.content.Context
import android.content.pm.PackageManager
import at.bluesource.choicesdk.messaging.common.TokenProvider
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.push.HmsMessaging.DEFAULT_TOKEN_SCOPE

internal object HmsTokenProvider : TokenProvider {

    override suspend fun requestToken(context: Context): String {
        val appId = getAppId(context)
        return HmsInstanceId.getInstance(context).getToken(appId, DEFAULT_TOKEN_SCOPE)
    }

    override suspend fun deleteToken(context: Context) {
        val appId = getAppId(context)
        HmsInstanceId.getInstance(context).deleteToken(appId, DEFAULT_TOKEN_SCOPE)
    }

    private fun getAppId(context: Context): String {
        val app = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
        val bundle = app.metaData
        return bundle.getString("com.huawei.hms.client.appid")?.removePrefix("appid=") ?: ""
    }
}