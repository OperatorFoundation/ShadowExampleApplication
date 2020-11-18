package org.operatorfoundation.shadowexampleapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowConfig
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowSocket
import kotlin.concurrent.thread

@ExperimentalUnsignedTypes
class SocketViewModel : ViewModel()
{
    private val config = ShadowConfig("1234", "AES-128-GCM")
    private val httpRequest  = "GET / HTTP/1.0\r\n\r\n"
    private val textBytes = httpRequest.toByteArray()
    private val buffer = ByteArray(2)

    init {
        viewModelScope

        thread {
            // Create the socket
            val shadowSocket = ShadowSocket(config, "159.203.158.90", 2346)

            // Send a request to the server
            shadowSocket.outputStream.write(textBytes)
            shadowSocket.outputStream.flush()

            // Read the data
            val bytesRead = shadowSocket.inputStream.read(buffer)
            if (bytesRead !=0) {
                println("Read successful!")
                Log.i("Read successful!","Read Successful!")
            }

            // Closes the socket
            shadowSocket.close()

        }
    }

}