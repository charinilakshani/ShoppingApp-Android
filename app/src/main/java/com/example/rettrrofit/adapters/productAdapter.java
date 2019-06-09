package com.example.rettrrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rettrrofit.FrontView.ViewProductActivity;
import com.example.rettrrofit.R;
import com.example.rettrrofit.models.Product;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewHolder> {

    private List<Product> products;
    private Context context;

    public productAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_product, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {

        final Product product = products.get(position);
        viewHolder.textViewPid.setText(String.valueOf(product.getPrice()));
        viewHolder.textViewProductName.setText(product.getProductName());
        Picasso.with(context)
                .load(product.getImage())
                .into(viewHolder.imageView);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context, "Clicked" + product.getpId(), Toast.LENGTH_LONG).show();

                Intent intent =new Intent(context, ViewProductActivity.class);
                Gson gson =new Gson();
                String productObject =gson.toJson(product);
                intent.putExtra("product",productObject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPid, textViewProductName;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPid = (TextView) itemView.findViewById(R.id.textPid);
            textViewProductName = (TextView) itemView.findViewById(R.id.textProductName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewList);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_row_id);
        }
    }

}