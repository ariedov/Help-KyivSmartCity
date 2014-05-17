package com.dleibovich.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.dleibovich.KSCApplication;
import com.dleibovich.R;
import com.dleibovich.emergency.EmergencyActivity;

public class PreferenceActivity extends Activity {

    private Button mSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);

        final EditText userName = (EditText) findViewById(R.id.user_name);
        final EditText userPhone = (EditText) findViewById(R.id.user_phone);
        mSaveData = (Button) findViewById(R.id.save_data);
        mSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveData.setEnabled(false);
                String name = userName.getText().toString();
                String phone = userPhone.getText().toString();
                KSCApplication.getPreferences().setUserData(name, phone);

                Intent intent = new Intent(PreferenceActivity.this, EmergencyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSaveData.setEnabled(true);
    }
}
