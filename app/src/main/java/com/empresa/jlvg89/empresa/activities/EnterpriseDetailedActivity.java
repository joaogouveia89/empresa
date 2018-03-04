package com.empresa.jlvg89.empresa.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.empresa.jlvg89.empresa.R;

public class EnterpriseDetailedActivity extends AppCompatActivity  implements View.OnClickListener{

    private Toolbar toolbar;

    private ImageView enterpriseImage;
    private TextView enterpriseDescription;
    private ImageButton backButton;
    private TextView enterpriseName;

    private String enterpriseNameText;
    private String enterpriseDescriptionText;
    private String enterprisePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_detailed);
        initializeWidgets();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        retrieveData();

        enterpriseName.setText(enterpriseNameText);
        enterpriseDescription.setText(enterpriseDescriptionText);

        backButton.setOnClickListener(this);
    }

    private void retrieveData() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            enterpriseNameText = extras.getString("enterprise-name");
            enterpriseDescriptionText = extras.getString("enterprise-description");
            enterprisePhoto = extras.getString("enterprise-photo");
        }
    }


    private void initializeWidgets() {
        toolbar                        = (Toolbar) findViewById(R.id.tb_enterprise_detailed);
        enterpriseImage                = (ImageView) findViewById(R.id.iv_enterprise_detailed_logo);
        enterpriseDescription          = (TextView) findViewById(R.id.tv_enterprise_detailed_description);
        backButton                     = (ImageButton) findViewById(R.id.ib_enterprise_detailed_back);
        enterpriseName                 = (TextView) findViewById(R.id.tv_enterprise_detailed_name);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_enterprise_detailed_back:
                this.finish();
        }
    }
}
