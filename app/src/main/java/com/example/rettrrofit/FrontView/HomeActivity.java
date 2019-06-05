package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

public class HomeActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Product> productList;
    private FloatingActionButton floatingActionButton;
    String categoryname ="";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        adapter = new productAdapter(productList, this);
        recyclerView.setAdapter(adapter);
//



//        public  void logout(View view){
//            SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//            editor.clear();
//            editor.commit();
//        }

        // getIntent Here
//        if(getIntent() !=null){
//            categoryname =getIntent().getStringExtra("categoryName");
//            if(! categoryname.isEmpty() && categoryname != null){
//                loadItemFromCategory(categoryname);
//            }
//        }
        loadRecylerViewData();
    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.fab:
//
//                SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
//                int userId = example.getInt("value",0);
//
//                System.out.println("userId"+userId);
//
//                Intent CIntent = new Intent(getApplicationContext(), CartActivity.class);
//
//                startActivity(CIntent);
////                startActivity( new Intent(this,CartActivity.class));
//                break;
//
//        }
//    }








    private void loadRecylerViewData() {
//
        Intent intent = getIntent();
        String categoryName = intent.getExtras().getString("categoryName");


        ProductService productService = ApiClient.getClient().create(ProductService.class);
        Call<List<Product>> call = productService.getByCategory(categoryName);

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
