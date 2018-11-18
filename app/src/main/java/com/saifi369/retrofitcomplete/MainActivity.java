package com.saifi369.retrofitcomplete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.saifi369.retrofitcomplete.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    public void runCode(View view){

        MyWebService myWebService=MyWebService.retrofit.create(MyWebService.class);

        Call<List<Post>> call=myWebService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(response.isSuccessful())
                {
                    for (Post post:response.body()) {
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
        mLog.append("userId: "+post.getUserId()+"\n");
        mLog.append("id: "+post.getId()+"\n");
        mLog.append("title: "+post.getTitle()+"\n");
        mLog.append("body: "+post.getText()+"\n\n");
    }

    public void clearOutput(View view) {
        mLog.setText("");

    }

    private void initViews() {
        mLog=findViewById(R.id.output_text);
    }

}
