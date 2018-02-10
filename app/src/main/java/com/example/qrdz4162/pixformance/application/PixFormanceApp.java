package com.example.qrdz4162.pixformance.application;

import android.app.Application;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class PixFormanceApp extends Application{

    public static final String API_KEY = "2696829a81b1b5827d515ff121700838";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w92";

    private static PixFormanceApp pixFormanceApp;

    public static PixFormanceApp getInstance(){

        if (pixFormanceApp == null){
            throw new IllegalStateException("something went wrong !!, no application attched");
        }
        return pixFormanceApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        pixFormanceApp = this;
    }
}
