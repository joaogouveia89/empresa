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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.empresa.jlvg89.empresa.R;
import com.empresa.jlvg89.empresa.models.Enterprise;
import com.empresa.jlvg89.empresa.models.QueryResult;
import com.empresa.jlvg89.empresa.network.RetrofitService;
import com.empresa.jlvg89.empresa.network.ServiceGenerator;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchableActivity extends AppCompatActivity implements Callback<QueryResult>{

    private static final String TAG = "SearchableActivity";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        initializeWidgets();
        setSearchActionBar(toolbar);

        handleSearch(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchable_activity, menu);
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


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch(intent);
    }


    /**
     * TRATAMENTO DA QUERY DIGITADA PELO USUÁRIO
     * @param intent
     */
    public void handleSearch(Intent intent){
        if(Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i(TAG, query);
            HashMap<String, String> headers = new HashMap<String, String>();
            SharedPreferences preferences = getSharedPreferences("empresas", MODE_PRIVATE);

            // verificando se todos os headers necessários existem
            if(preferences.getString("access-token", null) != null &&
                    preferences.getString("client", null) != null &&
                    preferences.getString("uid", null) != null){

                requestEnterprises(query, headers, preferences);
            }
        }
    }

    /**
     * FAZ A REQUISIÇÃO PARA BUSCAR AS EMPRESAS BASEADO NA BUSCA DO USUÁRIO
      */
    private void requestEnterprises(String query, HashMap<String, String> headers, SharedPreferences preferences) {
        headers.put("access-token", preferences.getString("access-token", null));
        headers.put("client", preferences.getString("client", null));
        headers.put("uid", preferences.getString("uid", null));
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class, headers);

        Call<QueryResult> call = service.fetchEnterprisesByName(query);
        call.enqueue(this);
    }

    private void setSearchActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void initializeWidgets() {
        toolbar = (Toolbar) findViewById(R.id.tb_searchable_search);
    }

    @Override
    public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
        Log.i(TAG,  response.body().getEnterprises().get(0).toString());
    }

    @Override
    public void onFailure(Call<QueryResult> call, Throwable t) {
        Log.i(TAG, "ERROR: " + t.getMessage());
    }
}
