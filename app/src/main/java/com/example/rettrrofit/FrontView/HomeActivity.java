package com.example.rettrrofit.FrontView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Product;
import com.example.rettrrofit.R;
import com.example.rettrrofit.adapters.productAdapter;
import com.example.rettrrofit.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        adapter = new productAdapter(productList, this);
        recyclerView.setAdapter(adapter);
        loadRecylerViewData();
    }


    private void loadRecylerViewData() {

        ProductService productService = ApiClient.getClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
//                testresult.setText("code" +response.code());.
                    List<Product> products = response.body();
                    productList.addAll(products);
                    adapter.notifyDataSetChanged();
                } else {
//                    Log.d()
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
