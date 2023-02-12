package com.kemalakkus.newsapp_retrofit_room_mvvm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kemalakkus.newsapp_retrofit_room_mvvm.R
import com.kemalakkus.newsapp_retrofit_room_mvvm.adapter.ArticleAdapter
import com.kemalakkus.newsapp_retrofit_room_mvvm.databinding.FragmentSavedNewsBinding
import com.kemalakkus.newsapp_retrofit_room_mvvm.viewmodel.NewsViewModel

class SavedNewsFragment() : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var viewModel: NewsViewModel
    private val newsAdapter = ArticleAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.rvSavedNews.apply {
            this.adapter = newsAdapter
            this.layoutManager = LinearLayoutManager(activity)

        }
        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleNewsFragment,bundle)
        }

        val itemTouchHelperCallBack=object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article= newsAdapter.differ.currentList[position]
                viewModel.deleteArticles(article)
                Snackbar.make(view,"Deleted article successfully",Snackbar.LENGTH_SHORT).apply {
                    setAction("undo"){
                        viewModel.saveArticles(article)
                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }
        observeSaveData()


    }
    fun observeSaveData(){
        viewModel.getSavedArticle().observe(viewLifecycleOwner, Observer {
            it?.let {
                newsAdapter.differ.submitList(it)

            }
        })



    }


}
