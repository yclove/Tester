package com.ycengine.tester.api

import android.content.res.AssetManager
import okhttp3.*
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader

class AssetFileInterceptor(private val assetManager: AssetManager) : Interceptor {

    companion object {
        private const val ASSET_PATH: String = "assets"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url().encodedPath()

        takeIf { chain.request().url().pathSegments()[0] == ASSET_PATH } ?: return chain.proceed(chain.request())

        val assetFile = chain.request().url().pathSegments()[1]
        val responseBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), readAssetFile("api/$assetFile.json"))

        Timber.e("host : ${chain.request().url().host()}, $path, api/$assetFile.json")

        return Response.Builder()
                .code(200)
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(responseBody)
                .build()
    }

    private fun readAssetFile(path: String) : String {
        val reader = BufferedReader(InputStreamReader(assetManager.open(path)))
        val builder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append(line)
            line = reader.readLine()
        }

        return builder.toString()
    }
}