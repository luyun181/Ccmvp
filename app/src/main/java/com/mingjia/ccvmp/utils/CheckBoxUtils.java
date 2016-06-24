package com.mingjia.ccvmp.utils;

import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Administrator on 2016-06-07.
 */
public class CheckBoxUtils {
    public void onlyOneChecked( final CheckBox ck1,  final CheckBox ck2){
        ck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if (ck1.isChecked() ){
            ck2.setChecked(false);
        }
            }
        });
        ck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ck2.isChecked()){
                    ck1.setChecked(false);
                }
            }
        });
    }

}
