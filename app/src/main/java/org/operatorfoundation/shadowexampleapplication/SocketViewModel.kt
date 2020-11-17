package org.operatorfoundation.shadowexampleapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowConfig
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowSocket
import org.operatorfoundation.shapeshifter.shadow.kotlin.readNBytes
import kotlin.concurrent.thread



@ExperimentalUnsignedTypes
class SocketViewModel(text: String) : ViewModel()
{
    val config = ShadowConfig("1234", "AES-128-GCM")
    val httpRequest  = "GET / HTTP/1.0\r\n\r\n"
    val textBytes = text.toByteArray()
    val textLength = textBytes.size.toByte()
    val lengthByteArray = byteArrayOf(textLength)


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
            val shadowSocket = ShadowSocket(config, "159.203.158.90", 2345)

            // Send a request to the server
            shadowSocket.outputStream.write(httpRequest.toByteArray())
            shadowSocket.outputStream.flush()

            val response = readNBytes(shadowSocket.inputStream, 1)
            val inputLength = response[0].toInt()
            val buffer = ByteArray(inputLength)


            println("Config init successful!")

            shadowSocket.outputStream.flush()
            shadowSocket.outputStream.write(lengthByteArray)
            shadowSocket.outputStream.write(textBytes)
            shadowSocket.outputStream.flush()
            println("Write successful")

            shadowSocket.inputStream.read(buffer)
            if (String(buffer) == text) {
                println("read successful")
            }
            shadowSocket.close()
        }
    }
}