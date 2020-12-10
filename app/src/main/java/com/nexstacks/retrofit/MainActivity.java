package com.nexstacks.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.NewsCategoryAdapter;
import adapter.NewsListAdapter;
import model.Article;
import model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsCategoryAdapter.CategoryClickListener {

    private final static String APIKEY = "4c82d7e8131841f484c6cf169bb83ae4";

    private RecyclerView mRcNewsCategories;
    private RecyclerView mRcNews;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcNews = findViewById(R.id.rc_news);
        mRcNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mRcNewsCategories = findViewById(R.id.rc_categories);
        mRcNewsCategories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        NewsCategoryAdapter newsCategoryAdapter = new NewsCategoryAdapter(this);
        newsCategoryAdapter.setListener(this);
        mRcNewsCategories.setAdapter(newsCategoryAdapter);

        pd = new ProgressDialog(MainActivity.this);

        onGetEveryNewsClicked();
    }

    public void onGetEveryNewsClicked() {
        pd.setTitle("Getting all News");
        pd.show();
        HashMap<String, Object> queries = new HashMap<>();
        queries.put("apiKey", APIKEY);
        queries.put("sources", "google-news");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<News> callNews = apiInterface.getEveryNews(queries);

        callNews.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                pd.hide();
                News news = response.body();
                setAdapterToRecycler(news.articlesList);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("Response", "Failure");
                pd.hide();
            }
        });

//        callNews.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Response", "Success");
//                Log.i("Response", response.body());
//                try {
//                    JSONObject responseObject = new JSONObject(response.body());
//                    News currentNews = News.parseNewsResponse(responseObject);
//                    setAdapterToRecycler(currentNews.articles);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                pd.hide();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i("Response", "Failure");
//                pd.hide();
//            }
//        });
    }

    private void getCategoryNews(String category){
        pd.setTitle("Getting ".concat(category).concat(" news"));
        pd.show();
        HashMap<String,Object> queries = new HashMap<>();
        queries.put("apiKey", APIKEY);
        queries.put("category", category);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<News> getCategoryNews = apiInterface.getTopHeadlines(queries);

        getCategoryNews.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                pd.hide();
                News news = response.body();
                setAdapterToRecycler(news.articlesList);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("Response", "Failure");
                pd.hide();
            }
        });

//        getCategoryNews.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Response", "Success");
//                Log.i("Response", response.body());
//                try {
//                    JSONObject responseObject = new JSONObject(response.body());
//                    News currentNews = News.parseNewsResponse(responseObject);
//                    setAdapterToRecycler(currentNews.articles);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                pd.hide();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i("Response", "Failure");
//                pd.hide();
//            }
//        });
    }

    private void setAdapterToRecycler(ArrayList<Article> articles) {
        NewsListAdapter newsListAdapter = new NewsListAdapter(MainActivity.this, articles);
        mRcNews.setAdapter(newsListAdapter);
    }

    @Override
    public void onCategoryClicked(String category) {
        getCategoryNews(category);
    }
}