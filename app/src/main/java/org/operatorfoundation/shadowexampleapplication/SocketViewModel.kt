package org.operatorfoundation.shadowexampleapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowConfig
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowSocket
import org.operatorfoundation.shapeshifter.shadow.kotlin.readNBytes
import java.net.ServerSocket
import kotlin.concurrent.thread



@ExperimentalUnsignedTypes
class SocketViewModel(text: String) : ViewModel() {
    init {
        viewModelScope
//        thread {
//            val testServer = ServerSocket(3333)
//            val socket = testServer.accept()
//            readNBytes(socket.inputStream, 2)
//            socket.outputStream.write("Yo".toByteArray())
//            println("testServer started up")
//        }

        thread {
            val password = "1234"
            val config = ShadowConfig(password, "AES-128-GCM")
            val shadowSocket = ShadowSocket(config, "159.203.158.90", 2345)
            println("Config init successful!")
            val server = ServerSocket(2345)
            server.accept()
            val httpResponse  = "GET / HTTP/1.0\r\n\r\n"
            shadowSocket.outputStream.write(httpResponse.toByteArray())
            shadowSocket.outputStream.flush()
            val textBytes = text.toByteArray()
            val textLength = textBytes.size.toByte()
            val lengthByteArray = byteArrayOf(textLength)
            shadowSocket.outputStream.write(lengthByteArray)
            shadowSocket.outputStream.write(textBytes)
            shadowSocket.outputStream.flush()
            println("Write successful")
            val receiveTextLengthBytes = readNBytes(shadowSocket.inputStream, 1)
            val inputLength = receiveTextLengthBytes[0].toInt()
            val buffer = ByteArray(inputLength)
            shadowSocket.inputStream.read(buffer)
            if (String(buffer) == text) {
                println("read successful")
            }
            shadowSocket.close()
        }
    }
}