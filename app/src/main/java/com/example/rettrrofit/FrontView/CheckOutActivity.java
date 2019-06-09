package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rettrrofit.R;
import com.example.rettrrofit.adapters.categoryAdapter;
import com.example.rettrrofit.adapters.checkOutAdapter;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.Category;
import com.example.rettrrofit.models.CheckOut;
import com.example.rettrrofit.services.CartService;
import com.example.rettrrofit.services.CheckOutService;
import com.example.rettrrofit.services.PurchaseHistoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity  implements View.OnClickListener {
  private TextView Total;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private List<Cart> checkOutList;
    private List<Cart>  cartListForCheckOut;
    private Button checkOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        init();
        getForViewAllCartsForCheckOut();
    }
    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        checkOut =(Button) findViewById(R.id.btn_placeOrder);
        checkOut.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkOutList = new ArrayList<>();
        adapter = new checkOutAdapter(checkOutList, this);
        recyclerView.setAdapter(adapter);



    }

    private void getForViewAllCartsForCheckOut() {
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);
        System.out.println("userString" + userId);
        System.out.println(" Authenticatedddddd" + userId);

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<List<Cart>> call = cartService.getByUserId(userId);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
//                testresult.setText("code" +response.code());.
                    List<Cart> checkOut = response.body();
                    checkOutList.addAll(checkOut);
                    adapter .notifyDataSetChanged();
                    System.out.println(" responce body" + response.body());

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
                    getForSendToHistory();

                    startActivity( new Intent(this,PurchaseHistoryActivity.class));
                    break;

            }

        }


    private void getForSendToHistory() {
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
                       sendToHistory();

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
    private  void sendToHistory() {

        System.out.println("cartLstForCheckOtu" + cartListForCheckOut);
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);

        PurchaseHistoryService purchaseHistoryService = ApiClient.getClient().create(PurchaseHistoryService.class);
        Call<List> call = purchaseHistoryService.sendToHistory(cartListForCheckOut);

        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                if (response.isSuccessful()) {
                    deleteCart();

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

    public void deleteCart() {
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);
        System.out.println("userString" + userId);
        System.out.println(" Authenticatedddddd" + userId);


        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<Void> call = cartService.deleteCart(userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}

