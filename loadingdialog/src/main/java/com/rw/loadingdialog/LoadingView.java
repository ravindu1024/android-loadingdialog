package com.rw.loadingdialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by ravindu on 14/06/17.
 * Attaches a customisable non ui-blocking loading view to a given parent view
 * or to the root view of the given activity
 *
 */
@SuppressWarnings("WeakerAccess")
public class LoadingView
{
    private RelativeLayout mRootLayout;
    private ViewGroup mTargetView;
    private View background;
    private ProgressBar progress;

    @ColorInt
    private int defaultBackgroundColor = Color.TRANSPARENT;
    @ColorInt
    private int accentColor = Color.RED;

    private Context mContext;
    private boolean touchThroughDisabled = true;


    public LoadingView(Activity activity)
    {
        mTargetView = (ViewGroup) activity.findViewById(android.R.id.content);
        mContext = activity;

        init();
    }

    public LoadingView(Activity activity, @ColorInt int progressColor, @ColorInt int backgroundColor)
    {
        mTargetView = (ViewGroup) activity.findViewById(android.R.id.content);
        mContext = activity;

        this.accentColor = progressColor;
        this.defaultBackgroundColor = backgroundColor;

        init();
    }

    public LoadingView(Activity activity, @ColorRes int progressColor, @ColorRes int backgroundColor, @Nullable String tag)
    {
        mTargetView = (ViewGroup) activity.findViewById(android.R.id.content);
        mContext = activity;

        this.accentColor = ContextCompat.getColor(mContext, progressColor);
        this.defaultBackgroundColor = ContextCompat.getColor(mContext, backgroundColor);

        init();
    }

    public LoadingView(ViewGroup rootView)
    {
        mTargetView = rootView;
        mContext = rootView.getContext();

        init();
    }

    public LoadingView(ViewGroup rootView, @ColorInt int progressColor, @ColorInt int backgroundColor)
    {
        mTargetView = rootView;
        mContext = rootView.getContext();

        this.accentColor = progressColor;
        this.defaultBackgroundColor = backgroundColor;

        init();
    }

    public LoadingView(ViewGroup rootView, @ColorRes int progressColor, @ColorRes int backgroundColor, @Nullable String tag)
    {
        mTargetView = rootView;
        mContext = rootView.getContext();

        this.accentColor = ContextCompat.getColor(mContext, progressColor);
        this.defaultBackgroundColor = ContextCompat.getColor(mContext, backgroundColor);

        init();
    }

    private void init()
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootLayout = (RelativeLayout) inflater.inflate(R.layout.rw_layout_progress, mTargetView, false);

        background = mRootLayout.findViewById(R.id.rw_loadingview_background);
        progress = (ProgressBar) mRootLayout.findViewById(R.id.rw_loadingview_progress);

        mRootLayout.setOnTouchListener(touchListener);

        if(accentColor == -1)
            accentColor = Utils.getAccentColor(mContext);

        if(accentColor != -1)
            setProgressColor(accentColor);



        resetBackgroundColor();

    }

    /**
     * Sets a margin to the coloured background (not the full view)
     * @param left  left margin in pixels
     * @param top   top margin in pixels
     * @param right right margin in pixels
     * @param bottom bottom margin in pixels
     */
    public void setCustomMargins(@Px int left, @Px int top, @Px int right, @Px int bottom)
    {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) background.getLayoutParams();
        lp.setMargins(left, top, right, bottom);
        background.setLayoutParams(lp);
    }

    /**
     * Same as {@link LoadingView#setCustomMargins(int, int, int, int)}.
     * '0' can be passed to indicate no margins.
     * @see LoadingView#setCustomMargins(int, int, int, int)
     * @param left left margin dimension
     * @param top top margin dimension
     * @param right right margin dimension
     * @param bottom bottom margin dimension
     */
    public void setCustomMarginDimensions(@DimenRes int left, @DimenRes int top, @DimenRes int right, @DimenRes int bottom)
    {
        Resources resources = mContext.getResources();

        int l = left > 0 ? resources.getDimensionPixelSize(left) : 0;
        int t = top > 0 ? resources.getDimensionPixelSize(top) : 0;
        int r = right > 0 ? resources.getDimensionPixelSize(right) : 0;
        int b = bottom > 0 ? resources.getDimensionPixelSize(bottom) : 0;

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) background.getLayoutParams();
        lp.setMargins(l, t, r, b);
        background.setLayoutParams(lp);
    }

    /**
     * Enable or disable touching the views underneath the LoadingView. Disabled by default.
     * @param enabled pass 'true' to allow touches and 'false' to disable
     */
    public void setTouchThroughEnabled(boolean enabled)
    {
        this.touchThroughDisabled = !enabled;
    }

    /**
     * Show the loadingview by adding it to the top of the given parent view or root
     * view of the given activity
     */
    public void show()
    {
        hide();
        mTargetView.addView(mRootLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * Remove the displayed loadingview
     */
    public void hide()
    {
        mTargetView.removeView(mRootLayout);
    }

    /**
     * Sets the color of the loadingview background
     * @param color background color
     */
    public void setBackgroundColor(@ColorInt int color)
    {
        background.setBackgroundColor(color);
    }

    /**
     * Sets the background color
     * @param color background color resource
     */
    public void setBackgroundColorResource(@ColorRes int color)
    {
        background.setBackgroundColor(ContextCompat.getColor(mContext, color));
    }

    /**
     * Resets the background color to the default.
     * Default color is {@link Color#WHITE} or what was passed in the constructor
     */
    public void resetBackgroundColor()
    {
        background.setBackgroundColor(defaultBackgroundColor);
    }

    /**
     * sets the progress spinner color
     * @param color progress color
     */
    public void setProgressColor(@ColorInt int color)
    {
        if(color == -1)
            resetProgressColor();
        else
        {
            Drawable drawable = progress.getIndeterminateDrawable();
            drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }
    }

    /**
     * sets the progress spinner color
     * @param color progress color resource
     */
    public void setProgressColorResource(@ColorRes int color)
    {
        setProgressColor(ContextCompat.getColor(mContext, color));
    }

    /**
     * resets the progress spinner color to the default.
     * Default is {@link Color#RED} or what was passed in via the constructor
     */
    public void resetProgressColor()
    {
        Drawable drawable = progress.getIndeterminateDrawable();
        drawable.clearColorFilter();
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            return touchThroughDisabled;
        }
    };
}
