package com.example.dell.facebookmodule.Adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.facebookmodule.List.page_detail_list.page_post_details.Datum;
import com.example.dell.facebookmodule.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class post_adapter extends RecyclerView.Adapter<post_adapter.ShowViewHolder>
{

    private List<Datum> list_post;
    private Context context;

    public post_adapter(List<Datum> list_post, Context context)
    {

        this.context=context;
        this.list_post=list_post;
    }


    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_detail, parent, false);

        return new ShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        holder.count.setText(String.valueOf(position));
        Picasso.with(context).load(list_post.get(position).getPicture())
                .placeholder(R.drawable.ic_image_black_48dp).
                resize((int) (0.5*width), (int) (0.5*width))
                .into(holder.imageView);
        holder.message.setText(list_post.get(position).getMessage());
holder.likes.setText(String.valueOf(list_post.get(position).getLikes().getSummary().getTotalCount()));
 holder.reactions.setText(String.valueOf(list_post.get(position).getReactions().getSummary().getTotalCount()));

    }

    @Override
    public int getItemCount() {

        return list_post.size();
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder {
        TextView message,likes,reactions,count;
        ImageView imageView;
        public ShowViewHolder(View itemView) {
            super(itemView);
            count= (TextView) itemView.findViewById(R.id.count);
           imageView= (ImageView) itemView.findViewById(R.id.image_post_view);
            message= (TextView) itemView.findViewById(R.id.text_desp);
            likes= (TextView) itemView.findViewById(R.id.text_post_like);
            reactions= (TextView) itemView.findViewById(R.id.text_post_reaction);
        }
    }
}
