package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rettrrofit.R;
import com.example.rettrrofit.adapters.cartAdapter;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.services.CartService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView totalPrice;
    private Button btn_placeOrder;
    private RecyclerView.Adapter adapter;
    private List<Cart> cartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        onInit();

    }
    private void onInit(){
        totalPrice =(TextView) findViewById(R.id.total_price);
        btn_placeOrder =(Button) findViewById(R.id.btn_placeOrder);
        recyclerView = (RecyclerView) findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        btn_placeOrder.setOnClickListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartList = new ArrayList<>();
        adapter = new cartAdapter(cartList, this);
        recyclerView.setAdapter(adapter);
        loadRecylerViewData();

    }

    private void loadRecylerViewData() {

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<List<Cart>> call = cartService.getCartAll();

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
//                testresult.setText("code" +response.code());.
                    List<Cart> carts = response.body();
                    cartList.addAll(carts);
                    adapter.notifyDataSetChanged();
                } else {
//                    Log.d()
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_placeOrder:
                startActivity(new Intent(this, CheckOutActivity.class));
//                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

    }
}
