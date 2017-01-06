package com.example.administrator.designtest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    RecyclerView  recyclerview;
    RecyclerView.Adapter<MyViewHolder>adapter;
    List<String>texts;
    BottomSheetBehavior<View> behavior;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        texts = new ArrayList<>();
        texts.add("测试1");
        texts.add("测试2");
        texts.add("测试3");
        texts.add("测试4");
        texts.add("测试5");
        texts.add("测试5");
        texts.add("测试5");
        //创建适配器
        adapter = new RecyclerView.Adapter<MyViewHolder>() {
            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false));
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, final int position) {
                holder.getTextView(R.id.text).setText(texts.get(position));
                holder.getTextView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public int getItemCount() {
                return texts.size();
            }
        };
        //初始化recyclerview
        recyclerview.setAdapter(adapter);
        recyclerview.setHasFixedSize(true);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //配置bottomSheet
        View bottomSheet =  findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        //设置监听bottomSheet的状态
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i("tag00","新状态："+newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("tag00","拖动操作："+slideOffset);
            }
        });
    }

    //所有点击事件
    public void designClick(View view){
        switch (view.getId()){
            case R.id.tabLayout:
                startActivity(new Intent(this,TabLayoutActivity.class));
                break;
            case R.id.navigation:
                startActivity(new Intent(this,NavigationActivity.class));
                break;
            case R.id.collasping:
                startActivity(new Intent(this,CollaspingActivity.class));
                break;
            case R.id.floatingaction:
                Snackbar.make(coordinatorLayout,"点击了floatingActionButton",Snackbar.LENGTH_LONG).show();
                break;
            //点击BottomSheet使用,改变状态
            case R.id.design_bottom_sheet:
                int state = behavior.getState();
                if (state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }else{
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
        }
    }

    //用于展示弹窗的list
    public class MyViewHolder extends RecyclerView.ViewHolder{
       private SparseArray<View> array;

        public MyViewHolder(View itemView) {
            super(itemView);
            array = new SparseArray<>();
        }

        private <T extends View> T findViewById(int viewId){
            View view = array.get(viewId);
            if (view == null){
                view = itemView.findViewById(viewId);
                array.put(viewId,view);
            }
            return (T) view;
        }

        private View findView(int viewId){
            return findViewById(viewId);
        }

        public TextView getTextView(int viewid){
            return (TextView)findView(viewid);
        }
    }

}
