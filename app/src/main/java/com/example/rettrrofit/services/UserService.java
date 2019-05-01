package com.example.rettrrofit.services;

import com.example.rettrrofit.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("user")
    Call<User>CreateUser(@Body User user);



}
