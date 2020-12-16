package org.operatorfoundation.shadowexampleapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.libsodium.jni.SodiumConstants
import org.libsodium.jni.crypto.Random
import org.libsodium.jni.keys.KeyPair
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowConfig
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowSocket
import org.operatorfoundation.shapeshifter.shadow.kotlin.ShadowSocketFactory
import java.io.IOException
import java.security.NoSuchAlgorithmException

class
MainActivity : AppCompatActivity() {

    var httpRequest = "GET / HTTP/1.0\r\n\r\n"
    var textBytes = httpRequest.toByteArray()
    var buffer = ByteArray(1024)

    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test Libsodium
        val seed = Random().randomBytes(SodiumConstants.SECRETKEY_BYTES)
        val encryptionKeyPair = KeyPair(seed)
        Log.i("PUBLIC KEY:", encryptionKeyPair.publicKey.toString())

        chaChaTest.setOnClickListener {
            val outcome = findViewById<TextView>(R.id.outcome)
            val output = findViewById<TextView>(R.id.output)

            outcome.text = ""
            output.text = ""
            Thread {
                try {

                    //OKHTTP Stuff
                    val host = "159.203.158.90"
                    val port = 2345

                    val sConfig = ShadowConfig("1234", "CHACHA20-IETF-POLY1305")
                    val client: OkHttpClient = OkHttpClient.Builder()
                        .socketFactory(ShadowSocketFactory(sConfig, host, port)).build()

                    val request: Request = Request.Builder().url("https://foo.com").build()

                    try {
                        client.newCall(request).execute()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }




                    // Create the socket
                    val config = ShadowConfig("1234", "CHACHA20-IETF-POLY1305")
                    val shadowSocket = ShadowSocket(config, "159.203.158.90", 2345)

                    // Send a request to the server
                    shadowSocket.outputStream.write(textBytes)
                    shadowSocket.outputStream.flush()

                    // Read the data
                    val bytesRead: Int = shadowSocket.inputStream.read(buffer)
                    val result = ByteArray(bytesRead)
                    System.arraycopy(buffer, 0, result, 0, bytesRead)
                    println(String(result))

                    // Closes the socket
                    shadowSocket.close()
                    runOnUiThread {
                        if (bytesRead == 0) {
                            outcome.setText(R.string.Fail)
                            output.setText(R.string.Empty_String)
                        } else {
                            outcome.setText(R.string.Success)
                            output.text = String(result)
                        }
                    }
                } catch (e: IOException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                } catch (e: NoSuchAlgorithmException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                }
            }.start()
        }
        aesTest.setOnClickListener {
            val outcome = findViewById<TextView>(R.id.outcome)
            val output = findViewById<TextView>(R.id.output)

            outcome.text = ""
            output.text = ""
            Thread {
                try {
                    // Create the socket
                    val config = ShadowConfig("1234", "AES-128-GCM")
                    val shadowSocket = ShadowSocket(config, "159.203.158.90", 2346)

                    // Send a request to the server
                    shadowSocket.outputStream.write(textBytes)
                    shadowSocket.outputStream.flush()

                    // Read the data
                    val bytesRead: Int = shadowSocket.inputStream.read(buffer)
                    val result = ByteArray(bytesRead)
                    System.arraycopy(buffer, 0, result, 0, bytesRead)
                    println(String(result))

                    // Closes the socket
                    shadowSocket.close()
                    runOnUiThread {
                        if (bytesRead == 0) {
                            outcome.setText(R.string.Fail)
                            output.setText(R.string.Empty_String)
                        } else {
                            outcome.setText(R.string.Success)
                            output.text = String(result)
                        }
                    }
                } catch (e: IOException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                } catch (e: NoSuchAlgorithmException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                }
            }.start()
        }
        okhttpChaCha.setOnClickListener {
            val outcome = findViewById<TextView>(R.id.outcome)
            val output = findViewById<TextView>(R.id.output)

            outcome.text = ""
            output.text = ""
            Thread {
                try {

                    //OKHTTP Stuff
                    val host = "159.203.158.90"
                    val port = 2345

                    val sConfig = ShadowConfig("1234", "CHACHA20-IETF-POLY1305")
                    val client: OkHttpClient = OkHttpClient.Builder()
                        .socketFactory(ShadowSocketFactory(sConfig, host, port)).build()

                    val request: Request = Request.Builder().url("http://foo.com").build()

                    try {
                        client.newCall(request).execute()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                } catch (e: IOException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                } catch (e: NoSuchAlgorithmException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                }
            }.start()
        }
        okhttpAes.setOnClickListener {
            val outcome = findViewById<TextView>(R.id.outcome)
            val output = findViewById<TextView>(R.id.output)

            outcome.text = ""
            output.text = ""
            Thread {
                try {
                    val host = "159.203.158.90"
                    val port = 2346

                    val sConfig = ShadowConfig("1234", "AES-128-GCM")
                    val client: OkHttpClient = OkHttpClient.Builder()
                        .socketFactory(ShadowSocketFactory(sConfig, host, port)).build()

                    val request: Request = Request.Builder().url("https://foo.com").build()

                    try {
                        client.newCall(request).execute()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } catch (e: IOException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                } catch (e: NoSuchAlgorithmException) {
                    runOnUiThread {
                        outcome.setText(R.string.Fail)
                        output.text = e.toString()
                    }
                    e.printStackTrace()
                }
            }.start()
        }
    }
}