package com.example.libnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.View;

import com.example.libnetwork.bean.Feed;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request1();
            }
        });
    }

    public void request1() {
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedType", "all")
                .addParam("userId", 0)
                .addParam("feedId", 0)
                .addParam("pageCount", 10);

        request.execute(new JsonCallback<List<Feed>>() {
            @Override
            public void onSuccess(ApiResponse<List<Feed>> response) {
                Log.e("======", "======response:" + response.body);
            }
        });
    }
}