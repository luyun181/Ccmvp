package com.mingjia.ccvmp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mingjia.ccvmp.R;

public class InfoActivity extends AppCompatActivity {
    private TextView tv_com_name;
    private TextView tv_com_id;
    private TextView tv_pro_name;
    private String CONFIG = "config";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("基本信息");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        SharedPreferences sp = getSharedPreferences(CONFIG,0);
        String com_name = sp.getString("com_name", "");
        String com_id = sp.getString("com_id", "");
        String pro_name = sp.getString("pro_name","");
        tv_pro_name.setText(pro_name);
        tv_com_name.setText(com_name);
        tv_com_id.setText(com_id);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    private void init(){
        tv_com_id = (TextView) findViewById(R.id.tv_con_id);
        tv_com_name = (TextView) findViewById(R.id.tv_con_name);
        tv_pro_name = (TextView) findViewById(R.id.tv_pro_name);
    }
}
