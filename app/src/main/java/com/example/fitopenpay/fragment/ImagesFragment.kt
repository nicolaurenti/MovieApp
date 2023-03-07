package com.example.fitopenpay.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentImagesBinding
import com.example.fitopenpay.viewmodel.ImageData
import com.example.fitopenpay.viewmodel.ImageState
import com.example.fitopenpay.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private val viewModel: ImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.observe({ lifecycle }, ::updateState)
        binding.imageButton.setOnClickListener {
            launchImagePicker()
        }
    }

    private fun updateState(imageData: ImageData) {
        when (imageData.state) {
            ImageState.SHOW_IMAGE -> binding.image.setImageURI(viewModel.state.value?.image)
            ImageState.CONNECTION_ERROR -> {}
        }
    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                viewModel.selectImageAndUpload(uri)
            }
        }

    private fun launchImagePicker() {
        pickImageLauncher.launch("image/*")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ImagesFragment().apply {
                arguments = Bundle()
            }
    }
}
