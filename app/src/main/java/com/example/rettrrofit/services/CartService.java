package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CartService {


    @GET("carts")
    Call<List<Cart>> getCartAll();



}
