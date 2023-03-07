package com.example.domain.usecase

import android.net.Uri
import com.example.domain.service.FirebaseService
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

interface FirebaseUseCase {
    fun uploadImageToFirestore(image: Uri): CoroutineResult<Boolean>
}

class FirebaseUseCaseImpl @Inject constructor(private val firebaseService: FirebaseService) :
    FirebaseUseCase {

    override fun uploadImageToFirestore(image: Uri): CoroutineResult<Boolean> {
        return firebaseService.uploadImage(image)
    }

    companion object {
        const val COLLECTION_PATH = "image"
        const val DOCUMENT_PATH = "USER"
        const val UPLOAD_KEY = "uri"
    }
}
