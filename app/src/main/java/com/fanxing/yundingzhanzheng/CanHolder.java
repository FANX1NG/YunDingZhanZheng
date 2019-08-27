package com.fanxing.yundingzhanzheng;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * @author 繁星
 */
public class CanHolder extends RecyclerView.ViewHolder {

    public final ImageView mIvEquipment;
    public final ImageView mIvA;
    public final ImageView mIvB;
    public final ImageView mIvANo;
    public final ImageView mIvBNo;
//    public final ImageView mIvBS;
//    public final ImageView mIvAS;
//    public final TextView mTvB;
//    public final TextView mTvSX;
//    public final TextView mTvA;
    public final ImageView mIvEquipmentNo;
    public final CardView mCardView;

    public CanHolder(@NonNull View itemView) {
        super(itemView);
        mIvEquipment = itemView.findViewById(R.id.iv_Equipment);
        mIvEquipmentNo = itemView.findViewById(R.id.iv_Equipment_no);
        mIvA = itemView.findViewById(R.id.iv_a);
        mIvB = itemView.findViewById(R.id.iv_b);
        mIvANo = itemView.findViewById(R.id.iv_a_no);
        mIvBNo = itemView.findViewById(R.id.iv_b_no);
        mCardView = itemView.findViewById(R.id.cardView);
//        mIvBS = itemView.findViewById(R.id.iv_bs);
//        mIvAS = itemView.findViewById(R.id.iv_as);
//        mTvSX = itemView.findViewById(R.id.tv_sx);
//        mTvA = itemView.findViewById(R.id.tv_a);
//        mTvB = itemView.findViewById(R.id.tv_b);


    }
}
