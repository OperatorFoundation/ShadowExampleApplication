package org.operatorfoundation.shadowexampleapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.libsodium.jni.SodiumConstants
import org.libsodium.jni.crypto.Random
import org.libsodium.jni.keys.KeyPair

class MainActivity : AppCompatActivity() {
    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test Libsodium
        val seed = Random().randomBytes(SodiumConstants.SECRETKEY_BYTES)
        val encryptionKeyPair = KeyPair(seed)
        Log.i("PUBLIC KEY:", encryptionKeyPair.publicKey.toString())

        send.setOnClickListener {
            SocketViewModel()
            textReceived.text = getString(R.string.Finished)
        }
    }
}