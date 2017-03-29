package com.example.scindapsus.vp.browse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scindapsus.R;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.util.ActivityUtils;

public class BrowseActivity extends AppCompatActivity {

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
