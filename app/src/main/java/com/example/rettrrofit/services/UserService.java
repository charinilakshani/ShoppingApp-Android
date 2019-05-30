package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Product;
import com.example.rettrrofit.models.User;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("user/")
    Call<JSONObject>CreateUser(@Body User user);

    @GET("user/{email}")
    Call<User>getUserByEmail(@Path("email") String email);

}
