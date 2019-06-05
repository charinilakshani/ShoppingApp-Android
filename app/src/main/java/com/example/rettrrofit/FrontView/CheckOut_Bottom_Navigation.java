package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.rettrrofit.R;

public class CheckOut_Bottom_Navigation extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    home();
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    history();
                    return true;
                case R.id.navigation_notifications:
                  logOut();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out__bottom__navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onClick(View view) {

    }
    private  void home(){
        Intent intent = new Intent(this, content_home.class);
        startActivity(intent);
    }
    private  void history(){
        Intent intent = new Intent(this, PurchaseHistoryActivity.class);
        startActivity(intent);
    }
    private  void  logOut(){
        Intent intent = new Intent(this, PurchaseHistoryActivity.class);
        startActivity(intent);

    }



}
