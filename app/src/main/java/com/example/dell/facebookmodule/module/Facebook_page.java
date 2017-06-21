package com.example.dell.facebookmodule.module;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.facebookmodule.Adapters.page_adapter;
import com.example.dell.facebookmodule.List.page_detail_list.page_info.Datum;
import com.example.dell.facebookmodule.List.page_detail_list.page_info.Example;
import com.example.dell.facebookmodule.R;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Facebook_page extends AppCompatActivity implements ObjectInterface
{

     RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView textView;
    RelativeLayout relative_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        textView= (TextView) findViewById(R.id.textView_no_data);
        relative_layout= (RelativeLayout) findViewById(R.id.relative_layout);
        GraphApi graphApi=new GraphApi(this);
        graphApi.getResponse("accounts{app_id,access_token,name,picture.width(600).height(600)}");
        }

    @Override
    public void Object(JSONObject jsonObject) {
        parsing(jsonObject);
    }

    @Override
    protected void onStart() {
        super.onStart();
    if (new CheckConnection(Facebook_page.this).checkInternetConenction())
    {
        GraphApi graphApi=new GraphApi(this);
        graphApi.getResponse("accounts{app_id,access_token,name,picture.width(600).height(600)}");
    }
    else
    {
        Snackbar.make(relative_layout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
    }

    }

    @Override
    public void Object(JSONObject jsonObject, int count) {

    }

    private void parsing(JSONObject jsonObject)
    {

            GsonBuilder gsonBuilder=new GsonBuilder();
            Gson gson=gsonBuilder.create();
            Example example=gson.fromJson(jsonObject.toString(),Example.class);


            progressBar.setVisibility(View.GONE);
            assert recyclerView != null;

if(example.getAccounts()!=null) {
    List<Datum> list_page=new ArrayList<>();
    list_page.addAll(example.getAccounts().getData());
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    page_adapter adapter = new page_adapter(list_page, Facebook_page.this);
    recyclerView.setAdapter(adapter);

}
else
{
    textView.setVisibility(View.VISIBLE);
    textView.setText("No Data Available");
}
            }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.log_out)
        {
            LoginManager.getInstance().logOut();
            startActivity(new Intent(Facebook_page.this,MainActivity.class));
            finish();
        }
        return true;
    }
}



