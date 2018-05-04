package com.example.gamgam.applicationforyandex.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

//this package catch network connection errors
class ConnectivityInterceptor(private val mContext: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isOnline(mContext)) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}
//check network connection
object NetworkUtil{
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}

class NoConnectivityException : IOException() {
    override fun getLocalizedMessage(): String {
        return "No connectivity exception"
    }

}