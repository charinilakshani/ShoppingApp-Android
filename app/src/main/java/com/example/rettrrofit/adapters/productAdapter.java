package com.example.rettrrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rettrrofit.R;
import com.example.rettrrofit.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;

    public productAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_product, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Product product = products.get(position);
        viewHolder.textViewPid.setText(product.getpId());
        viewHolder.textViewProductName.setText(product.getProductName());
        Picasso.with(context)
                .load(product.getImage())
                .into(viewHolder.imageView);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked" +product.getpId(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPid, textViewProductName;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPid = (TextView) itemView.findViewById(R.id.textPid);
            textViewProductName = (TextView) itemView.findViewById(R.id.textProductName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewList);
            linearLayout =(LinearLayout) itemView.findViewById(R.id.linear_row_id);
        }
    }

}
