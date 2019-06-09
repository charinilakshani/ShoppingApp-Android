package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rettrrofit.R;
import com.example.rettrrofit.adapters.cartAdapter;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.CheckOut;
import com.example.rettrrofit.services.CartService;
import com.example.rettrrofit.services.CheckOutService;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private List<Cart>  cartListForCheckOut;

    String cjjc;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        onInit();
        loadRecylerViewData();

    }

    private void onInit() {
        totalPrice = (TextView) findViewById(R.id.total_price);
        btn_placeOrder = (Button) findViewById(R.id.btn_placeOrder);
        recyclerView = (RecyclerView) findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        btn_placeOrder.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartList = new ArrayList<>();

        adapter = new cartAdapter(cartList, this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_placeOrder:

                getCartForCheckOut();
//                deleteCart();

                startActivity(new Intent(this, CheckOutActivity.class));
//                getCartForCheckOut();

//                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

    }

    private void loadRecylerViewData() {
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);
        System.out.println("userString" + userId);
        System.out.println(" Authenticatedddddd" + userId);

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<List<Cart>> call = cartService.getByCartId(userId);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {

//                testresult.setText("code" +response.code());.
                    List<Cart> carts = response.body();

                    System.out.println(" responce body" + response.body());
                    cartList.addAll(carts);
                    System.out.println(" cartList" + cartList);
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


    private void getCartForCheckOut() {
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);
        System.out.println("userString" + userId);
        System.out.println(" Authenticatedddddd" + userId);

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<List<Cart>> call = cartService.getByCartId(userId);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {

//                testresult.setText("code" +response.code());.


                    List<Cart> carts = response.body();
                    System.out.println(" responce body" + response.body());
                    cartListForCheckOut = new ArrayList<>();
                    cartListForCheckOut.addAll(carts);
//
                    System.out.println(" cartList" + cartListForCheckOut);
                    sendListOfCheckOut();

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


    private  void sendListOfCheckOut(){

        System.out.println("cartLstForCheckOtu" + cartListForCheckOut);
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);

        CheckOutService checkOutService = ApiClient.getClient().create(CheckOutService.class);
        Call<List> call = checkOutService.checkOut(cartListForCheckOut);

        call.enqueue(new Callback <List>() {
            @Override
            public void onResponse(Call <List>call, Response<List > response) {
                if (response.isSuccessful()) {

                } else {
//                    Log.d()
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });



    }

//

}
