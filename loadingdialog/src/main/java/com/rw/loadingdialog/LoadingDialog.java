package com.rw.loadingdialog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Displays a fully customisable iOS inspired progress dialog
 * USage:   LoadingDialog dialog = new LoadingDialog(Context, String);
 *          dialog.show();
 * @deprecated Blocking progress dialogs have been depricated by android. see {@link android.app.ProgressDialog}
 */
@SuppressWarnings("WeakerAccess")
public class LoadingDialog
{

    private Context mContext;
    private AlertDialog mDialog;

    private RelativeLayout mContainer;
    private TextView mMessageText;
    private ProgressBar mProgress;

    private String mProgressMessage = "";


    /**
     * Text position (on right (android style) or bottom (ios style) of progress spinner)
     */
    public enum TextPosition
    {
        Right, Bottom
    }

    /**
     *
     * @param ctx the calling activity context
     */
    public LoadingDialog(Context ctx)
    {
        mContext = ctx;

        init(-1, -1, -1, TextPosition.Right);
    }

    /**
     *
     * @param ctx the calling activity context
     * @param message progress message
     */
    public LoadingDialog(Context ctx, String message)
    {
        mContext = ctx;
        mProgressMessage = message;

        init(-1, -1, -1, TextPosition.Right);
    }

    /**
     *
     * @param ctx the calling activity context
     * @param message progress message
     * @param progressColor color of the progress spinner
     */
    public LoadingDialog(Context ctx, String message, @ColorInt int progressColor)
    {
        mContext = ctx;
        mProgressMessage = message;

        init(progressColor, -1, -1, TextPosition.Right);
    }

    /**
     *
     * @param ctx the calling activity context
     * @param message progress message
     * @param progressColor color of the progress spinner
     * @param backgroundColor color of the dialog
     * @param textColor font color
     */
    public LoadingDialog(Context ctx, String message, @ColorInt int progressColor, @ColorInt int backgroundColor, @ColorInt int textColor)
    {
        mContext = ctx;
        mProgressMessage = message;

        init(progressColor, backgroundColor, textColor, TextPosition.Right);
    }

    /**
     *
     * @param ctx the calling activity context
     * @param message progress message
     * @param progressColor color of the progress spinner
     * @param backgroundColor color of the dialog
     * @param textColor font color
     * @param position text position relative to the progress spinner
     */
    public LoadingDialog(Context ctx, String message, @ColorInt int progressColor, @ColorInt int backgroundColor, @ColorInt int textColor, TextPosition position)
    {
        mContext = ctx;
        mProgressMessage = message;

        init(progressColor, backgroundColor, textColor, position);
    }


    /**
     * Initialize the dialog window with the parameters passed via the constructor
     *
     * @param progressColor   Color of the progress spinner circle
     * @param backgroundColor Color of the dialog background drawale
     * @param textColor       Color of the dialog message text
     * @param position        Text position (on right (android style) or bottom (ios style) of progress spinner)
     */
    private void init(@ColorInt int progressColor, @ColorInt int backgroundColor, @ColorInt int textColor, TextPosition position)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
        b.setTitle("");
        b.setCancelable(false);

        @SuppressLint("InflateParams") View v = LayoutInflater.from(mContext).inflate(R.layout.rw_dialog_progress, null);

        mMessageText = (TextView) v.findViewById(R.id.rw_dialog_progress_textView);
        mProgress = (ProgressBar) v.findViewById(R.id.rw_dialog_progress_bar);
        mContainer = (RelativeLayout) v.findViewById(R.id.rw_dialog_container);

        //set colors if passed via constructor
        setProgressColor(Utils.getAccentColor(mContext));
        if (progressColor != -1)
            setProgressColor(progressColor);

        if (textColor != -1)
            setTextColor(textColor);

        if (backgroundColor != -1)
            setBackgroundColor(backgroundColor);
//        else
//            setBackgroundColor(Color.WHITE/* getDefaultThemeBackgroundColor()*/);


        mMessageText.setText(mProgressMessage);

        setTextPosition(position);

        b.setView(v);
        mDialog = b.create();
    }

    /**
     * Set the progress message font color
     *
     * @param color font color
     */
    public void setTextColor(int color)
    {
        mMessageText.setTextColor(color);
    }

    /**
     * Set the progress circle color
     *
     * @param color progress color
     */
    public void setProgressColor(int color)
    {
        Drawable spinner = mProgress.getIndeterminateDrawable();
        spinner.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
    }

    /**
     * Set the dialog bakground color
     *
     * @param color the background color
     */
    public void setBackgroundColor(@ColorInt int color)
    {
        Drawable background = mContainer.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
    }

    /**
     * set a custom background drawable to the dialog
     *
     * @param drawable custom background drawable
     */
    public void setBackgroundDrawable(Drawable drawable)
    {
        mContainer.setBackground(drawable);
    }

    /**
     * Set the progress, background and text colors
     *
     * @param progressColor   progress color
     * @param backgroundColor background color
     * @param textColor       text color
     */
    public void setColors(@ColorInt int progressColor, int backgroundColor, int textColor)
    {
        setProgressColor(progressColor);
        setBackgroundColor(backgroundColor);
        setTextColor(textColor);
    }


    /**
     * Set the progress message without changing text position
     *
     * @param message progress message
     */
    public void setMessage(String message)
    {
        this.mProgressMessage = message;

        mMessageText.setText(mProgressMessage);
    }

    /**
     * Set the progress message with text position
     *
     * @param message  progress message
     * @param position text position
     */
    public void setMessage(String message, TextPosition position)
    {
        setMessage(message);

        setTextPosition(position);
    }

    /**
     * Show the dialog
     */
    public void show()
    {
        mDialog.show();

        Window window = mDialog.getWindow();

        if (window != null)
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * Dismiss the dialog
     */
    public void dismiss()
    {
        mDialog.dismiss();
    }

    /**
     *
     * @return returns true if the dialog is visible
     */
    public boolean isShowing()
    {
        return mDialog.isShowing();
    }

    /**
     * Retrieve the default background color of the current theme of the calling activity
     *
     * @return current default background color
     */
    private
    @ColorInt
    int getDefaultThemeBackgroundColor()
    {
        TypedArray array = mContext.getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.colorBackground,
                android.R.attr.textColorPrimary,
        });

        int ret = array.getColor(0, ContextCompat.getColor(mContext, android.R.color.background_light));

        array.recycle();

        return ret;
    }

    /**
     * set the text position relative to the progress spinner circle
     */
    public void setTextPosition(TextPosition position)
    {

        if(mProgressMessage != null && mProgressMessage.length() > 0)
            mMessageText.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams lpText = (RelativeLayout.LayoutParams) mMessageText.getLayoutParams();
        lpText.removeRule(RelativeLayout.END_OF);
        lpText.removeRule(RelativeLayout.BELOW);
        lpText.topMargin = 0;
        lpText.leftMargin = 0;
        lpText.removeRule(RelativeLayout.TEXT_ALIGNMENT_GRAVITY);

        RelativeLayout.LayoutParams lpProg = (RelativeLayout.LayoutParams) mProgress.getLayoutParams();
        lpProg.removeRule(RelativeLayout.CENTER_HORIZONTAL);

        float density = mContext.getResources().getDisplayMetrics().density;

        if (position == TextPosition.Right)
        {
            lpText.addRule(RelativeLayout.END_OF, R.id.rw_dialog_progress_bar);
            lpText.leftMargin = (int) (5 * density);
            lpText.addRule(RelativeLayout.TEXT_ALIGNMENT_GRAVITY, Gravity.START);
        }
        else
        {
            lpText.addRule(RelativeLayout.BELOW, R.id.rw_dialog_progress_bar);
            lpText.topMargin = (int) (5 * density);
            lpText.addRule(RelativeLayout.TEXT_ALIGNMENT_GRAVITY, Gravity.CENTER_HORIZONTAL);

            lpProg.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }

        mProgress.setLayoutParams(lpProg);
        mMessageText.setLayoutParams(lpText);

        mMessageText.setText(mProgressMessage);
    }



}
