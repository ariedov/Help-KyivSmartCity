package com.dleibovich.emergency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.dleibovich.R;
import com.dleibovich.preferences.PreferenceActivity;

public class EmergencyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);

        Button callEmergency = (Button) findViewById(R.id.call_emergency_btn);
        callEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmergencyActivity.this, ChooseAddressActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.emergency_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_preferences:
                startActivity(new Intent(this, PreferenceActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
