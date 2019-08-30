package com.saifi369.retrofitcomplete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.saifi369.retrofitcomplete.model.Comment;
import com.saifi369.retrofitcomplete.model.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView mLog;
    MyWebService mWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mWebService = MyWebService.retrofit.create(MyWebService.class);

    }

    public void runCode(View view) {


//        getPosts();
//        getComments();
//        createPost();
//        updatePost();
        deletePost();
    }

    private void deletePost() {

        Call<Void> call = mWebService.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    mLog.setText(String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });


    }

    private void updatePost() {

        Post post = new Post(15, "New Title", null);

        Call<Post> postCall = mWebService.patchPost(5, post);

        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    mLog.setText(String.valueOf(response.code()));
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable throwable) {

            }
        });

    }

    private void createPost() {
        Post post = new Post(1, "Post Title", "this is post body");
        Map<String, String> postMap = new HashMap<>();

        postMap.put("userId", "33");
        postMap.put("title", "My Post Title");
        postMap.put("body", "this is my post body in the map");

        Call<Post> postCall = mWebService.createPost(postMap);

        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    mLog.setText(String.valueOf(response.code()));
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void getComments() {

        Map<String, String> parameters = new HashMap<>();

        parameters.put("postId", "5");
        parameters.put("_sort", "email");
        parameters.put("_order", "asc");

        Call<List<Comment>> call = mWebService.getComments(new Integer[]{2}, null, null);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful())
                    showComments(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }

    private void showComments(List<Comment> body) {

        for (Comment comment : body) {

            mLog.append("id: " + comment.getId() + "\n");
            mLog.append("postId: " + comment.getPostId() + "\n");
            mLog.append("user: " + comment.getName() + "\n");
            mLog.append("email: " + comment.getEmail() + "\n");
            mLog.append("body: " + comment.getBody() + "\n");
        }

    }

    private void getPosts() {
        Call<List<Post>> call = mWebService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.isSuccessful()) {
                    for (Post post : response.body()) {
                        showPost(post);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void showPost(Post post) {
        mLog.append("\nuserId: " + post.getUserId() + "\n");
        mLog.append("id: " + post.getId() + "\n");
        mLog.append("title: " + post.getTitle() + "\n");
        mLog.append("body: " + post.getText() + "\n\n");
    }

    public void clearOutput(View view) {
        mLog.setText("");

    }

    private void initViews() {
        mLog = findViewById(R.id.output_text);
    }

}
