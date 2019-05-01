package com.example.rettrrofit.FrontView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rettrrofit.R;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.User;
import com.example.rettrrofit.services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Name, userName, Age, Password;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        Name = (EditText) findViewById(R.id.etName);
        userName = (EditText) findViewById(R.id.etUserName);
        Age = (EditText) findViewById(R.id.etAge);
        Password = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.bRegister);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bRegister:
//                loadRecylerViewData();
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

    }

//    private void loadRecylerViewData() {
//        UserService userService = ApiClient.getClient().create(UserService.class);
//        Call<List<User>> call = userService.CreateUser();
//
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (!response.isSuccessful()) {
//                    return;
//                }
//                List<User> users = response.body();
//                Toast.makeText(getApplicationContext(), "added ", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//
//            }
//        });
//    }


}
