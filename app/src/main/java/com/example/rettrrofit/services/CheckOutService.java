package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.CheckOut;
import com.example.rettrrofit.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CheckOutService {

    @POST("user/checkOut")
    Call <List> checkOut(@Body List<Cart> checkOut);

    @GET("user/checkOut/{userId}")
    Call<List<CheckOut>> getByUserId(@Path("userId") int id);

}
