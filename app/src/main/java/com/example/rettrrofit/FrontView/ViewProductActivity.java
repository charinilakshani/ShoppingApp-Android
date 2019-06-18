package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView productName, productPrice, productDescription, product_Quantity, productId;
    private ImageView product_Image;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton btnCart;
    private ElegantNumberButton numberButton;
    private Button btn_add, btn_sub;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;
    String foodId = "";
    private Product product;
    private List<Cart> cartList;
    int pid;
    int quantity;
    int cartId;
  int  cartQuantityR;
 String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        init();

        bindData();
        loadRecylerViewData();

    }


    private void init() {
        productName = (TextView) findViewById(R.id.food_name);
        productPrice = (TextView) findViewById(R.id.food_price);
        productDescription = (TextView) findViewById(R.id.food_description);
//        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        product_Image = (ImageView) findViewById(R.id.food_img);
        productId = (TextView) findViewById(R.id.productId);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        product_Quantity = (TextView) findViewById(R.id.txt_quantity);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btnCart.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            product = gson.fromJson(bundle.getString("product"), Product.class);
        }

    }


    private void bindData() {

        productName.setText(product.getProductName());
        productDescription.setText(product.getDescription());
        productPrice.setText(Integer.toString(product.getPrice()));
        productId.setText(Integer.toString(product.getpId()));
        Picasso.with(getBaseContext())
                .load(product.getImage())
                .into(product_Image);





    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_sub:
                quantity = Integer.parseInt(product_Quantity.getText().toString());
                quantity--;
                product_Quantity.setText(String.valueOf(quantity));
                UpdateCart();

                break;
            case R.id.btn_add:
                quantity = Integer.parseInt(product_Quantity.getText().toString());
                quantity++;
                product_Quantity.setText(String.valueOf(quantity));
                UpdateCart();
                break;
            case R.id.btnCart:
                startActivity( new Intent(this,CartActivity.class));
                break;

        }

    }


    private void loadRecylerViewData() {
        pid = Integer.parseInt(productId.getText().toString());
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<Cart> call = cartService.getByBoth(userId, pid);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                     cartId = response.body().getCartId();
                    System.out.println("getcartId" + cartId);
                     imageUrl =response.body().getProductImage();
                    System.out.println("imageUrl" + imageUrl);

                     cartQuantityR = response.body().getQuantity();
                    product_Quantity.setText(Integer.toString(cartQuantityR));
                     System.out.println("firsttime cartId" +cartQuantityR);



                } else {
                    cartId = 0;

                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }



    private void UpdateCart() {
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);

        Cart cartUpdate = new Cart();
        cartUpdate.setUserId(userId);
        cartUpdate.setCartId(cartId);
        cartUpdate.setQuantity(quantity);
        cartUpdate.setProductImage(product.getImage());
//        cartUpdate.setProductImage(product_Image.ge);
//        cartUpdate.setProductImage(product_Image..toString());

    //    cartUpdate.setTotalPrice(price*quantity);

        cartUpdate.setProductName(productName.getText().toString().trim());


        cartUpdate.setpId(pid);
        System.out.print(" get Updated products" +cartUpdate);

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<JSONObject> call = cartService.UpdateCart(cartUpdate);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    System.out.println(" cart updated");

                } else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }

}
