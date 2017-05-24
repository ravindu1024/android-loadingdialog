package com.rw.sample;

import android.graphics.Color;
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

        LoadingDialog pd = new LoadingDialog(this);
        //pd.setTextPosition(LoadingDialog.TextPosition.Right);
        pd.setMessage("slkvfdkv");


        pd.show();

    }
}
