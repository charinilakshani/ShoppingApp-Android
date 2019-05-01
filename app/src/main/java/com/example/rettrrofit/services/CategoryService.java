package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("category")
    Call<List<Category>> getCategory();
}
