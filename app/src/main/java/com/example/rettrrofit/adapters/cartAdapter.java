package com.example.rettrrofit.adapters;


import android.content.Context;
import android.content.Intent;
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
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.Product;
import com.example.rettrrofit.services.CartService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {

    private List<Cart> carts;
    private Context context;

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
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {

        final Cart cart = carts.get(position);
        viewHolder.cart_item_name.setText(String.valueOf(cart.getpId()));

        viewHolder.cart_item_price.setText(String.valueOf(cart.getQuantity()));
        viewHolder.cart_item_name.setText(cart.getProductName());
//        viewHolder.btn_delete.setOnClickListener(view -> {
//            CartService cartService = new CartService() ;
//
//
//            cartService.deleteCartItem(context, cartProduct.getProduct().getId(), 0);
//            cartProductList.remove(myViewHolder.getAdapterPosition());
//            notifyItemRemoved(myViewHolder.getAdapterPosition());
//        });

//        viewHolder.cart_id_count.setText(String.valueOf(cart.getQuantity()));
//        viewHolder.text_price.setText(String.valueOf(cart.getQuantity()));



//        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Toast.makeText(context, "Clicked" + product.getpId(), Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(context, CartActivity.class);
//                Gson gson = new Gson();
//                String productObject = gson.toJson(cart);
//                intent.putExtra("cart", productObject);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cart_item_name, cart_item_price;
        public ImageView cart_id_count;
        public LinearLayout linearLayout;
        public ImageView btn_delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_item_name = (TextView) itemView.findViewById(R.id.cart_item_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.cart_row_id);
            cart_item_price = (TextView) itemView.findViewById(R.id.cart_item_price);
            btn_delete = (ImageView) itemView.findViewById(R.id.detele_item);

        }
    }

}


