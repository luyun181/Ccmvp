package com.mingjia.ccvmp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.mingjia.ccvmp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ColdOverViewActivity extends AppCompatActivity {
    private ListView lv_table;
    private String[] strs = {"Iamge", "Text"};
    private String[] str = {"冷库基本信息", "冷库结构参数",
            "冷库图片", "冷库平面图", "冷库安装温湿度监测系统终端运行情况确认书要技术参数",
            "制冷机组主要技术参数", "冷库运行情况确认书"};
    private int[] icon = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] icons = {R.id.iv_item, R.id.tv_item};
    public int TAKE_PHOTO_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_over_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        getSupportActionBar().setTitle("冷库概述");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_table = (ListView) findViewById(R.id.lv_clod);

        lv_table.setAdapter(new SimpleAdapter(getApplicationContext(), getData(), R.layout.listview_table_item, strs, icons));

        lv_table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), VerTabOneActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), VerTabTwoActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), ColdPicActivity.class));
                       /* if (ContextCompat.checkSelfPermission(ColdOverViewActivity.this,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ColdOverViewActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    TAKE_PHOTO_REQUEST_CODE);
                        } else {
                            callCamera();
                        }*/

                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(), VerTabFourActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(), VerTabThreeActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(), VerTabThreeActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        startActivity(new Intent(getApplicationContext(), VerTabThreeActivity.class));
                        Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT).show();
                        break;
                    default:
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

    public void callCamera() {
       /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);*/
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定存储照片的路径
        Uri imageUri = Uri.fromFile(getTempImage());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                // Permission Denied
                Toast.makeText(ColdOverViewActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static Bitmap getScaleBitmap(Context ctx, String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, opt);

        int bmpWidth = opt.outWidth;
        int bmpHeght = opt.outHeight;

        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        opt.inSampleSize = 1;
        if (bmpWidth > bmpHeght) {
            if (bmpWidth > screenWidth)
                opt.inSampleSize = bmpWidth / screenWidth;
        } else {
            if (bmpHeght > screenHeight)
                opt.inSampleSize = bmpHeght / screenHeight;
        }
        opt.inJustDecodeBounds = false;

        bmp = BitmapFactory.decodeFile(filePath, opt);
        return bmp;
    }

    public static File getTempImage() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            String filePath = Environment.getExternalStorageDirectory() + File.separator + "ColdStorage";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);
            File desDir = new File(filePath);
            if (!desDir.exists()) {
                desDir.mkdirs();
            }
            File tempFile = new File(desDir, str + ".jpg");

            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tempFile;
        }
        return null;
    }
}
