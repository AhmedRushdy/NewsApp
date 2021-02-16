package com.graduationproject.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.graduationproject.newsapp.R
import com.graduationproject.newsapp.adapters.NewsAdapter
import com.graduationproject.newsapp.databinding.FragmentSavedNewsBinding
import com.graduationproject.newsapp.ui.NewsActivity
import com.graduationproject.newsapp.ui.NewsViewModel

class SavedNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var binding:FragmentSavedNewsBinding
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        initRecyclerView()


        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment,bundle)
        }

        val itemTouchHelper = object :ItemTouchHelper.SimpleCallback(
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
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)

                Snackbar.make(view,"deleted article successfully", Snackbar.LENGTH_LONG)
                    .setAction("Undo"){
                        viewModel.saveArticle(article)
                    }.show()

            }
        }
            ItemTouchHelper(itemTouchHelper).apply {
                attachToRecyclerView(binding.savedNewsRv)
            }
        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles->
            newsAdapter.differ.submitList(articles)
        })
    }
    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        binding?.savedNewsRv?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}