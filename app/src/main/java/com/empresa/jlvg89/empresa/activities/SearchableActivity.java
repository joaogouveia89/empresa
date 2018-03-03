package com.empresa.jlvg89.empresa.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

public class SearchableActivity extends AppCompatActivity {

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
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch(intent);
    }

    public void handleSearch(Intent intent){
        if(Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i(TAG, query);
        }
    }

    private void setSearchActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void initializeWidgets() {
        toolbar = (Toolbar) findViewById(R.id.tb_searchable_search);
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
}
