package com.fanxing.yundingzhanzheng;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanxing.yundingzhanzheng.barUtil.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.fl_tab1)
    FrameLayout flTab1;
    @BindView(R.id.fl_tab2)
    FrameLayout flTab2;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_tab2)
    TextView tvTab2;
    @BindView(R.id.fl_fragment)
    FrameLayout flFragment;
    private List<Fragment> mFragments;
    private FragmentManager fm;
    int tag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //如果是横屏了，在这里设置横屏的UI
        } else {
            //否则，在这里设置竖屏的UI
        }

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        Tab1Fragment tab1Fragment = new Tab1Fragment();
        Tab2Fragment tab2Fragment = new Tab2Fragment();
        flTab1.setOnClickListener(this);
        flTab2.setOnClickListener(this);
        mFragments = new ArrayList<>();
        mFragments.add(tab1Fragment);
        mFragments.add(tab2Fragment);

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_fragment, tab1Fragment).commit();
    }


    public void showHow(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            try {
                ft.hide(mFragments.get(i));
            } catch (NullPointerException e) {
                Log.i("er==============", "指定Fragment还没加入FragmentTransaction中");
            }
        }

        if (mFragments.get(index).isAdded()) {
            ft.show(mFragments.get(index));
        } else {
            ft.add(R.id.fl_fragment, mFragments.get(index));
            ft.show(mFragments.get(index));
        }
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) flFragment.getLayoutParams();

        switch (view.getId()) {
            case R.id.fl_tab1:
                if (tag != 1) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    tvTab2.setVisibility(View.VISIBLE);
                    params.height = 0;
                    linearLayout.getBackground().setAlpha(255);
                    showHow(0);
                    tag=1;
                }
                break;
            case R.id.fl_tab2:
                if (tag!=2) {
                    showHow(1);
                    params.height = ConstraintLayout.LayoutParams.MATCH_PARENT;
                    linearLayout.getBackground().setAlpha(0);
                    tvTab2.setVisibility(View.GONE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    tag=2;
                }
                break;
            default:
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //如果是横屏了，在这里设置横屏的UI
        } else {
            //否则，在这里设置竖屏的UI
        }

    }
}
