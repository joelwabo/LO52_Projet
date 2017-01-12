package com.example.joelw.multimediaserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joelw.multimediaserver.Controller.MainActivity;

public class SettingActivity extends AppCompatActivity {

    Button valider = null;
    EditText ipServer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        valider = (Button) findViewById(R.id.valider);
        ipServer = (EditText) findViewById(R.id.ipServer);
        valider.setOnClickListener(validerListener);
        SharedPreferences preferences = getSharedPreferences("preference", Context.MODE_WORLD_WRITEABLE);
        ipServer.setText(preferences.getString("IPSERVER", "0.0.0.0"));

    }
    private View.OnClickListener validerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences preferences = getSharedPreferences("preference", Context.MODE_WORLD_WRITEABLE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("IPSERVER", ipServer.getText().toString());
            editor.commit();
            Intent mainIntent = new Intent(SettingActivity.this, MainActivity.class);
            setResult(RESULT_OK, mainIntent);
            finish();
        }
    };
}
