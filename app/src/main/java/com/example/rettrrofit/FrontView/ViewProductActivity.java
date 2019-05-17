package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.rettrrofit.R;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.Product;
import com.example.rettrrofit.services.CartService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends AppCompatActivity implements View.OnClickListener {

  private   TextView productName,productPrice,productDescription,product_Quantity;
  private   ImageView product_Image;
 private    CollapsingToolbarLayout collapsingToolbarLayout;
 private    FloatingActionButton btnCart;
 private    ElegantNumberButton numberButton;
 private  Button btn_add,btn_sub;

    String foodId ="";
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        init();
        bindData();
    }




    private void init() {
        productName =(TextView) findViewById(R.id.food_name);
        productPrice =(TextView) findViewById(R.id.food_price);
        productDescription =(TextView) findViewById(R.id.food_description);

        numberButton =(ElegantNumberButton) findViewById(R.id.number_button);
        btnCart =(FloatingActionButton) findViewById(R.id.btnCart);
        product_Image =(ImageView) findViewById(R.id.food_img);

        collapsingToolbarLayout =(CollapsingToolbarLayout) findViewById(R.id.collapsing);




        btn_add =(Button) findViewById(R.id.btn_add);
        btn_sub =(Button) findViewById(R.id.btn_sub);
        product_Quantity =(TextView) findViewById(R.id.txt_quantity);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            product = gson.fromJson(bundle.getString("product"), Product.class);
        }

    }

    private  void bindData(){

        productName.setText(product.getProductName());
        productDescription.setText(product.getDescription());
        productPrice.setText(Integer.toString(product.getPrice()));
        Picasso.with(getBaseContext())
                .load(product.getImage())
                .into(product_Image);




    }

    @Override
    public void onClick(View view) {
        int quantity;
        switch (view.getId()) {
            case R.id.btn_sub:
                quantity = Integer.parseInt(product_Quantity.getText().toString());
                quantity--;
                product_Quantity.setText(String.valueOf(quantity));

                // update  cart from the user id



                break;
            case R.id.btn_add:
                 quantity = Integer.parseInt(product_Quantity.getText().toString());
                quantity++;
                product_Quantity.setText(String.valueOf(quantity));
                // update cart from the user id


        }

    }
    private void loadRecylerViewData() {

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<List<Cart>> call = cartService.getByCartId(cartId);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
//                testresult.setText("code" +response.code());.
                    List<Cart> carts = response.body();
                    int quantity = carts.get(cartId).getQuantity();
                    product_Quantity.setText(String.valueOf(quantity));

//                    cartList.addAll(carts);
//                    adapter.notifyDataSetChanged();
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

}
