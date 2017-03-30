package com.example.scindapsus.vp.browse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.scindapsus.R;
import com.example.scindapsus.global.BaseActivity;
import com.example.scindapsus.global.ScindapsusApplication;

public class BrowseActivity extends BaseActivity {

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

            addFragment(R.id.browse_frame, browseFragment);
        }

        // Create the presenter
        new BrowsePresenter(browseFragment, ((ScindapsusApplication)getApplication()).getAppComponent());
    }
}
