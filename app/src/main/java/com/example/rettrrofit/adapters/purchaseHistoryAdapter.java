package com.example.rettrrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rettrrofit.R;
import com.example.rettrrofit.models.Cart;
import com.example.rettrrofit.models.CheckOut;

import java.util.List;

public class purchaseHistoryAdapter extends RecyclerView.Adapter<purchaseHistoryAdapter.ViewHolder> {
    private List<Cart> checkOuts;
    private Context context;

    public purchaseHistoryAdapter(List<Cart> categories, Context context) {
        this.checkOuts = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.checkout_list, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Cart checkOut = checkOuts.get(i);
        viewHolder.textViewProductName.setText(checkOut.getProductName());
        viewHolder.txtViewQuantity.setText(String.valueOf(checkOut.getQuantity()));
        viewHolder.textViewProductPrice.setText(String.valueOf(checkOut.getCartId()));
//


    }


    @Override
    public int getItemCount() {
        return checkOuts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewProductName, textViewProductPrice, txtViewQuantity;

        public LinearLayout linearLayoutCategoryId;

        //        public ImageView  categoryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = (TextView) itemView.findViewById(R.id.checkOut_out_name);
            textViewProductPrice = (TextView) itemView.findViewById(R.id.checkOut_item_price);
            txtViewQuantity = (TextView) itemView.findViewById(R.id.txt_item_count);

//            linearLayoutCategoryId = (LinearLayout) itemView.findViewById(R.id.linear_category_row_id);

        }
    }
}
