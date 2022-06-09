package com.midas.midaschick.ui.scan

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.midas.midaschick.R
import com.midas.midaschick.data.remote.response.ServerResponse
import com.midas.midaschick.data.remote.retrofit.APIConfig
import com.midas.midaschick.databinding.ActivityScanBinding
import com.midas.midaschick.ui.coccidiosis.CoccidiosisActivity
import com.midas.midaschick.ui.createCustomTempFile
import com.midas.midaschick.ui.newcastle.NewCastleActivity
import com.midas.midaschick.ui.onboarding.OnBoardingActivity
import com.midas.midaschick.ui.reduceFileImage
import com.midas.midaschick.ui.salmonella.SalmonellaActivity
import com.midas.midaschick.ui.uriToFile
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    private val _responseMessage = MutableLiveData<String?>()
    val responseMessage: LiveData<String?> = _responseMessage

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btnCamera.setOnClickListener {
            startTakePhoto()
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.btnScan.setOnClickListener {
            if(getFile != null){
                showButton(true)
                uploadImage()
                responseMessage.observe(this){
                    showButton(false)
                    when (it) {
                        "0" -> {
                            binding.tvHasil.setText(R.string.coccidiosis)
                            binding.btnInformasi.setText(R.string.coccidiosis)
                            binding.btnInformasi.isVisible = true
                            binding.btnInformasi.setOnClickListener {
                                val move = Intent(this@ScanActivity, CoccidiosisActivity::class.java)
                                startActivity(move)
                            }
                        }
                        "1" -> {
                            binding.tvHasil.setText(R.string.healthy)
                        }
                        "2" -> {
                            binding.tvHasil.setText(R.string.new_castle_disease)
                            binding.btnInformasi.setText(R.string.new_castle_disease)
                            binding.btnInformasi.isVisible = true
                            binding.btnInformasi.setOnClickListener {
                                val move = Intent(this@ScanActivity, NewCastleActivity::class.java)
                                startActivity(move)
                            }
                        }
                        "3" -> {
                            binding.tvHasil.setText(R.string.salmonella)
                            binding.btnInformasi.setText(R.string.salmonella)
                            binding.btnInformasi.isVisible = true
                            binding.btnInformasi.setOnClickListener {
                                val move = Intent(this@ScanActivity, SalmonellaActivity::class.java)
                                startActivity(move)
                            }
                        }
                    }
                }
            }else{
                Toast.makeText(this@ScanActivity, resources.getString(R.string.image_error), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    resources.getString(R.string.permission_error),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@ScanActivity)
            getFile = myFile
            binding.imgAddStories.setImageURI(selectedImg)
        }
    }


    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.imgAddStories.setImageBitmap(result)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Pilih Gambar")
        launcherIntentGallery.launch(chooser)
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@ScanActivity,
                "com.midas.midaschick",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private fun uploadImage() {
        val file = reduceFileImage(getFile as File)
        val model = "modelresnet".toRequestBody("text/plain".toMediaType())
        val key = "safsa7fs9f87r6q97rpaofafe".toRequestBody("text/plain".toMediaType())
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestFile
        )

        val client = APIConfig.getAPIService().uploadImage(imageMultipart, model, key)
        client.enqueue(object : Callback<ServerResponse> {
            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _responseMessage.postValue(responseBody.pred?.toString())

                    }
                } else {
                    val errorBody = response.errorBody()
                    val json = errorBody?.string()
                    val objJSON = JSONTokener(json).nextValue() as JSONObject
                    val message = objJSON.getString("message")
                    _responseMessage.postValue(message)
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun showButton(isBoolean: Boolean) {
        if(isBoolean){
            binding.imgAddStories.isVisible = false
            binding.progressBar.isVisible = true
            binding.linearLayout.isVisible = false
            binding.btnScan.isVisible = false
        }else{
            binding.imgAddStories.isVisible = true
            binding.progressBar.isVisible = false
            binding.linearLayout.isVisible = false
            binding.btnScan.isVisible = false
            binding.tvPrediksi.isVisible = true
            binding.tvHasil.isVisible = true
        }
    }
}