package com.empresa.jlvg89.empresa.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.empresa.jlvg89.empresa.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!checkUserCredentials()){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * VERIFICA SE O USUÁRIO FEZ O LOGIN
     * @return
     */
    private boolean checkUserCredentials() {
        SharedPreferences preferences = getSharedPreferences("empresas", MODE_PRIVATE);

        if(preferences.getString("access-token", null) != null &&
                preferences.getString("client", null) != null &&
                preferences.getString("uid", null) != null){
            return true;
        }
        return false;
    }
}
