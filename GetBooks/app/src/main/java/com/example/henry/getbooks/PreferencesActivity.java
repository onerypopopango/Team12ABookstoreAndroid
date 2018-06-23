package com.example.henry.getbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences pref;
    EditText ipAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        setTitle(getString(R.string.settings));

        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ipAddressEditText = (EditText) findViewById(R.id.editText_ipAddress);
        ipAddressEditText.setText(pref.getString("IPAddress", "172.17.170.88"));
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);

//        String IPAddress = pref.getString("IPAddress", "172.17.170.88");


    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("IPAddress", ipAddressEditText.getText().toString());
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), BooksListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
