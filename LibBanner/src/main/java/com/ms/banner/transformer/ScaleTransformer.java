package com.ms.banner.transformer;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class ScaleTransformer implements ViewPager.PageTransformer {

    private ViewPager viewPager;
    private static final float SCALE_Y = 0.82f;
    private static final float SCALE_X = 0.05f;

    private float mMinScale = 0.72f;

    public void transformPage(View view, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) view.getParent();
        }
        //小于0是右
        float scale = (position < 0)
                ? ((1 - SCALE_Y) * position + 1)
                : ((SCALE_Y - 1) * position + 1);

        float alpha = (position < 0)
                ? ((1 - mMinScale) * position + 1)
                : ((mMinScale - 1) * position + 1);
        int leftInScreen = view.getLeft() - viewPager.getScrollX();
        int centerXInViewPager = leftInScreen + view.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - viewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * SCALE_X / viewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);
        if (scaleFactor > 0) {
            view.setScaleX(scaleFactor);
            view.setScaleY(scale);
            view.setAlpha(alpha);
        }
        Log.i("transformPage", alpha + "");
    }
}
