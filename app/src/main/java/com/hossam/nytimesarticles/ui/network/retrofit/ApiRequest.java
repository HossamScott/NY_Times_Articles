package com.hossam.nytimesarticles.ui.network.retrofit;

import com.hossam.nytimesarticles.ui.network.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Hossam on 11/06/2020.
 */
public interface ApiRequest {
    @GET("viewed/1.json")
    Call<ArticleResponse> getArticles(
            @Query("api-key") String apiKey
    );
}
