package com.graduationproject.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.graduationproject.newsapp.R
import com.graduationproject.newsapp.adapters.NewsAdapter
import com.graduationproject.newsapp.databinding.FragmentArticleBinding
import com.graduationproject.newsapp.databinding.FragmentSavedNewsBinding
import com.graduationproject.newsapp.models.Article
import com.graduationproject.newsapp.ui.NewsActivity
import com.graduationproject.newsapp.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentArticleBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        binding.articleWebView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener{
           viewModel.saveArticle(article)
           Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_SHORT).show()
        }
    }

}