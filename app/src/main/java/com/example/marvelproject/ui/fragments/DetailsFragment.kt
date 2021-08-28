package com.example.marvelproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.DEBUG_TAG
import com.example.marvelproject.R
import com.example.marvelproject.ui.pages.DetailsPage
import com.example.marvelproject.ui.theme.MarvelProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args: DetailsFragmentArgs by navArgs()

        return ComposeView(requireContext()).apply {
            setContent {
                    DetailsPage(
                        result = args.result,
                    navController = findNavController())
            }

        }
    }

}