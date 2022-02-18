
package com.example.androidtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.adapter.MovieAdapter
import com.example.androidtest.data.model.Movie
import com.example.androidtest.databinding.FragmentMoviesBinding
import com.example.androidtest.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment @Inject constructor(): Fragment() {
    lateinit var binding: FragmentMoviesBinding
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoviesBinding.inflate(layoutInflater)
        setUp(binding)
        return binding.root
    }

    private fun setUp(binding: FragmentMoviesBinding){
        movieViewModel.getAllPopularMovies()
        movieViewModel.movieList.observe(requireActivity(), Observer {
            val adapter = MovieAdapter(it, ::onCallBack, parentFragmentManager)
            binding.run {
                moviesRecyclerview.layoutManager = LinearLayoutManager(requireContext())
                moviesRecyclerview.adapter = adapter
            }
            it.forEachIndexed { index, movie ->

                binding.moviesRecyclerview
            }
        })

    }




    private fun onCallBack(movie:Movie, byte: ByteArray){
        movieViewModel.updateImage(movie.id, byte)
    }


}