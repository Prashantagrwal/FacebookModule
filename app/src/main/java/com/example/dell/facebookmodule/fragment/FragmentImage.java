package com.example.dell.facebookmodule.fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.facebookmodule.Adapters.image_adapter;
import com.example.dell.facebookmodule.List.page_detail_list.post_image_details.Datum;
import com.example.dell.facebookmodule.List.page_detail_list.post_image_details.ExampleImage;
import com.example.dell.facebookmodule.List.page_detail_list.post_image_details.Posts;
import com.example.dell.facebookmodule.R;
import com.example.dell.facebookmodule.module.GraphApi;
import com.example.dell.facebookmodule.module.GridSpacingItemDecoration;
import com.example.dell.facebookmodule.module.ObjectInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentImage extends Fragment implements ObjectInterface
{
    RecyclerView recyclerView;
    String page_id;
    TextView textView;
    ProgressBar progressBar;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    GsonBuilder gsonBuilder;
    Gson gson;
    ExampleImage exampleImage=null;
    Posts posts=null;
    image_adapter adapter;
    static int count_image=0;
    List<Datum> list_image;
    GraphApi graphApi;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    GridLayoutManager mLayoutManager;

    public FragmentImage(String page_id)
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
        graphApi.getResponse(page_id,"posts{picture}","",true,0);
         mLayoutManager= new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list_image=new ArrayList<>();
        adapter=new image_adapter(getActivity(),list_image);
        gsonBuilder=new GsonBuilder();
        gson=gsonBuilder.create();
    }

    @Override
    public void Object(JSONObject jsonObject, int count) {
        parseing(jsonObject,count);
    }

    private void parseing(JSONObject jsonObject,int count)
    {

if(count==0)
{
    exampleImage=gson.fromJson(jsonObject.toString(),ExampleImage.class);
    list_image.addAll(exampleImage.getPosts().getData());
    progressBar.setVisibility(View.GONE);
    if(exampleImage.getPosts()!=null)
    {

        recyclerView.setAdapter(adapter);
    }
    else
    {
        textView.setVisibility(View.VISIBLE);
        textView.setText("No data Available");
    }
}
else if(count==1)
{
    Log.e("is ","run on");
    count_image = 1;
    posts = gson.fromJson(jsonObject.toString(), Posts.class);
    list_image.addAll(posts.getData());
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

                    if (count_image == 0 && exampleImage.getPosts().getPaging().getNext() != null) {
                        Log.e("on",exampleImage.getPosts().getPaging().getNext());
                        graphApi.getResponse(page_id + "/posts", "picture,likes.limit(1).summary(true),reactions.limit(1).summary(true)",
                                exampleImage.getPosts().getPaging().getCursors().getAfter(), false, 1);
                    }
                    if (count_image == 1 && posts.getPaging().getNext()!=null) {
                        Log.e("next", posts.getPaging().getNext());
                        graphApi.getResponse(page_id + "/posts", "picture,likes.limit(1).summary(true),reactions.limit(1).summary(true)",
                                posts.getPaging().getCursors().getAfter(), false, 1);
                    }

                    loading = true;
                }
            }
        });


    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void Object(JSONObject jsonObject) {

    }


}
