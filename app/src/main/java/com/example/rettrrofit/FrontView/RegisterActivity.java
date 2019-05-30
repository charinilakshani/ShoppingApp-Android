package com.example.rettrrofit.FrontView;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rettrrofit.R;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.User;
import com.example.rettrrofit.services.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstName, secondName, email, password;
    private Button btnRegister;
private TextView alertText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

    }

    private void init() {
        firstName = (EditText) findViewById(R.id.etName);
        secondName = (EditText) findViewById(R.id.etUserName);
        email = (EditText) findViewById(R.id.etAge);
        password = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.bRegister);
        alertText =(TextView) findViewById(R.id.AlertTextView);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.bRegister:
                loadRecylerViewData();
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Massage");
                builder.setMessage("You Are Successfully registered");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertText.setVisibility(view.VISIBLE);
                    }
                });
                builder.show();

//                startActivity(new Intent(this, content_home.class));
//                break;
        }

    }


    private void loadRecylerViewData() {
        User user = new User();
        user.setFirstName(firstName.getText().toString().trim());
        user.setSecondName(firstName.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());

        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<JSONObject> call = userService.CreateUser(user);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), content_home.class);
                    startActivity(intent);
                } else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }


}
