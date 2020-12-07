package com.nexstacks.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String APIKEY = "4c82d7e8131841f484c6cf169bb83ae4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGetEveryNewsClicked(View view){

        HashMap<String,Object> queries = new HashMap<>();
        queries.put("apiKey", APIKEY);
        queries.put("sources", "google-news");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callNews = apiInterface.getEveryNews(queries);

        callNews.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Response", "Success");
                Log.i("Response", response.body());
                try {
                    JSONObject responseObject = new JSONObject(response.body());

                    News currentNews = News.parseNewsResponse(responseObject);

                    Toast.makeText(MainActivity.this, currentNews.articles.size() + "", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Response", "Failure");
            }
        });
    }
}