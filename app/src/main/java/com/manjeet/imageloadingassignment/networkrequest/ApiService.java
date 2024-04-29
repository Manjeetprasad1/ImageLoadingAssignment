package com.manjeet.imageloadingassignment.networkrequest;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
        @GET("content/misc/media-coverages?limit=100")
        Call<Object> callMediaCoverageApi();

    @NotNull
    Object callClockInApi(@NotNull HashMap<String, Object> body);
}

