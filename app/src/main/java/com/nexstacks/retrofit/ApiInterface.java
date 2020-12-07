package com.nexstacks.retrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    /**
     * 1. GET
     * 2. POST
     * 3. PUT
     * 4. DELETE
     */

    @GET("everything/")
    Call<String> getEveryNews(@QueryMap HashMap<String, Object> queries);
}
