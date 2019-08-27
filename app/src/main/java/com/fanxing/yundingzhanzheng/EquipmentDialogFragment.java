package com.fanxing.yundingzhanzheng;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


public class EquipmentDialogFragment extends DialogFragment {
    public String[] equipmentSX = {
            "+50%攻击吸血",//1
            "在承受致命伤害使会进入滞凝状态，净化掉所有负面效果并回复至多500生命值",//2
            "+33%全能吸血",//3
            "暴击造成+200%伤害",//4
            "在释放后，携带者的每次攻击回复自身15%最大法力值",//5
            "每秒钟，携带者有7%几率获得100%暴击几率",//6
            "携带者也是一名【刺客】",//7
            "在战斗环节开始时，为持有者左右两格内的英雄提供15%攻击速度",//8
            "携带者也是一名【剑士】",//9
            "攻击在命中时有较低几率使敌人的星级降低1，持续至本回合战斗结束",//10
            "攻击提供5%攻击速度。可无限叠加",//11
            "携带者会闪避所有暴击",//12
            "携带者的攻击不会被闪避。攻击距离翻倍",//13
            "每3次攻击对3敌人造成100魔法伤害",//14
            "攻击造成基于携带者10%最大生命值的溅射伤害",//15
            "敌人在每次施放技能时，都会承受125伤害",//16
            "在战斗环节开始时，站在同一排2格内的友军获得一层格挡250伤害的护盾",//17
            "技能命中时造成200溅射伤害",//18
            "技能造成燃烧伤害，在10秒内持续造成相当于敌人20%最大生命值的总伤害，并阻止治疗",//19
            "携带者的法术强度属性会增幅50%",//20
            "携带者也是一名【法师】",//21
            "携带者也是一名【恶魔】",//22
            "临近格子的敌人们失去25%攻击速度",//23
            "攻击在命中时33%的概率造成沉默效果，阻止敌人获取法力，持续4秒",//24
            "生命值降至25%以下时，治疗附近所有友军1500生命值",//25
            "携带者在技能释放后回复20法力",//26
            "携带者也是一名【极地生物】",//27
            "攻击造成燃烧效果，在10秒内持续造成相当于敌人20%最大生命值的总伤害，并阻止治疗",//28
            "携带者每秒回复6%已损失生命值",//29
            "在战斗环节开始时，放逐一名敌人5秒",//30
            "携带者也是一名【骑士】",//31
            "攻击有25%几率造成缴械效果",//32
            "被攻击时回敬100%所受伤害",//33
            "获得83%对魔法伤害的抗性",//34
            "攻击可选取1个额外敌人，额外攻击造成75%的伤害。附带普攻效果",//35
            "获得+1队伍规模"//36

    };
    public String[] equipmentName = {
            "饮血剑",//1
            "守护天使",//2
            "海克斯科技枪刃",//3
            "无尽之刃",//4
            "朔极之矛",//5
            "神圣之剑",//6
            "幽梦之灵",//7
            "基克的先驱",//8
            "破败王者之刃",//9
            "诅咒之刃",//10
            "鬼索的狂暴之刃",//11
            "幻影之舞",//12
            "疾射火炮",//13
            "斯塔缇克电刃",//14
            "巨型九头蛇",//15
            "离子火花",//16
            "钢铁烈焰之匣",//17
            "卢登的回声",//18
            "莫雷洛秘典",//19
            "灭世者的死亡之帽",//20
            "悠米",//21
            "暗裔",//22
            "冰霜之心",//23
            "肃静",//24
            "救赎",//25
            "炽天使之拥",//26
            "冰霜之锤",//27
            "蜥蜴长老之赐",//28
            "狂徒铠甲",//29
            "灵风",//30
            "骑士之誓",//31
            "折剑者",//32
            "荆棘之甲",//33
            "巨龙之爪",//34
            "卢安娜的飓风",//35
            "自然之力"//36

    };
    public int[] xj = {20, 20, 20, 20, 200, 20, 20};

    public static EquipmentDialogFragment newInstance(CanData canData) {
        Bundle args = new Bundle();
        args.putSerializable("a", canData);
        EquipmentDialogFragment fragment = new EquipmentDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        window.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.black_bg));
        window.setWindowAnimations(R.style.dialog_animation);
        //设置边距
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout((int) (dm.widthPixels * 0.72), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_equipment, container, false);
        Bundle bundle = getArguments();
        CanData canData = (CanData) bundle.getSerializable("a");
        TextView tvEquipment = view.findViewById(R.id.tv_Equipment);
        tvEquipment.setText(equipmentName[canData.CanH - 1]);
        ImageView ivEquipment = view.findViewById(R.id.iv_Equipment);
        ImageView ivA = view.findViewById(R.id.iv_a);
        ImageView ivB = view.findViewById(R.id.iv_b);
        Resources res = getResources();
        int equipment = res.getIdentifier("b" + canData.CanH,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        ivEquipment.setImageResource(equipment);
        int a = res.getIdentifier("a" + canData.a,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        ivA.setImageResource(a);
        int b = res.getIdentifier("a" + canData.b,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        ivB.setImageResource(b);
        TextView tvEquipmentSx = view.findViewById(R.id.tv_Equipment_sx);
        tvEquipmentSx.setText(equipmentSX[canData.CanH - 1]);
        TextView tvSxA = view.findViewById(R.id.tv_sx_a);
        TextView tvSxB = view.findViewById(R.id.tv_sx_b);
        ImageView ivSxA = view.findViewById(R.id.iv_sx_a);
        ImageView ivSxB = view.findViewById(R.id.iv_sx_b);
        int ca = res.getIdentifier("c" + canData.a,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        ivSxA.setImageResource(ca);
        int cb = res.getIdentifier("c" + canData.b,
                "drawable",
                "com.fanxing.yundingzhanzheng");
        ivSxB.setImageResource(cb);
        if (canData.a == 8 && canData.b == 8) {
            tvSxA.setVisibility(View.INVISIBLE);
            tvSxB.setVisibility(View.INVISIBLE);
            ivSxA.setVisibility(View.INVISIBLE);
            ivSxB.setVisibility(View.INVISIBLE);
        } else if (canData.a == 8) {
            tvSxB.setVisibility(View.INVISIBLE);
            ivSxB.setVisibility(View.INVISIBLE);
            if (canData.b == 3||canData.b==2) {
                tvSxA.setText("+" + (xj[canData.b - 1] * 2) + "%");
            } else {
                tvSxA.setText("+" + (xj[canData.b - 1] * 2));
            }
        } else if (canData.b == 8) {
            tvSxB.setVisibility(View.INVISIBLE);
            ivSxB.setVisibility(View.INVISIBLE);
            if (canData.a == 3||canData.a==2) {
                tvSxA.setText("+" + (xj[canData.a - 1] * 2) + "%");
            } else {
                tvSxA.setText("+" + (xj[canData.a - 1] * 2));
            }
        } else if (canData.a == 2 && canData.b == 3||canData.a == 3 && canData.b == 2) {
            tvSxA.setText("+"+xj[canData.a-1]+"%");
            tvSxB.setText("+"+xj[canData.b-1]+"%");
        }else if (canData.a==2||canData.a==3){
            tvSxA.setText("+"+xj[canData.a-1]+"%");
            tvSxB.setText("+"+xj[canData.b-1]);
        }else if (canData.b==2||canData.b==3){
            tvSxA.setText("+"+xj[canData.a-1]);
            tvSxB.setText("+"+xj[canData.b-1]+"%");
        }else {
            tvSxA.setText("+"+xj[canData.a-1]);
            tvSxB.setText("+"+xj[canData.b-1]);
        }

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

}
