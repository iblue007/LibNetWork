package com.example.libnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.TypeReference;
import com.example.libnetwork.bean.Feed;

import java.util.ArrayList;
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
        findViewById(R.id.request2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request2();
            }
        });
    }

    public void request1() {
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedType", "all")
                .addParam("userId", 0)
                .addParam("feedId", 0)
                .cacheStrategy(Request.CACHE_FIRST)
                .addParam("pageCount", 10);

        request.execute(new JsonCallback<List<Feed>>() {
            @Override
            public void onSuccess(ApiResponse<List<Feed>> response) {
                Log.e("======", "======response:" + response.body);
            }
        });
    }

    @SuppressLint("RestrictedApi")
    public void request2() {
        ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Request request = ApiService.get("/feeds/queryHotFeedsList")
                        .addParam("feedType", "all")
                        .addParam("userId", 0)
                        .addParam("feedId", 0)
                        .addParam("pageCount", 10)
                        .cacheStrategy(Request.CACHE_ONLY)
                        .responseType(new TypeReference<ArrayList<Feed>>() {
                        }.getType());//xuquxning

                ApiResponse<ArrayList<Feed>> response = request.execute();
                Log.e("======", "======response:" + response.body);
            }
        });
    }

}