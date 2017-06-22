package com.rw.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.rw.loadingdialog.LoadingDialog;
import com.rw.loadingdialog.LoadingView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LoadingDialog pd = new LoadingDialog(this, "loading", Color.RED);
//        pd.show();
//
//        pd.setBackgroundColor(Color.CYAN);
        //pd.setBackgroundDrawable(getResources().getDrawable(R.drawable.rw_rounded_rect_white));

        FrameLayout frame = (FrameLayout)findViewById(R.id.framelayout);

        LoadingView view = new LoadingView(this, -1, Color.WHITE);
        view.setCustomMarginDimensions(0, R.dimen.margin, 0, 0);
        //view.setBackgroundColor(Color.WHITE);
        view.setTouchThroughEnabled(false);

        view.show();

    }
}
