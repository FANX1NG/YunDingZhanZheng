package com.fanxing.yundingzhanzheng;

import android.content.Context;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CanAdapter extends RecyclerView.Adapter<CanHolder> {

    private List<CanData> mCanH;
    private final Context mContext;
    private final FragmentManager mManager;
    private View mInflate;
    private Vibrator mVibrator;

    public CanAdapter(Context context, List<CanData> mCanH, FragmentManager childFragmentManager) {
        this.mContext = context;
        this.mCanH = mCanH;
        mManager = childFragmentManager;
    }

    @NonNull
    @Override
    public CanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        mInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_can, viewGroup, false);
        return new CanHolder(mInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CanHolder canHolder, int i) {
        Resources res = mContext.getResources();
        int equipment = res.getIdentifier("b" + mCanH.get(i).CanH,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        canHolder.mIvEquipment.setImageResource(equipment);
        int a = res.getIdentifier("a" + mCanH.get(i).a,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        canHolder.mIvA.setImageResource(a);
        int b = res.getIdentifier("a" + mCanH.get(i).b,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        canHolder.mIvB.setImageResource(b);
        if (mCanH.get(i).c == 0) {
            canHolder.mIvEquipmentNo.setVisibility(View.GONE);
            canHolder.mIvANo.setVisibility(View.GONE);
            canHolder.mIvBNo.setVisibility(View.GONE);
        } else if (mCanH.get(i).c == 1) {
            canHolder.mIvEquipmentNo.setVisibility(View.VISIBLE);
            canHolder.mIvANo.setVisibility(View.GONE);
            canHolder.mIvBNo.setVisibility(View.VISIBLE);
        } else if (mCanH.get(i).c == 2) {
            canHolder.mIvEquipmentNo.setVisibility(View.VISIBLE);
            canHolder.mIvANo.setVisibility(View.VISIBLE);
            canHolder.mIvBNo.setVisibility(View.GONE);
        }
        canHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(10);
                EquipmentDialogFragment fragment = EquipmentDialogFragment.newInstance(mCanH.get(i));
                fragment.show(mManager, "");
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCanH == null ? 0 : mCanH.size();
    }

    public void setData(List<CanData> mCanH) {
        this.mCanH = mCanH;
        notifyDataSetChanged();

    }
}
