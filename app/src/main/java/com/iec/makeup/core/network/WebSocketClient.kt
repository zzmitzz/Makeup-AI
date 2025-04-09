package com.iec.makeup.core.network

import android.util.Log
import com.iec.makeup.core.utils.Constants.WEB_SOCKET_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class WebSocketClient @Inject constructor() {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(
                READ_TIMEOUT,
                TimeUnit.MILLISECONDS
            ) // Important for long-lived connections
            .pingInterval(Duration.ofSeconds(PING_PONG_INTERVAL))
            .build();
    }

    private var _webSocket: WebSocket? = null

    private val webSocketListener by lazy {
        WebSocketListenerImpl()
    }

    inner class WebSocketListenerImpl : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
            super.onOpen(webSocket, response)
            // Handle connection open
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            // Handle incoming messages
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            // Handle closing the connection
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            // Handle closed connection
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
            super.onFailure(webSocket, t, response)
            // Handle failure
            _webSocket = null
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            // Handle binary messages
        }

    }


    fun startWebSocket(endpoint: String) {
        val request = Request.Builder()
            .url(endpoint)
            .build()
        try {
            _webSocket = client.newWebSocket(
                request, webSocketListener
            )
        } catch (e: Exception) {
            Log.e("WebSocket", "Error starting WebSocket", e)
        }
    }

    fun closeWebSocket() {
        if (_webSocket != null) {
            try {
                _webSocket?.close(1000, "Goodbye!")
                Log.d("WebSocket", "WebSocket closed")
            } catch (e: Exception) {
                Log.e("WebSocket", "Error closing WebSocket", e)
            }
        }
    }

    companion object {
        const val READ_TIMEOUT = 10000L
        const val PING_PONG_INTERVAL = 5L
    }
}