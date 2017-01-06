package com.example.administrator.designtest;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    List<BlankFragment>fragmentList;
    List<String>stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_top);
        //添加fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        //添加标题
        stringList = new ArrayList<>();
        stringList.add("热门新闻");
        stringList.add("热门推荐");
        stringList.add("本月热榜");
        stringList.add("今日热榜");
        //添加tab
        tabLayout.addTab(tabLayout.newTab().setText("热门新闻"));
        tabLayout.addTab(tabLayout.newTab().setText("热门推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("本月热榜"));
        tabLayout.addTab(tabLayout.newTab().setText("今日热榜"));
        //适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList,stringList);
        //建立联系
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager,true);


    }
}
