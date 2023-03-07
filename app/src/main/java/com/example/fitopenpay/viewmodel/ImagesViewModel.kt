package com.example.fitopenpay.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.FirebaseUseCase
import com.example.domain.utils.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val firebaseUseCase: FirebaseUseCase) :
    ViewModel() {

    private val mutableStateLiveData: MutableLiveData<ImageData> = MutableLiveData()
    val state: LiveData<ImageData>
        get() = mutableStateLiveData

    fun selectImageAndUpload(image: Uri) = viewModelScope.launch {
        withContext(Dispatchers.IO) { firebaseUseCase.uploadImageToFirestore(image) }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableStateLiveData.postValue(
                        ImageData(
                            state = ImageState.SHOW_IMAGE,
                            image = image,
                        ),
                    )
                }
                is CoroutineResult.Failure -> {
                    mutableStateLiveData.postValue(ImageData(state = ImageState.CONNECTION_ERROR))
                }
            }
        }
    }

    companion object {
        const val IMAGE_UPLOAD_ERROR = "IMAGE_UPLOAD_ERROR"
    }
}

data class ImageData(
    val state: ImageState,
    val image: Uri? = null,
    val error: String? = null
)

enum class ImageState {
    SHOW_IMAGE,
    CONNECTION_ERROR,
}
