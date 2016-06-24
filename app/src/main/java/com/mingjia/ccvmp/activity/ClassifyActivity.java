package com.mingjia.ccvmp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mingjia.ccvmp.R;

public class ClassifyActivity extends AppCompatActivity {
    private GridView gd_view;
    private int[] icon = { R.mipmap.jbxx,  R.mipmap.shezhi,
            R.mipmap.yanzheng,  R.mipmap.shujucaiji,
            R.mipmap.lengkuyanzheng, R.mipmap.budian,
            R.mipmap.piancha};
    private String[] iconName = {
            "基本信息",
            "验证系统及设备",
            "验证组织与管理表",
            "数据采集要求",
            "冷库概述",
            "证仪布点方案",
            "偏差处理确认"  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("冷链验证系统");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        gd_view.setAdapter(new MyAdapter(this, iconName, icon));
        gd_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), InfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), OrzActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),VerifyActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(), DataCollectActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),ColdOverViewActivity.class));
                        Toast.makeText(getApplicationContext(), "id" + id, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),VerifyActivity.class));
                        Toast.makeText(getApplicationContext(), "id" + id, Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        startActivity(new Intent(getApplicationContext(),VerifyActivity.class));
                        Toast.makeText(getApplicationContext(), "id" + id, Toast.LENGTH_SHORT).show();
                        break;

                }
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
    private  void init(){
        gd_view = (GridView) findViewById(R.id.gd_container);
    }
    public class MyAdapter extends BaseAdapter {

        private String data[]=null;
        private int imgId[]=null;
        private Context context=null;
        private LayoutInflater inflater=null;
        private ImageView view2;
        private TextView view3;
        public MyAdapter(Context context,String[] data, int[] imgId) {
            super();
            this.data = data;
            this.imgId = imgId;
            this.context = context;

            inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view = inflater.inflate(R.layout.gridview_item, null);
            view2 = (ImageView) view.findViewById(R.id.iv_item);
            view3 = (TextView) view.findViewById(R.id.tv_item);

            view3.setText(data[position]);
            view2.setImageResource(imgId[position]);
            return view;
        }

    }
}
