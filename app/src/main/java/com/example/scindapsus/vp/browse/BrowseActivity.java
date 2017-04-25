package com.example.scindapsus.vp.browse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.scindapsus.R;
import com.example.scindapsus.global.BaseActivity;
import com.example.scindapsus.global.ScindapsusApplication;
import com.example.scindapsus.global.navigation.Navigator;
import com.example.scindapsus.model.User;

public class BrowseActivity extends BaseActivity {

    static String TAG = BrowseActivity.class.getName();

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

        User user = (User) getIntent().getParcelableExtra(Navigator.PARA_MACRO);

    }
}
