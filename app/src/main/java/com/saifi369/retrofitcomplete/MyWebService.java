package com.saifi369.retrofitcomplete;

import com.saifi369.retrofitcomplete.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface MyWebService {

    String BASE_URL="https://jsonplaceholder.typicode.com/";
    String FEED="posts";

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(FEED)
    Call<List<Post>> getPosts();


}
