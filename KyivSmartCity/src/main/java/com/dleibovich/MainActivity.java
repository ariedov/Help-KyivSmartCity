package com.dleibovich;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.dleibovich.emergency.EmergencyActivity;
import com.dleibovich.preferences.PreferenceActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finish();
        if (KSCApplication.getPreferences().isSavedUserInfo()) {
            startActivity(new Intent(this, EmergencyActivity.class));
        } else {
            startActivity(new Intent(this, PreferenceActivity.class));
        }
    }
}