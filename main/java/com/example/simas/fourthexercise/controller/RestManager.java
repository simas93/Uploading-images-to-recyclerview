package com.example.simas.fourthexercise.controller;

import com.example.simas.fourthexercise.Model.helper.Constants;
import com.example.simas.fourthexercise.callback.NatureApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by simas on 8/16/2016.
 */
public class RestManager {

    private NatureApi natureApi;

    public NatureApi getNatureApi() {
        if (natureApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            natureApi = retrofit.create(NatureApi.class);
        }
        return natureApi;
    }
}
