package com.example.dell.facebookmodule.pages.details;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dell.facebookmodule.module.MainActivity;
import com.example.dell.facebookmodule.R;
import com.example.dell.facebookmodule.fragment.FragmentImage;
import com.example.dell.facebookmodule.fragment.FragmentPost;
import com.example.dell.facebookmodule.fragment.FragmentVideo;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

public class FacebookTabLayout extends AppCompatActivity
{

    Intent intent;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String page_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent=getIntent();
        if(intent!=null)
        {
            page_id=intent.getStringExtra("page_id");
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
    }
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
            //  startActivity(new Intent(Facebook_page.this,CollectionDemoActivity.class));
            LoginManager.getInstance().logOut();
            startActivity(new Intent(FacebookTabLayout.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));

        }
        else if(id==android.R.id.home)
        {
            finish();
        }
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Log.e("page ","call");
        adapter.addFragment(new FragmentPost(page_id), "POST");
        adapter.addFragment(new FragmentImage(page_id), "IMAGE");
        adapter.addFragment(new FragmentVideo(page_id),"VIDEO");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
