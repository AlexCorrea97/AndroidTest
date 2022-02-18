package com.example.androidtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidtest.data.model.Movie
import com.example.androidtest.databinding.FragmentDialogNewMovieBinding
import com.example.androidtest.databinding.FragmentFavoriteMoviesBinding
import com.example.androidtest.ui.dialog.receiveMovie
import com.example.androidtest.viewmodel.MovieViewModel
import com.example.androidtest.viewmodel.MyMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import layout.NewMovieDialogFragment
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
@AndroidEntryPoint
class MyMoviesFragment @Inject constructor(): Fragment() {
    lateinit var binding: FragmentFavoriteMoviesBinding
    private val movieViewModel: MyMoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater)
        setUp(binding)
        return binding.root
    }

    private fun setUp(binding: FragmentFavoriteMoviesBinding){
        binding.run {
            addMovieButton.setOnClickListener {
                var dialogNewMovie = NewMovieDialogFragment(movieViewModel)
                dialogNewMovie.show(parentFragmentManager, "NEW_MOBIE")

            }

        }
    }



}