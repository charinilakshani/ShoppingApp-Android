package com.example.rettrrofit.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rettrrofit.FrontView.CartActivity;
import com.example.rettrrofit.FrontView.ViewProductActivity;
import com.example.rettrrofit.R;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.CheckOut;
import com.example.rettrrofit.models.Product;
import com.example.rettrrofit.services.CartService;
import com.example.rettrrofit.services.CheckOutService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {

    private List<Cart> carts;
    private Context context;
    int quantity;
    Cart cart;
    int productId, cartId;
    String productName,image;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;

    public cartAdapter(List<Cart> carts, Context context) {
        this.carts = carts;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list, viewGroup, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int position) {

        final Cart cart = carts.get(position);
        viewHolder.cart_item_name.setText(String.valueOf(cart.getpId()));

        viewHolder.cart_item_price.setText(String.valueOf(cart.getQuantity()));
        viewHolder.cart_item_name.setText(cart.getProductName());
        viewHolder.txt_quantity.setText(String.valueOf(cart.getQuantity()));
        viewHolder.product_id.setText(String.valueOf(cart.getpId()));
        viewHolder.cart_Id.setText(String.valueOf(cart.getCartId()));

        Picasso.with(context)
                .load(cart.getProductImage())
                .into(viewHolder.image);


        viewHolder.btn_Add.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

                  quantity = Integer.parseInt(viewHolder.txt_quantity.getText().toString());
                 quantity ++;
                 viewHolder.txt_quantity.setText(String.valueOf(quantity));
                 productId = Integer.parseInt(viewHolder.product_id.getText().toString());
                 cartId = Integer.parseInt(viewHolder.cart_Id.getText().toString());
                 productName = viewHolder.cart_item_name.getText().toString();
                 image         =viewHolder.image.getContext().toString();
                 getUpdate( );

                 System.out.print(" product added");
             }
         });
         viewHolder.btn_Sub.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 quantity = Integer.parseInt(viewHolder.txt_quantity.getText().toString());
                 quantity --;
                 viewHolder.txt_quantity.setText(String.valueOf(quantity));
                 productId = Integer.parseInt(viewHolder.product_id.getText().toString());
                 cartId = Integer.parseInt(viewHolder.cart_Id.getText().toString());
                 productName = viewHolder.cart_item_name.getText().toString();

                 getUpdate();

                 System.out.print(" product added");

             }
         });

         viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 cartId = Integer.parseInt(viewHolder.cart_Id.getText().toString());
                 deleteCart();


             }
         });



        }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cart_item_name, cart_item_price ,txt_quantity, cart_Id,product_id;
        public ImageView cart_id_count;
        public LinearLayout linearLayout;
        public ImageView btn_delete, image;
        public  Button btn_Sub,btn_Add;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_item_name = (TextView) itemView.findViewById(R.id.cart_item_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.cart_row_id);
            cart_item_price = (TextView) itemView.findViewById(R.id.cart_item_price);
            btn_delete = (ImageView) itemView.findViewById(R.id.detele_item);
            txt_quantity =(TextView) itemView.findViewById(R.id.txt_quantity);
            btn_Add =   (Button)  itemView.findViewById(R.id.btn_add);
            btn_Sub =(Button) itemView.findViewById(R.id.btn_sub);
            cart_Id =(TextView) itemView.findViewById(R.id.cart_id);
            product_id =(TextView) itemView.findViewById(R.id.product_id);
            image =(ImageView)itemView.findViewById(R.id.image);


        }
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

public  void getUpdate(){
        System.out.println(" cart option" +carts);

    SharedPreferences example = context.getSharedPreferences(MyPREFERENCES, 0);
    int userId = example.getInt("value", 0);


    Cart cartUpdate = new Cart();
    cartUpdate.setUserId(userId);
    cartUpdate.setpId(productId);
    cartUpdate.setCartId(cartId);
    cartUpdate.setProductName(productName);
    cartUpdate.setProductImage(image);



    cartUpdate.setQuantity(quantity);


    System.out.println("string values"+ cartUpdate);


    CartService cartService = ApiClient.getClient().create(CartService.class);
    Call<JSONObject> call = cartService.UpdateCart(cartUpdate);

    call.enqueue(new Callback<JSONObject>() {
        @Override
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                System.out.println(" cart updated");

            } else {

            }
        }

        @Override
        public void onFailure(Call<JSONObject> call, Throwable t) {

        }
    });

}

public  void deleteCart(){

    CartService cartService = ApiClient.getClient().create(CartService.class);
    Call<Void> call = cartService.deleteCartByCartId(cartId);

    call.enqueue(new Callback <Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                System.out.println(" cart updated");
                getAgain();



            } else {

            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {

        }
    });

}

    private void getAgain() {

        SharedPreferences example = context.getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);
        System.out.println("userString" + userId);
        System.out.println(" Authenticatedddddd" + userId);

        CartService cartService = ApiClient.getClient().create(CartService.class);
        Call<List<Cart>> call = cartService.getByCartId(userId);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
//                    int totalprice = 0;
//                testresult.setText("code" +response.code());.
                    List<Cart> carts = response.body();
//
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


