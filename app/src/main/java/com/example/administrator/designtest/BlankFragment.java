package com.example.administrator.designtest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BlankFragment extends Fragment {

    private static String STR1;
    private TextView textView;
    public BlankFragment(){}

    public static BlankFragment newInstance(String str){
        STR1 = str;
        return new BlankFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        textView = (TextView) view.findViewById(R.id.test);
        textView.setText(STR1);
        return view;
    }

}
