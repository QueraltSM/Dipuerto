package es.disoft.dicloud.user;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

public class NestedWebView extends WebView implements NestedScrollingChild {
//    private final int[] mScrollOffset = new int[2];
//    private final int[] mScrollConsumed = new int[2];
//    private int mLastY;
//    private int mNestedOffsetY;
    private NestedScrollingChildHelper mChildHelper;

    public NestedWebView(Context context) {
        this(context, null);
    }

    public NestedWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }

    public NestedWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("scroll", "onTouchEvent: " + getScrollY());
        MotionEvent event = MotionEvent.obtain(ev);
        final int action  = MotionEventCompat.getActionMasked(event);
//        if (action == MotionEvent.ACTION_DOWN) mNestedOffsetY = 0;
//        int eventY = (int) event.getY();

//        event.offsetLocation(0, mNestedOffsetY);

        switch (action) {
//            case MotionEvent.ACTION_MOVE:
//                int deltaY = mLastY - eventY;
//
//                // NestedPreScroll
//                if (dispatchNestedPreScroll(0, deltaY, mScrollConsumed, mScrollOffset)) {
//                    deltaY -= mScrollConsumed[1];
//                    mLastY = eventY - mScrollOffset[1];
//                    event.offsetLocation(0, -mScrollOffset[1]);
//                    mNestedOffsetY += mScrollOffset[1];
//                }
//
//                // NestedScroll
//                if (dispatchNestedScroll(0, mScrollOffset[1], 0, deltaY, mScrollOffset)) {
//                    event.offsetLocation(0, mScrollOffset[1]);
//                    mNestedOffsetY += mScrollOffset[1];
//                    mLastY -= mScrollOffset[1];
//                }
//                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                this.getParent().requestDisallowInterceptTouchEvent(true);
        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean isNestedScrollingEnabled() {
        return mChildHelper.isNestedScrollingEnabled();
    }

    // Nested Scroll implements
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,
                                        int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

}