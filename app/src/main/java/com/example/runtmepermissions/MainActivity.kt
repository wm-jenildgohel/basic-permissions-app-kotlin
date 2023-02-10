package com.example.runtmepermissions

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.runtmepermissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val requestCode = 101
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.btnAudioPermission.setOnClickListener {
            val permission =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "not enabled", Toast.LENGTH_SHORT).show()
                makeAudioRequest()
            } else {
                Toast.makeText(this, "permissions allowed", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnNetworkPermission.setOnClickListener {
            val permission =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "not enabled", Toast.LENGTH_SHORT).show()
                makeNetworkRequest()
            } else {
                Toast.makeText(this, "permissions allowed", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnStoragePermission.setOnClickListener {
            val permission =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                } else {
                    TODO("VERSION.SDK_INT < R")
                }
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "not enabled", Toast.LENGTH_SHORT).show()
                makeStorageRequest()
            } else {
                Toast.makeText(this, "permissions allowed", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCameraPermission.setOnClickListener {
            val permission =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "not enabled", Toast.LENGTH_SHORT).show()
                makeCameraRequest()
            } else {
                Toast.makeText(this, "permissions allowed", Toast.LENGTH_SHORT).show()
            }
        }

        setContentView(binding.root)
    }

    private fun makeStorageRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            requestCode
        )
    }

    private fun makeNetworkRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_NETWORK_STATE),
            requestCode
        )
    }

    private fun makeCameraRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCode
        )
    }

    private fun makeAudioRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            requestCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            this.requestCode -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "not granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}