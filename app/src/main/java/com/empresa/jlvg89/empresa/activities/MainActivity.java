package com.empresa.jlvg89.empresa.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.empresa.jlvg89.empresa.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!checkUserCredentials()){
            redirectToLogin();
        }
        initializeWidgets();
        setSearchActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            searchView = (SearchView) item.getActionView();
        }else{
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));

        return true;
    }

    private void setSearchActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void initializeWidgets() {
        toolbar = (Toolbar) findViewById(R.id.tb_search);
    }


    /**
     * REDIRECIONA PARA A TELA DE LOGIN
     */
    private void redirectToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
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
