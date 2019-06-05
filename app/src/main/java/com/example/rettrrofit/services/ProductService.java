package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    @GET("products")
    Call<List<Product>> getProducts();


    @GET("products/getByProductName/{category}")
    Call<List<Product>> getByCategory(@Path("category") String category);
}

