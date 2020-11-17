package org.operatorfoundation.shadowexampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send.setOnClickListener {
            val text = "Yeah"
            val viewModel = SocketViewModel(text)
            if (viewModel != null) {
                textReceived.text = getString(R.string.Success)
            }

        }

    }

}