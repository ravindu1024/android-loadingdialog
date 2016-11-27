package com.rw.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rw.betterprogress.ProgressDialog;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog pd = new ProgressDialog(this, "progress...", Color.RED);
        //pd.setColors(Color.BLUE, Color.DKGRAY, Color.WHITE);
        pd.setTextPosition(ProgressDialog.TextPosition.Bottom);

        pd.show();

    }
}
