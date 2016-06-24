package com.mingjia.ccvmp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mingjia.ccvmp.R;
import com.mingjia.ccvmp.fragment.TextFragment;

public class VerTabThreeActivity extends AppCompatActivity {
    private Fragment fragment1;
    private Fragment fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tab_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("验证小组成员培训记录");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
       fragment1 = fm.findFragmentById(R.id.fragment1);
        if (fragment1 == null) {
            fragment1 = new TextFragment();
            fm.beginTransaction()
                    .add(R.id.fragment1, fragment1)
                    .commit();
        }

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}
