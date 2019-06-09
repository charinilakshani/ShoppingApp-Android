package com.example.rettrrofit.FrontView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rettrrofit.R;
import com.example.rettrrofit.adapters.categoryAdapter;
import com.example.rettrrofit.adapters.productAdapter;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Category;
import com.example.rettrrofit.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCategory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryList = new ArrayList<>();
        adapter = new categoryAdapter(categoryList, this);
        recyclerView.setAdapter(adapter);
        loadRecylerViewData();

    }

    private void loadRecylerViewData() {
        CategoryService categoryService = ApiClient.getClient().create(CategoryService.class);
        Call<List<Category>> call = categoryService.getCategory();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categories = response.body();
                    categoryList.addAll(categories);
                    adapter .notifyDataSetChanged();
                }else {

                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
}