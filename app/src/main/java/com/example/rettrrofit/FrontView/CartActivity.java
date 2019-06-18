package com.example.rettrrofit.FrontView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
import com.example.rettrrofit.services.PurchaseHistoryService;

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
    private TextView alertText;
    private int subTotal;

    String cjjc;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        onInit();


    }

    private void onInit() {
        totalPrice = (TextView) findViewById(R.id.total_price);
        btn_placeOrder = (Button) findViewById(R.id.btn_placeOrder);
        alertText =(TextView) findViewById(R.id.AlertTextView);
        recyclerView = (RecyclerView) findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        btn_placeOrder.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartList = new ArrayList<>();

        adapter = new cartAdapter(cartList, this);
        recyclerView.setAdapter(adapter);
        loadRecylerViewData();

//        subTotal = Integer.parseInt(totalPrice.getText().toString());

    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            case R.id.btn_placeOrder:
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Massage");
                builder.setMessage("Order successfully completed");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();




                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertText.setVisibility(view.VISIBLE);

                    }
                });
                builder.show();



//                deleteCart();

//
                getForSendToHistory();
//                getCartForCheckOut();
                Intent intent = new Intent(getApplicationContext(),content_home.class);
                startActivity(intent);
//
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
            int totalprice = 0;
//                testresult.setText("code" +response.code());.
                    List<Cart> carts = response.body();
                    for (Cart c: carts
                         ) {
                        subTotal = subTotal + c.getQuantity();

                    }
                    System.out.println(" responce body" + subTotal);


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




//
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
                    loadRecylerViewData();




                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

}
