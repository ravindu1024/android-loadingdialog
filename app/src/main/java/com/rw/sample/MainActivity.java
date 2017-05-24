package com.rw.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rw.loadingdialog.LoadingDialog;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadingDialog pd = new LoadingDialog(this, "loading", Color.RED);
        pd.show();

//        pd.setBackgroundColor(Color.CYAN);
        //pd.setBackgroundDrawable(getResources().getDrawable(R.drawable.rw_rounded_rect_white));



    }
}
