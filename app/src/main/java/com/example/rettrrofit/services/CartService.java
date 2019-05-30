package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Cart;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {


    @GET("user/carts")
    Call<List<Cart>> getCartAll();

    @GET("user/carts/{cartId}")
    Call<List<Cart>> getByCartId(@Path("cartId") int id);

    @GET("user/carts/byBoth/{userId}/{pid}")
    Call<Cart>getByBoth(@Path("userId") int userId, @Path("pid") int pid);

    @POST("user/carts")
    Call<JSONObject>UpdateCart(@Body Cart cart);

}
