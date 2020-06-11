package com.hossam.nytimesarticles.ui.network.retrofit;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hossam.nytimesarticles.ui.network.constants.AppConstant.BASE_URL;

/**
 * Created by Hossam on 11/06/2020.
 */
public class RetrofitRequest {

    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
