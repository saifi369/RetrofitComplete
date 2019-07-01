package com.saifi369.retrofitcomplete;

import com.saifi369.retrofitcomplete.model.Comment;
import com.saifi369.retrofitcomplete.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MyWebService {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    String FEED = "posts";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //get list of posts
    @GET(FEED)
    Call<List<Post>> getPosts();

    //dynamic url parameters with path
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int userId);

    //query parameters
    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap Map<String, String> params);

    //query map(query parameters in map)
    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") Integer[] ids,
                                    @Query("_sort") String sortBy,
                                    @Query("_order") String orderBy
    );

    //using url
    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId,
                          @Field("title") String title,
                          @Field("body") String text);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> postMap);


}
