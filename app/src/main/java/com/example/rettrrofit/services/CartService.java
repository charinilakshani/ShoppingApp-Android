package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Cart;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @POST("user/carts/fromMobile")
    Call<JSONObject>UpdateCart(@Body Cart cart);

    @DELETE("user/carts/deleteAll/{userId}")
    Call<Void> deleteCart(@Path("userId") int userId);

    @GET("user/carts/{userId}")
    Call<List<Cart>> getByUserId(@Path("userId") int id);

    @DELETE("user/carts/{cartId}")
    Call<Void> deleteCartByCartId(@Path("cartId") int cartId);

}
