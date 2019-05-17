package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartService {


    @GET("carts")
    Call<List<Cart>> getCartAll();

    @GET("carts/{cartId}")
    Call<Cart> getByCartId(@Path("cartId") int id);

}
