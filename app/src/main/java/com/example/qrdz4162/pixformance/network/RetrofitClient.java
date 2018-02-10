package com.example.qrdz4162.pixformance.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class RetrofitClient {

    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit;

    // use singleton design pattern to create single instance of RetrofitClient for the app
    public static Retrofit getRetrofitInstance(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
        return retrofit;
    }
}
