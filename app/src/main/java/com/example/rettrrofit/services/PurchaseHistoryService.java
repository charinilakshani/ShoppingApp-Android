package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.History;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseHistoryService {

    @POST("user/history")
    Call <List> sendToHistory(@Body List<Cart> histories);


    @GET("user/history/{userId}")
    Call<List<Cart>> getForViewallHisotry(@Path("userId") int id);
}
