package com.mingjia.ccvmp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingjia.ccvmp.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linear1;
    private LinearLayout linear2;
    private TextView tv_new;
    private TextView tv_name;
    private TextView tv_id;
    private TextView tv_pro_name;
    private EditText et_comp_name;
    private EditText et_comp_id;
    private EditText et_pro_name;
    private Button btn_yes;
    private Button btn_yes1;
    private Button btn_exit;
    private Button btn_exit1;
    String CONFIG = "config";
    SharedPreferences.Editor ed;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        init();
        sp = getSharedPreferences(CONFIG, 0);
        ed = sp.edit();
        if (sp.getString("com_name","")==""){
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.GONE);
        }else{
            linear1.setVisibility(View.GONE);
            linear2.setVisibility(View.VISIBLE);
            tv_name.setText(sp.getString("com_name", ""));
            tv_id.setText(sp.getString("com_id", ""));
            tv_pro_name.setText(sp.getString("pro_name", ""));
        }



        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.GONE);
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        btn_exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String com_name = et_comp_name.getText().toString();
                String com_id = et_comp_id.getText().toString();
                String pro_name = et_pro_name.getText().toString();

                if(!TextUtils.isEmpty(com_name)&&!TextUtils.isEmpty(com_id)&&!TextUtils.isEmpty(pro_name)){
                ed.putString("com_name", com_name);
                ed.putString("com_id", com_id);
                ed.putString("pro_name", pro_name);
                ed.commit();
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.VISIBLE);
                tv_name.setText(sp.getString("com_name", ""));
                tv_id.setText(sp.getString("com_id", ""));
                tv_pro_name.setText(sp.getString("pro_name", ""));
                }else {
                    Toast.makeText(getApplicationContext(),"请输入所需信息！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_yes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Intent intent = new Intent(MainActivity.this, ClassifyActivity.class);
                startActivity(intent);
            }
        });



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void init() {
        linear1 = (LinearLayout) findViewById(R.id.linear_container);
        linear2 = (LinearLayout) findViewById(R.id.linear_container2);
        et_comp_name = (EditText) findViewById(R.id.ed_com_name);
        et_comp_id = (EditText) findViewById(R.id.ed_com_id);
        et_pro_name = (EditText) findViewById(R.id.ed_project_name);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_yes1 = (Button) findViewById(R.id.btn_yes1);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit1 = (Button) findViewById(R.id.btn_exit1);
        tv_new = (TextView) findViewById(R.id.tv_new);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_pro_name = (TextView) findViewById(R.id.tv_pro_name);
    }
}
