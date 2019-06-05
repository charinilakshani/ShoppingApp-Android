package com.example.rettrrofit.FrontView;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.rettrrofit.R;
import com.example.rettrrofit.adapters.checkOutAdapter;
import com.example.rettrrofit.clients.ApiClient;
import com.example.rettrrofit.models.CheckOut;
import com.example.rettrrofit.services.CheckOutService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseHistoryActivity extends AppCompatActivity {
    private TextView Total;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserId = "userId";
    SharedPreferences sharedpreferences;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CheckOut> checkOutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
    }
    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCategory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkOutList = new ArrayList<>();
        adapter = new checkOutAdapter(checkOutList, this);
        recyclerView.setAdapter(adapter);
    }

    private void getCartForCheckOut() {
        SharedPreferences example = getSharedPreferences(MyPREFERENCES, 0);
        int userId = example.getInt("value", 0);
        System.out.println("userString" + userId);
        System.out.println(" Authenticatedddddd" + userId);

        CheckOutService checkOutService = ApiClient.getClient().create(CheckOutService.class);
        Call<List<CheckOut>> call = checkOutService.getByUserId(userId);

        call.enqueue(new Callback<List<CheckOut>>() {
            @Override
            public void onResponse(Call<List<CheckOut>> call, Response<List<CheckOut>> response) {
                if (response.isSuccessful()) {
//                testresult.setText("code" +response.code());.
                    List<CheckOut> checkOut = response.body();
                    checkOutList.addAll(checkOut);
                    adapter .notifyDataSetChanged();
                    System.out.println(" responce body" + response.body());

                } else {
//                    Log.d()
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<List<CheckOut>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
