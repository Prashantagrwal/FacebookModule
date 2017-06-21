package com.example.dell.facebookmodule.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.facebookmodule.List.page_detail_list.post_image_details.Datum;
import com.example.dell.facebookmodule.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class image_adapter extends RecyclerView.Adapter<image_adapter.ShowViewHolder>
{

 private List<Datum> list_image;
 private Context context;

    public image_adapter(Context context, List<Datum> list_image) {
       this.context=context;
        this.list_image=list_image;
    }

    @Override
    public image_adapter.ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_detail, parent, false);

        return new ShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(image_adapter.ShowViewHolder holder, int position)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Picasso.with(context).load(list_image.get(position).getPicture())
                .placeholder(R.drawable.ic_image_black_48dp).
                resize((int) (0.3*width), (int) (0.3*width)).
                into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list_image.size();
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ShowViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
