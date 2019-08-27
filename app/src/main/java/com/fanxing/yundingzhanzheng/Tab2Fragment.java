package com.fanxing.yundingzhanzheng;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextClock;
import android.widget.TextView;

import com.fanxing.yundingzhanzheng.notMeUtil.Gradual;

public class Tab2Fragment extends Fragment {
    boolean a =true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_tab2, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Gradual gradual=view.findViewById(R.id.gradual);
                gradual.requestLayout();
                gradual.invalidate();
                handler.postDelayed(this,15000);
            }
        },15000);

        Gradual gradul = view.findViewById(R.id.gradual);
        TextView textView = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView1);
        TextClock textClock = view.findViewById(R.id.tc);

        gradul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a==true){
                    textView.setVisibility(View.GONE);
                    textView1.setVisibility(View.GONE);
                    textClock.setVisibility(View.VISIBLE);
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
                    ((MainActivity)getActivity()).linearLayout.setVisibility(View.GONE);
                    a=false;
                }else {
                    textView.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                    textClock.setVisibility(View.INVISIBLE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
                    ((MainActivity)getActivity()).linearLayout.setVisibility(View.VISIBLE);
                    a=true;
                }
            }
        });
    }
}
