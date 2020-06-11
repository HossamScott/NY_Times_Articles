package com.hossam.nytimesarticles.ui.network.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hossam.nytimesarticles.ui.network.model.ArticleResponse;
import com.hossam.nytimesarticles.ui.network.repository.ArticleRepository;

import static com.hossam.nytimesarticles.ui.network.constants.AppConstant.API_KEY;

/**
 * Created by Hossam on 11/06/2020.
 */

public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);

        articleRepository = new ArticleRepository();
        this.articleResponseLiveData = articleRepository.getMovieArticles(API_KEY);
    }

    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
