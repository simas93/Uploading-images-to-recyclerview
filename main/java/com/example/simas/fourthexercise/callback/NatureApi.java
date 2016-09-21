package com.example.simas.fourthexercise.callback;

import com.example.simas.fourthexercise.Model.Nature;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by simas on 8/16/2016.
 */
public interface NatureApi {

    @GET("/bins/2m15r")
    Call<List<Nature>> getAllNature();
}
