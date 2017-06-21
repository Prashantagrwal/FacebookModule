package com.example.dell.facebookmodule.Adapters;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dell.facebookmodule.List.page_detail_list.page_video_details.Datum;
import com.example.dell.facebookmodule.R;

import java.util.List;

public class video_adapter extends RecyclerView.Adapter<video_adapter.ShowViewHolder> {
    List<Datum> list_video;
    Context context;
    public video_adapter(List<Datum> list_video, Context context)
    {this.context=context;
        this.list_video=list_video;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_details, parent, false);
        return new ShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {


        MediaController media=new MediaController(context);

        media.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(media);
        Uri myURL =Uri.parse(list_video.get(position).getSource());
        holder.tv_desp.setText(list_video.get(position).getDescription());
       holder.videoView.setVideoURI(myURL);
      //  holder.videoView.start();
        holder.tv_likes.setText(String.valueOf(list_video.get(position).
                getLikes().getSummary().getTotalCount()));
        holder.tv_reactions.setText((String.valueOf(list_video.get(position).
                getReactions().getSummary().getTotalCount())));
    }

    @Override
    public int getItemCount() {
        return list_video.size();
    }


    public class ShowViewHolder extends RecyclerView.ViewHolder {

        TextView tv_desp,tv_likes,tv_reactions;
        VideoView videoView;
        public ShowViewHolder(View itemView)
        {
            super(itemView);

        tv_desp= (TextView) itemView.findViewById(R.id.text_video_desp);
        videoView=(VideoView) itemView.findViewById(R.id.text_video_source);
            tv_likes=(TextView) itemView.findViewById(R.id.text_video_like);
            tv_reactions=(TextView) itemView.findViewById(R.id.text_video_reaction);
        }
    }


}
