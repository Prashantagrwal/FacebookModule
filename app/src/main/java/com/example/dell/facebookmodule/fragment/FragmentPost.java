package com.example.dell.facebookmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.facebookmodule.Adapters.post_adapter;
import com.example.dell.facebookmodule.List.page_detail_list.page_post_details.Datum;
import com.example.dell.facebookmodule.List.page_detail_list.page_post_details.ExamplePost;
import com.example.dell.facebookmodule.List.page_detail_list.page_post_details.Posts;
import com.example.dell.facebookmodule.R;
import com.example.dell.facebookmodule.module.GraphApi;
import com.example.dell.facebookmodule.module.ObjectInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentPost extends  Fragment implements ObjectInterface{

    RecyclerView recyclerView;
    String page_id;
    GridLayoutManager mLayoutManager;
    TextView textView;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    ProgressBar progressBar;
    GraphApi graphApi;
    post_adapter adapter;
    List<Datum> list_post;
    GsonBuilder gsonBuilder;
    Gson gson;
    ExamplePost examplePost=null;Posts datum=null;
    static int count_post=0;

    public FragmentPost(String page_id)
    {
        this.page_id=page_id;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.recycle_view,container,false);
        ids(view);
        return view;
        }

        void ids(View view)
        {
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
            textView= (TextView) view.findViewById(R.id.textView_no_data);
            graphApi=new GraphApi(this);
            graphApi.getResponse(page_id,"posts{message,likes.limit(1).summary(true),reactions.limit(1).summary(true),picture}"
                    ,"",true,0);
            mLayoutManager = new GridLayoutManager(getActivity(), 1);
            recyclerView.setLayoutManager(mLayoutManager);
            list_post = new ArrayList<>();
            adapter = new post_adapter(list_post,getActivity());
            gsonBuilder=new GsonBuilder();
            gson=gsonBuilder.create();
        }

    @Override
    public void Object(JSONObject jsonObject) {}

    @Override
    public void Object(JSONObject jsonObject, int count) {parseing(jsonObject,count);}



    private void parseing(JSONObject jsonObject,int count) {

        if (count == 0) {
            examplePost = gson.fromJson(jsonObject.toString(), ExamplePost.class);
            list_post.addAll(examplePost.getPosts().getPostData());
            Log.e("size", String.valueOf(examplePost.getPosts().getPostData().size()));
            progressBar.setVisibility(View.GONE);
            if (examplePost.getPosts() != null) {
                recyclerView.setAdapter(adapter);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText("No data Available");
            }
        } else if (count == 1) {
            count_post = 1;
            datum = gson.fromJson(jsonObject.toString(), Posts.class);
            Log.e("size in value", String.valueOf(datum.getPostData().size()));
            list_post.addAll(datum.getPostData());
            adapter.notifyDataSetChanged();
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    Log.e("Yaeye!", "end called");
                    if (count_post == 0 && examplePost.getPosts().getPaging().getNext() != null) {
                        graphApi.getResponse(page_id + "/posts", "message,picture,likes.limit(1).summary(true),reactions.limit(1).summary(true)",
                                examplePost.getPosts().getPaging().getCursors().getAfter(), false, 1);
                    }
                    if (count_post == 1 && datum.getPaging().getNext() != null) {
                        Log.e("next", datum.getPaging().getNext());
                        graphApi.getResponse(page_id + "/posts", "message,picture,likes.limit(1).summary(true),reactions.limit(1).summary(true)",
                                datum.getPaging().getCursors().getAfter(), false, 1);
                    }
                    loading = true;
                }
            }
        });


    }
}
