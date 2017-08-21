package com.rw.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        final LoadingView loadingView = new LoadingView.Builder(this)
//                                .setProgressStyle(LoadingView.ProgressStyle.HORIZONTAL)
                                .setBackgroundColor(Color.WHITE)
                                .setCustomMargins(0, 100, 100, 0)
                                .setHorizontalBarMarginPercentage(0.2f)
                                .attachTo(frame);

        loadingView.show();


        Button b = (Button) findViewById(R.id.button_failed);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loadingView.setLoadingFailed("Operation failed", "retry", new LoadingView.OnRefreshClickListener()
                {
                    @Override
                    public void onRefreshClicked()
                    {
                        Log.d("IMG", "on refresh");
                    }
                });
            }
        });

        Button b2 = (Button)findViewById(R.id.button_show);
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loadingView.show();
            }
        });

        Button b3 = (Button)findViewById(R.id.button_hide);
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loadingView.hide();
            }
        });



    }
}
