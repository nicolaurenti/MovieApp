package com.example.data.service

import android.net.Uri
import com.example.domain.service.FirebaseService
import com.example.domain.usecase.FirebaseUseCaseImpl
import com.example.domain.utils.CoroutineResult
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseServiceImpl : FirebaseService {
    override fun uploadImage(image: Uri): CoroutineResult<Boolean> {
        return try {
            FirebaseFirestore.getInstance().collection(FirebaseUseCaseImpl.COLLECTION_PATH)
                .document(FirebaseUseCaseImpl.DOCUMENT_PATH)
                .set(hashMapOf(FirebaseUseCaseImpl.UPLOAD_KEY to image))
            CoroutineResult.Success(true)
        } catch (e: Exception) {
            CoroutineResult.Failure(Exception())
        }
    }
}
