package com.hossam.nytimesarticles.ui.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hossam.nytimesarticles.R;
import com.hossam.nytimesarticles.ui.network.model.ArticleResponse;
import com.hossam.nytimesarticles.ui.network.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private View root;
    private ProgressBar progress_circular_movie_article;
    private ArticleAdapter adapter;
    private ArrayList<ArticleResponse.ResultsBean> articleArrayList = new ArrayList<>();
    private ArticleViewModel articleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();

        return root;
    }

    private void initViews() {
        progress_circular_movie_article = (ProgressBar) root.findViewById(R.id.progress_circular_movie_article);
        RecyclerView my_recycler_view = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        my_recycler_view.setLayoutManager(layoutManager);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);
        // adapter
        adapter = new ArticleAdapter(getActivity(), articleArrayList);
        my_recycler_view.setAdapter(adapter);
        // View Model
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        getMovieArticles();
    }

    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData().observe(Objects.requireNonNull(getActivity()), articleResponse -> {
            if (articleResponse != null) {
                progress_circular_movie_article.setVisibility(View.GONE);
                List<ArticleResponse.ResultsBean> articles = articleResponse.getResults();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }
}