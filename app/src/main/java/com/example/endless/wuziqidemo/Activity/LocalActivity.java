package com.example.endless.wuziqidemo.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.endless.wuziqidemo.R;
import com.example.endless.wuziqidemo.View.WuziqiView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/19.
 */

public class LocalActivity extends AppCompatActivity {
    @BindView(R.id.wuziqi)
    WuziqiView mWuziqi;
    @BindView(R.id.btn_backp)
    Button mBtnBackp;
    @BindView(R.id.btn_new)
    Button mBtnNew;
    @BindView(R.id.btn_back)
    Button mBtnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);
        setTitle("五子棋");


    }

    @OnClick({R.id.btn_backp, R.id.btn_new, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_backp:
                mWuziqi.back();
                break;
            case R.id.btn_new:
                mWuziqi.start();
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("确定退出本轮比赛?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    return;
                }
            }).create().show();

            return true;
        }

        return super.onKeyDown(keyCode, event);

    }
}
