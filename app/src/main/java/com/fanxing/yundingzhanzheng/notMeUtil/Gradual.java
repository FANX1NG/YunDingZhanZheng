package com.fanxing.yundingzhanzheng.notMeUtil;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
public class Gradual extends View {

    private int animatedValue;
    private int colorEnd;
    private int colorStart;
    private int animatedValue1;

    public Gradual(Context context) {
        super(context);
        init();
        System.out.println(111);
        requestLayout();

    }

    public Gradual(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        System.out.println(222);
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    public void init() {
        postInvalidate();
        ValueAnimator animator=ValueAnimator.ofInt(0,255);
        animator.setDuration(6000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (int) animation.getAnimatedValue();
                if (animatedValue<255) {
                    colorStart = Color.rgb(255, animatedValue, 255 - animatedValue);
                    colorEnd = Color.rgb(animatedValue, 0, 255 - animatedValue);
                }else if (animatedValue==255){
                    ValueAnimator animator1=ValueAnimator.ofInt(0,255);
                    animator1.setDuration(2500);
                    animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            animatedValue1 = (int) animation.getAnimatedValue();
                            colorStart = Color.rgb(255- animatedValue1,255- animatedValue1, animatedValue1);
                            colorEnd = Color.rgb(255,0, animatedValue1);
                            if (animatedValue1==255){
                                ValueAnimator animator2=ValueAnimator.ofInt(0,255);
                                animator2.setDuration(2500);
                                animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation) {
                                        int animatedValue2 = (int) animation.getAnimatedValue();
                                        colorStart = Color.rgb(animatedValue2,0,255);
                                        colorEnd = Color.rgb(255-animatedValue2,0,255);
                                        invalidate();
                                    }
                                });
                                animator2.start();
                            }
                            invalidate();
                        }
                    });
                    animator1.start();
                }
                invalidate();
            }
        });
        animator.start();
    }

    public Gradual(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        System.out.println(333);
        requestLayout();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();

        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(width, 0, 0, 0, new int[]{colorStart,colorEnd}, new float[]{0,1f}, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);

        canvas.drawRect(0, 0, width, height, paint);
    }
}