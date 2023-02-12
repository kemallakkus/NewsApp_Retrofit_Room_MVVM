package com.kemalakkus.newsapp_retrofit_room_mvvm.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kemalakkus.newsapp_retrofit_room_mvvm.R
import com.kemalakkus.newsapp_retrofit_room_mvvm.adapter.ArticleAdapter
import com.kemalakkus.newsapp_retrofit_room_mvvm.databinding.FragmentBreakingNewsBinding
import com.kemalakkus.newsapp_retrofit_room_mvvm.viewmodel.NewsViewModel

class BreakingNewsFragment : Fragment() {
    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var viewModel: NewsViewModel
    private val newsAdapter= ArticleAdapter()
    private val TAG= "BreakingNewsFragment"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBreakingNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.getBreakingNews("tr")
        binding.rvBreakingNews.apply {
            this.adapter=newsAdapter
            this.layoutManager= LinearLayoutManager(activity)
        }

        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleNewsFragment,bundle)
        }



        observableLiveData()

    }
    private fun observableLiveData(){
        viewModel.newsArticles.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.paginationProgressBar.visibility=View.GONE
                newsAdapter.differ.submitList(it.articles)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.paginationProgressBar.visibility=View.VISIBLE

                }

            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){

                    Log.e(TAG,"AN ERROR OCURED")

                }

            }

        })



    }




}