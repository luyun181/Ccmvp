package com.mingjia.ccvmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.mingjia.ccvmp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VerifyActivity extends AppCompatActivity {
    private ListView lv_table;
    private String[] strs = {"Iamge", "Text"};
    private String[] str = {"验证领导小组人员及职责列表", "验证实施小组成员和职责列表",
            "验证小组成员培训记录", "验证测试人员签到表", "冷库验证期间进出情况记录表"};
    private int[] icon = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    private int[] icons = {R.id.iv_item, R.id.tv_item};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("验证组织与管理列表");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_table = (ListView) findViewById(R.id.lv_table);
        lv_table.setAdapter(new SimpleAdapter(getApplicationContext(), getData(), R.layout.listview_table_item, strs, icons));

        lv_table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                       startActivity(new Intent(getApplicationContext(),VerTabOneActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(),VerTabTwoActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),VerTabThreeActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),VerTabFourActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),VerTabThreeActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private List<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> sourceList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hashMap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            hashMap.put("Iamge", icon[i]);
            hashMap.put("Text", str[i]);
            sourceList.add(hashMap);
            hashMap = new HashMap<>();
        }
        return sourceList;
    }

}
