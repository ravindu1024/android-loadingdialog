package com.rw.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.rw.loadingdialog.LoadingDialog;
import com.rw.loadingdialog.LoadingView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup retry = (ViewGroup) getLayoutInflater().inflate(R.layout.custom_retry, null);

        ConstraintLayout frame = findViewById(R.id.framelayout);

        final LoadingView loadingView = new LoadingView.Builder(this)
                                .setBackgroundColor(Color.WHITE)
                                .setCustomRetryLayoutResource(R.layout.custom_retry)
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
