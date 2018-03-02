package com.empresa.jlvg89.empresa;

import android.os.PatternMatcher;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private ProgressBar pbProgress;

    /**
     * font: http://emailregex.com/
     */
    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeWidgets();
        showProgressBar(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login_login:
                if(verifyFields()){
                    showProgressBar(true);
                    enableLoginButton(false);


                }else{
                    invalidFieldsSnackBar(view);
                }
                break;
        }
    }

    private void showProgressBar(boolean b) {
        if(b){
            pbProgress.setVisibility(View.VISIBLE);
        }else{
            pbProgress.setVisibility(View.INVISIBLE);
        }
    }

    private void enableLoginButton(boolean b){
        btLogin.setEnabled(b);
    }

    private void initializeWidgets() {
        etEmail         = (EditText) findViewById(R.id.et_login_email);
        etPassword      = (EditText) findViewById(R.id.et_login_password);
        btLogin         = (Button) findViewById(R.id.bt_login_login);
        pbProgress      = (ProgressBar) findViewById(R.id.pb_login_progress);

        btLogin.setOnClickListener(this);
    }



    private void invalidFieldsSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, getString(R.string.invalid_data), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private boolean verifyFields() {
        Pattern p = Pattern.compile(emailRegex);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        Matcher m = p.matcher(email);

        if(m.find() && !password.isEmpty()){
            return true;
        }
        return false;
    }
}
