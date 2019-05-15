package com.example.rettrrofit.FrontView;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.rettrrofit.R;
import com.example.rettrrofit.models.Product;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ViewProductActivity extends AppCompatActivity {

    TextView productName,productPrice,productDescription;
    ImageView product_Image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
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

//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.);



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

}
