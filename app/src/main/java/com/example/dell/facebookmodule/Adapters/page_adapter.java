package com.example.dell.facebookmodule.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.facebookmodule.List.page_detail_list.page_info.Datum;
import com.example.dell.facebookmodule.R;
import com.example.dell.facebookmodule.pages.details.FacebookTabLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class page_adapter extends RecyclerView.Adapter<page_adapter.ShowVeiwHolder>
{

   private List<Datum> example;
    private Context context;
    public page_adapter(List<Datum> example, Context context)
    {
        this.example=example;
        this.context=context;
    }



    @Override
    public ShowVeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.page_view, parent, false);

        return new ShowVeiwHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowVeiwHolder holder, final int position)
    {
         holder.text_page_name.setText(example.get(position).getName());
        Picasso.with(context).load(example.get(position).getPicture().getData().getUrl()).
                placeholder(R.drawable.ic_image_black_48dp).into(holder.page_profile_image);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FacebookTabLayout.class);
               intent.putExtra("page_id",example.get(position).getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return example.size();
    }

    public class ShowVeiwHolder extends RecyclerView.ViewHolder
    {
        TextView text_page_name;
        ImageView page_profile_image;
        LinearLayout linearLayout;
        ShowVeiwHolder(View itemView) {
            super(itemView);
         text_page_name= (TextView) itemView.findViewById(R.id.text_page_name);
         page_profile_image= (ImageView) itemView.findViewById(R.id.profile_image);
         linearLayout= (LinearLayout) itemView.findViewById(R.id.linear);
        }
    }
}
