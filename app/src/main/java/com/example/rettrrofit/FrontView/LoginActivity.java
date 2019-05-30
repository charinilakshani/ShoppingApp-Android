package com.example.rettrrofit.FrontView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rettrrofit.R;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.User;
import com.example.rettrrofit.services.UserService;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etEmail, etPassword;
    TextView registerLink;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        etEmail = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tRegisterLink);


        btnLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                loadRecylerViewData();
                break;
            case R.id.tRegisterLink:
                startActivity( new Intent(this,RegisterActivity.class));
                break;
        }
    }

//
//

    private void loadRecylerViewData() {
        String strEmail = etEmail.getText().toString().trim();
//
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<User>call = userService.getUserByEmail(strEmail);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User>call, Response<User>response) {
                if (response.isSuccessful()) {
                    int userId =response.body().getUserId();
                    SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
                    SharedPreferences.Editor editor = example.edit();
                    editor.putInt("value", userId);
                    editor.commit();
//                    String email = response.body().getEmail().toString();
                    Intent intent = new Intent(getApplicationContext(), content_home.class);

//                    intent.putExtra("userId", userId);
                    startActivity(intent);



//                    System.out.println(" Authenticated"+ userId);


                } else {
//                    Log.d()
                    System.out.println(" You are not an regisster person"+response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
