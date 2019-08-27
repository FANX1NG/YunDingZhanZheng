package com.fanxing.yundingzhanzheng;


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Tab1Fragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tab1)
    ImageView tab1;
    @BindView(R.id.tab2)
    ImageView tab2;
    @BindView(R.id.tab3)
    ImageView tab3;
    @BindView(R.id.tab4)
    ImageView tab4;
    @BindView(R.id.tab5)
    ImageView tab5;
    @BindView(R.id.tab6)
    ImageView tab6;
    @BindView(R.id.tab7)
    ImageView tab7;
    @BindView(R.id.tab8)
    ImageView tab8;
    Unbinder unbinder;
    int[] haveEquipment = new int[8];
    int count = 0;
    @BindView(R.id.gl_have)
    GridLayout glHave;
    int[][] can = {{4, 6, 3, 5, 8, 2, 1, 7}, {6, 13, 11, 14, 15, 12, 10, 9}, {3, 11, 20, 18, 19, 17, 16, 21}, {5, 14, 18, 26, 25, 23, 24, 22}, {8, 15, 19, 25, 29, 28, 30, 27},
            {2, 12, 17, 23, 28, 33, 32, 31}, {1, 10, 16, 24, 30, 32, 34, 35}, {7, 9, 21, 22, 27, 31, 35, 36}};
    int[][] can1 = new int[8][8];
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.rl_can)
    RecyclerView rlCan;
    @BindView(R.id.tv_tis)
    TextView tvTis;

    private List<CanData> mCanH;
    private CanAdapter mAdapter;
    private Vibrator mVibrator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void initView() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
        tab6.setOnClickListener(this);
        tab7.setOnClickListener(this);
        tab8.setOnClickListener(this);
        rlCan.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new CanAdapter(getActivity(), null,getChildFragmentManager());
        rlCan.setItemAnimator(new DefaultItemAnimator());
        rlCan.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addEquipment(int index) {
        count++;
        int width = glHave.getMeasuredWidth();
        int sumSize = width / 6;
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = sumSize;
        params.width = sumSize;
        ImageView imageView = new ImageView(getContext());
        switch (index) {
            case 1:
                imageView.setImageResource(R.drawable.a1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.a2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.a3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.a4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.a5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.a6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.a7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.a8);
                break;
            default:
                break;
        }
        imageView.setPadding(20, 20, 20, 20);
        imageView.setLayoutParams(params);
        glHave.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                glHave.removeView(imageView);
                haveEquipment[index - 1] -= 1;
                count--;
                resultRefresh();
            }
        });

        resultRefresh();
    }

    private void resultRefresh() {
        mVibrator.vibrate(10);

        if (count != 0) {
            tvTis.setVisibility(View.GONE);
            glHave.setVisibility(View.VISIBLE);
        } else {
            tvTis.setVisibility(View.VISIBLE);
            glHave.setVisibility(View.INVISIBLE);
        }
        can1 = new int[8][8];
        int[] clone = haveEquipment.clone();
        for (int i = 0; i < clone.length; i++) {
            for (int j = 0; j < can1.length; j++) {
                if (clone[i] > 0) {
                    can1[i][j] = 1;
                }
            }
            clone[i] -= 1;

            for (int j = 0; j < can1.length; j++) {
                if (clone[j] > 0) {
                    can1[i][j] += 1;
                }
            }
        }
//        tvTest.setText("");
//        for (int i = 0; i < can1.length; i++) {
//            int[] ints = can1[i];
//            for (int j = 0; j < ints.length; j++) {
//                tvTest.setText(tvTest.getText().toString() + ints[j] + " ");
//            }
//            tvTest.setText(tvTest.getText().toString() + "\n");
//        }
        mCanH = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (can1[i][j] == 2) {
                    boolean temp = true;
                    for (int k = 0; k < mCanH.size(); k++) {
                        CanData canData = mCanH.get(k);
                        if (can[i][j] == canData.CanH) {
                            temp = false;
                            break;
                        }

                    }
                    if (temp) {
                        CanData canData = new CanData(can[i][j], i + 1, j + 1);
                        mCanH.add(canData);
                    }
                }

            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (can1[i][j] == 1) {
                    boolean temp = true;
                    for (int k = 0; k < mCanH.size(); k++) {
                        CanData canData = mCanH.get(k);
                        if (can[i][j] == canData.CanH) {
                            temp = false;
                            break;
                        }

                    }
                    if (temp) {
                        if (haveEquipment[i] >= 1) {
                            CanData canData = new CanData(can[i][j], i + 1, j+1);
                            canData.c=1;
                            mCanH.add(canData);
                        } else {
                            CanData canData = new CanData(can[i][j], i+1, j+1);
                            canData.c=2;
                            mCanH.add(canData);
                        }
                    }
                }
            }
        }

        mAdapter.setData(mCanH);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                haveEquipment[0] += 1;
                addEquipment(1);
                break;
            case R.id.tab2:
                haveEquipment[1] += 1;
                addEquipment(2);
                break;
            case R.id.tab3:
                haveEquipment[2] += 1;
                addEquipment(3);
                break;
            case R.id.tab4:
                haveEquipment[3] += 1;
                addEquipment(4);
                break;
            case R.id.tab5:
                haveEquipment[4] += 1;
                addEquipment(5);
                break;
            case R.id.tab6:
                haveEquipment[5] += 1;
                addEquipment(6);
                break;
            case R.id.tab7:
                haveEquipment[6] += 1;
                addEquipment(7);
                break;
            case R.id.tab8:
                haveEquipment[7] += 1;
                addEquipment(8);
                break;
            default:
                break;
        }
    }
}
