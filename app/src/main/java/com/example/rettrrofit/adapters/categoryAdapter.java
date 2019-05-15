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

import com.example.rettrrofit.FrontView.HomeActivity;
import com.example.rettrrofit.FrontView.LoginActivity;
import com.example.rettrrofit.FrontView.RegisterActivity;
import com.example.rettrrofit.FrontView.content_home;
import com.example.rettrrofit.R;
import com.example.rettrrofit.models.Category;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    private List<Category> categories;
    private Context context;

    public categoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_category, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Category category = categories.get(i);
        viewHolder.textViewProductName.setText(category.getName());
//        viewHolder.linearLayoutCategoryId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, content_home.class);
//                intent.putExtra("categoryName", category.getName());
//                context.startActivity(intent);
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewProductName;
        public LinearLayout linearLayoutCategoryId;

        //        public ImageView  categoryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = (TextView) itemView.findViewById(R.id.textProductName);
            linearLayoutCategoryId = (LinearLayout) itemView.findViewById(R.id.linear_category_row_id);

        }
    }


}
