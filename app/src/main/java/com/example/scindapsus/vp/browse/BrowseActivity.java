package com.example.scindapsus.vp.browse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.util.ActivityUtils;

public class BrowseActivity extends AppCompatActivity {


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BrowseActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_act);

        // attach fragment to main layout
        BrowseFragment browseFragment = (BrowseFragment) getSupportFragmentManager()
                .findFragmentById(R.id.browse_frame);

        if (browseFragment == null) {
            browseFragment = new BrowseFragment();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    browseFragment, R.id.browse_frame);
        }

        // Create the presenter
        new BrowsePresenter(browseFragment, ((ScindapsusApplication)getApplication()).getAppComponent());
    }
}
