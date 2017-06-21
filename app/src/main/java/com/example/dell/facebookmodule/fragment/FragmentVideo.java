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

import com.example.dell.facebookmodule.Adapters.video_adapter;
import com.example.dell.facebookmodule.List.page_detail_list.page_video_details.Datum;
import com.example.dell.facebookmodule.List.page_detail_list.page_video_details.ExampleVideo;
import com.example.dell.facebookmodule.List.page_detail_list.page_video_details.Videos;
import com.example.dell.facebookmodule.R;
import com.example.dell.facebookmodule.module.GraphApi;
import com.example.dell.facebookmodule.module.ObjectInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentVideo extends Fragment implements ObjectInterface
{
    RecyclerView recyclerView;
    String page_id;
    TextView textView;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    GsonBuilder gsonBuilder;
    Gson gson;
    ExampleVideo exampleVideo=null;
    Videos videos=null;
    video_adapter adapter;
    static int count_video=0;
    List<Datum> list_video;
    GraphApi graphApi;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    GridLayoutManager mLayoutManager;


    public FragmentVideo(String page_id)
    {
        this.page_id=page_id;
    }
ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recycle_view,container,false);

        ids(view);
        return view;
    }

    private void ids(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
        textView= (TextView) view.findViewById(R.id.textView_no_data);
        graphApi=new GraphApi(this);
        graphApi.getResponse(page_id,"videos{description,source,likes.limit(1).summary(true),reactions.limit(1).summary(true)}"
                ,"",true,0);
        gsonBuilder=new GsonBuilder();
        gson=gsonBuilder.create();
        list_video=new ArrayList<>();
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter=new video_adapter(list_video,getActivity());
    }

    @Override
    public void Object(JSONObject jsonObject)
    {
        Log.e("response in fragment",jsonObject.toString());

    }

    @Override
    public void Object(JSONObject jsonObject, int count) {
        parseing(jsonObject,count);
    }

    private void parseing(JSONObject jsonObject,int count)
    {
        if(count==0)
        {
            exampleVideo=gson.fromJson(jsonObject.toString(),ExampleVideo.class);
            list_video.addAll(exampleVideo.getVideos().getData());
            progressBar.setVisibility(View.GONE);
            if(exampleVideo.getVideos()!=null)
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
            count_video = 1;
            videos = gson.fromJson(jsonObject.toString(), Videos.class);
            list_video.addAll(videos.getData());
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

                    if (count_video == 0 && exampleVideo.getVideos().getNext() != null) {
                        Log.e("on",exampleVideo.getVideos().getNext());
                        graphApi.getResponse(page_id + "/videos", "description,likes.limit(1).summary(true),reactions.limit(1).summary(true),source",
                                exampleVideo.getVideos().getPaging().getCursors().getAfter(), false, 1);
                    }
                    if (count_video == 1 && videos.getNext()!=null) {
                        Log.e("next", videos.getNext());
                        graphApi.getResponse(page_id + "/videos", "description,likes.limit(1).summary(true),reactions.limit(1).summary(true),source",
                                videos.getPaging().getCursors().getAfter(), false, 1);
                    }

                    loading = true;
                }
            }
        });

    }
}
