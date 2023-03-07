package com.example.domain.service

import android.net.Uri
import com.example.domain.utils.CoroutineResult

interface FirebaseService {
    fun uploadImage(image: Uri): CoroutineResult<Boolean>
}
