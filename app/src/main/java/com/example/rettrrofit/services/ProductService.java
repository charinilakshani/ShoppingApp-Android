package com.example.rettrrofit.services;

import com.example.rettrrofit.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("products")
    Call<List<Product>> getProducts();
}
