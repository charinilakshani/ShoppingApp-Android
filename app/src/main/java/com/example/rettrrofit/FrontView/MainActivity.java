package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rettrrofit.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView testresult;
    private Button btnclick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();
//
    }

    private void init() {
        btnclick = (Button) findViewById(R.id.btnclickforlogin);
        btnclick.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnclickforlogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
