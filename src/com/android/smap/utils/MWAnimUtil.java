package com.android.smap.utils;

import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MWAnimUtil {


    public static void pulseDown(View view) {
        pulseDown(view, new TransitionBundle());
    }

    public static void pulseDown(View view, TransitionBundle config) {
        if (supportsHoneycomb()) {
            view.setPivotY(1.0f);
            ObjectAnimator a = ObjectAnimator.ofFloat(view, "scaleY", 1.15f,
                    1.0f);
            a.setInterpolator(new DecelerateInterpolator());
            startWithConfig(a, config);
        }
    }
    
    public static void fadeIn(View view) {
        fadeIn(view, new TransitionBundle());
    }

    public static void fadeIn(View view, TransitionBundle c) {
        if (supportsHoneycomb()) {
            ObjectAnimator a = ObjectAnimator
                    .ofFloat(view, "alpha", 0.0f, 1.0f);
            a.setInterpolator(new DecelerateInterpolator());
            startWithConfig(a, c);
        }
    }
    
    private static void startWithConfig(ObjectAnimator a, TransitionBundle tb) {
        a.setDuration(tb.getDuration());

        if (tb.getListener() != null) {
            a.addListener(tb.getListener());
        }

        a.start();

        if (tb.isReversed()) {
            a.reverse();
        }
    }

    private static boolean supportsHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static class TransitionBundle {

        private static final int DEFAULT_ANIM_DURATION = 300;
        private long mDuration;
        private boolean mReverse;
        private AnimatorListener mListener;
        
        public TransitionBundle() {
            mDuration = DEFAULT_ANIM_DURATION;
            mReverse = false;
        }

        public void setDuration(long duration) {
            mDuration = duration;
        }

        public void setReversed(boolean reverse) {
            mReverse = reverse;
        }

        public void setListener(AnimatorListener listener) {
            mListener = listener;
        }

        public long getDuration() {
            return mDuration;
        }

        public boolean isReversed() {
            return mReverse;
        }

        public AnimatorListener getListener() {
            return mListener;
        }
    }

}
