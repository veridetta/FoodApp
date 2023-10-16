package com.rajendra.foodapp.retrofit;

import com.rajendra.foodapp.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("CKVpcp")
    Call<FoodData> getAllData();


    // lets make our model class of json data.

}
