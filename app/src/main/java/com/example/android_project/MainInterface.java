package com.example.android_project;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainInterface {

    @GET("api/characters")
    Call<String> STRING_CALL();
}
