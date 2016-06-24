package com.mingjia.ccvmp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingjia.ccvmp.R;
import com.mingjia.ccvmp.utils.DividerItemDecoration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.mingjia.ccvmp.activity.ColdOverViewActivity.getScaleBitmap;

public class ColdPicActivity extends AppCompatActivity {
    private RecyclerView rl_pic;
    private int TAKE_PHOTO_REQUEST_CODE = 1;
    private Bitmap bmp;
    private int temp;
    private Map<Integer,Object> map = new HashMap<>();
    private MyAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_pic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("冷库照片");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_pic = (RecyclerView) findViewById(R.id.rl_pic);

        rl_pic.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new MyAdapter(this);
        rl_pic.setAdapter(adapter);

        rl_pic.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
//        rl_pic.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                bmp = getScaleBitmap(getApplicationContext(), getTempImage().getPath());
                map.put(temp,bmp);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "resultCode==" + resultCode, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            } else {
                // Permission Denied
                Toast.makeText(ColdPicActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class MyAdapter extends RecyclerView.Adapter {

        private final LayoutInflater mLayoutInflater;
        private final Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PicViewHolder(mLayoutInflater.inflate(R.layout.pic_grid_recycler, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof PicViewHolder) {
//                if (null != bmp) {
                    ((PicViewHolder) holder).mImageView.setImageBitmap((Bitmap) map.get(position));
//                }
                ((PicViewHolder) holder).mTextView.setText("图片" + position);
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    public class PicViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;
        LinearLayout mLinear;

        PicViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.iv_pic_recycler);
            mTextView = (TextView) view.findViewById(R.id.tv_pic_show);
//            Log.d("TextViewHolder", "onClick--> position = " + "{txt}" + getPosition() + "param.size==" + param.size());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TextViewHolder", "onClick--> position = " + "{txt}" + getPosition());
                    if (ContextCompat.checkSelfPermission(getApplication(),
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ColdPicActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                TAKE_PHOTO_REQUEST_CODE);
                    } else {
                        callCamera();
                        temp = getPosition();
                    }
                    Toast.makeText(v.getContext(), "click......", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void callCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定存储照片的路径
        Uri imageUri = Uri.fromFile(getTempImage());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }

    public File getTempImage() {
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
