package com.empresa.jlvg89.empresa.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PatternMatcher;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.empresa.jlvg89.empresa.R;
import com.empresa.jlvg89.empresa.models.User;
import com.empresa.jlvg89.empresa.network.RetrofitService;
import com.empresa.jlvg89.empresa.network.ServiceGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Headers;
import okhttp3.internal.framed.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
                                                                Callback<User>{

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private ProgressBar pbProgress;

    /**
     * font: http://emailregex.com/
     */
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    /**
     * CICLO DE VIDA DA ACTIVITY, SERÁ CHAMADO QUANDO A ACTIVITY FOR CRIADA
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeWidgets();
        showProgressBar(false);
    }

    /**
     * LISTENER DOS WIDGETS
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login_login:
                if(verifyFields()){
                    showProgressBar(true);
                    enableLoginButton(false);
                    loginRequest();
                }else{
                    showInvalidFieldsSnackBar(view);
                }
                break;
        }
    }

    /**
     * SERÁ CHAMADO ASSIM QUE OBTIVERMOS RESPOSTA DO REQUEST FEITO PELO RETROFIT
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        enableLoginButton(true);
        showProgressBar(false);
        if(response.body() != null){
            if(saveUserCredentials(response.headers())){
                redirectToMainActivity();
            }else{
                showCannotLoginSnackBar(getCurrentFocus());
            }

        }else{
            showInvalidFieldsSnackBar(getCurrentFocus());
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        showNoInternetSnackBar(getCurrentFocus());
    }

    /**
     * CRIA INSTANCIA DO RETROFIT E COLOCA A REQUISIÇÃO NA FILA
     */
    private void loginRequest() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class, null);
        Call<User> call = service.requestLogin(etEmail.getText().toString(),
                                               etPassword.getText().toString());
        call.enqueue(this);
    }

    /**
     * ESCONDE OU MOSTRA O PROGRESS BAR
     * @param b
     */
    private void showProgressBar(boolean b) {
        if(b){
            pbProgress.setVisibility(View.VISIBLE);
        }else{
            pbProgress.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * HABILITA OU DESABILITA O BOTÃO DE LOGIN(PARA EVITAR QUE O USUÁRIO FAÇA VÁRIAS REQUISIÇÕES)
     * @param b
     */
    private void enableLoginButton(boolean b){
        btLogin.setEnabled(b);
    }

    /**
     * INICIALIZA OS ELEMENTOS DA UI
     */
    private void initializeWidgets() {
        etEmail         = (EditText) findViewById(R.id.et_login_email);
        etPassword      = (EditText) findViewById(R.id.et_login_password);
        btLogin         = (Button) findViewById(R.id.bt_login_login);
        pbProgress      = (ProgressBar) findViewById(R.id.pb_login_progress);

        btLogin.setOnClickListener(this);
    }


    /**
     * MOSTRA UM SNACKBAR INDICANDO QUE O EMAIL E/OU SENHA ESTÃO INCORRETOS
     * @param view
     */
    private void showInvalidFieldsSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, getString(R.string.invalid_data), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    /**
     * MOSTRA UM SNACKBAR ALERTANDO O USUÁRIO DA FALTA DE CONEXÃO COM A INTERNET
     * @param currentFocus
     */
    private void showNoInternetSnackBar(View currentFocus) {
        Snackbar snackbar = Snackbar.make(currentFocus, getString(R.string.no_internet), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    /**
     * MOSTRA UM SNACKBAR ALERTANDO O USUÁRIO DE QUE NÃO FOI POSSÍVEL REALIZAR O LOGIN
     * @param currentFocus
     */
    private void showCannotLoginSnackBar(View currentFocus) {
        Snackbar snackbar = Snackbar.make(currentFocus, getString(R.string.cannot_login), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    /**
     * VERIFICA SE OS DADOS DIGITADOS EM E-MAIL E SENHA ESTÃO VÁLIDOS
     * @return
     */
    private boolean verifyFields() {
        Pattern p = Pattern.compile(EMAIL_REGEX);
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        Matcher m = p.matcher(email);

        if(m.find() && !password.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * SALVA OS DADOS DE LOGIN DO USUÁRIO
     * @param headers
     * @return
     */
    private boolean saveUserCredentials(Headers headers) {
        SharedPreferences preferences = getSharedPreferences("empresas", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("access-token", headers.get("access-token"));
        editor.putString("client", headers.get("client"));
        editor.putString("uid", headers.get("uid"));

        return editor.commit();
    }

    /**
     * REDIRECIONA PARA A MAIN ACTIVITY
     */
    private void redirectToMainActivity() {
        Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
