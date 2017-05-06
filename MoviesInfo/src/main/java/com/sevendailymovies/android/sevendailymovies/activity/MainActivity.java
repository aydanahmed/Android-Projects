package com.sevendailymovies.android.sevendailymovies.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sevendailymovies.android.sevendailymovies.NotificationService;
import com.sevendailymovies.android.sevendailymovies.R;
import com.sevendailymovies.android.sevendailymovies.fragment.AllMovieFragment;
import com.sevendailymovies.android.sevendailymovies.fragment.AppInfoFragment;
import com.sevendailymovies.android.sevendailymovies.fragment.DMCAFragment;
import com.sevendailymovies.android.sevendailymovies.fragment.FavouriteMovieFragment;
import com.sevendailymovies.android.sevendailymovies.fragment.MovieFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private int[] tabIcons = {
            R.drawable.movies,
            R.drawable.top10graphic,
            R.drawable.fav,
            R.drawable.info,
            R.drawable.dmca
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actvity);
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
        tab();
    }

    public void tab() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        for (int i = 0; i < tabIcons.length; i++) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    public void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AllMovieFragment(), "MOVIES");
        adapter.addFrag(new MovieFragment(), "TOP 10");
        adapter.addFrag(new FavouriteMovieFragment(), "Favourite");
        adapter.addFrag(new AppInfoFragment(), "INFO");
        adapter.addFrag(new DMCAFragment(), "DMCA");

        viewPager.setAdapter(adapter);
    }

    //adapter for view pager
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            Log.e("fragmentList", "" + mFragmentList);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}

