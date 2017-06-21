package com.example.endless.wuziqidemo.Activity;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.endless.wuziqidemo.R;
import com.example.endless.wuziqidemo.View.WuziqiOnline;
import com.example.endless.wuziqidemo.bean.GsonDate;
import com.example.endless.wuziqidemo.bean.Room;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.ValueEventListener;

/**
 * Created by Administrator on 2017/6/19.
 */

public class OnLineActivity extends AppCompatActivity {

    @BindView(R.id.wuziqi)
    WuziqiOnline mWuziqi;
    @BindView(R.id.btn_new)
    Button mBtnNew;
    @BindView(R.id.btn_back)
    Button mBtnBack;

    private ProgressDialog mProgressDialog;
    private BmobRealTimeData mRtd;
    private Gson mGson;
    private int who = 1;
    private AlertDialog mDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        ButterKnife.bind(this);
        mGson = new Gson();
        final View inflate = LayoutInflater.from(this).inflate(R.layout.dailog_createroom, null);
        mDialog = new AlertDialog.Builder(this)
                .setTitle("进入房间")
                .setView(inflate).setCancelable(false)
                .show();
        final RadioButton play = (RadioButton) inflate.findViewById(R.id.player1);

        inflate.findViewById(R.id.btn_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play.isChecked()) {
                    who = 1;
                } else {
                    who = 2;
                }
                TextView roomID = (TextView) inflate.findViewById(R.id.ed_roomid);
                final String id = roomID.getText().toString().trim();
                if (id != null && id.length() > 0) {

                    BmobQuery<Room> query = new BmobQuery<Room>();
                    query.addWhereEqualTo("roomid", Integer.valueOf(id)).addWhereEqualTo("status", 1);
                    query.findObjects(new FindListener<Room>() {
                        @Override
                        public void done(List<Room> list, BmobException e) {
                            if (e == null) {
                                for (Room r : list) {

                                }
                                if (list.size() != 0) {
                                    final Room room = list.get(0);
                                    mRtd = new BmobRealTimeData();
                                    mRtd.start(new ValueEventListener() {
                                        @Override
                                        public void onConnectCompleted(Exception e) {
                                            if (e == null && mRtd.isConnected()) {
                                                mRtd.subRowUpdate("Room", room.getObjectId());
                                                Log.d("bmob", "连接成功:");
                                            }

                                        }

                                        @Override
                                        public void onDataChange(JSONObject jsonObject) {
                                            Log.e("Aaaaaaa", "onDataChange: "+jsonObject );
                                            GsonDate roomBean = mGson.fromJson(jsonObject.toString(), GsonDate.class);

                                            if (roomBean.getStatus() == 2) {
                                                hideProgressDialog();
                                                Room r = new Room();
                                                r.setObjectId(roomBean.getObjectId());
                                                r.setStatus(2);
                                                r.setRoomid(roomBean.getRoomid());
                                                r.setBlackArray((ArrayList<Point>) roomBean.getMBlackArray());
                                                r.setWhiteArray((ArrayList<Point>) roomBean.getMWhiteArray());
                                                r.setGameOver(roomBean.isMIsGameOver());
                                                r.setWhiteWinner(roomBean.isMIsWhiteWinner());
                                                mWuziqi.input(r,who,roomBean.getWho());
                                            }

                                        }
                                    });
                                    room.setStatus(2);
                                    room.update(new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                Toast.makeText(getApplicationContext(), "进入成功", Toast.LENGTH_SHORT).show();
                                                hideProgressDialog();
                                                mDialog.hide();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "进入失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(getApplicationContext(), "该房间已满人", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Room r = new Room();
                                r.setStatus(1);
                                r.setWho(1);
                                r.setRoomid(Integer.valueOf(id));
                                r.save(new SaveListener<String>() {
                                    @Override
                                    public void done(final String s, BmobException e) {
                                        if (e == null) {
                                            mRtd = new BmobRealTimeData();
                                            mRtd.start(new ValueEventListener() {
                                                @Override
                                                public void onConnectCompleted(Exception e) {
                                                    if (e == null && mRtd.isConnected()) {
                                                        mRtd.subRowUpdate("Room", s);
                                                        Log.d("bmob", "连接成功:");
                                                    }

                                                }

                                                @Override
                                                public void onDataChange(JSONObject jsonObject) {
                                                    Log.e("Aaaaaaa", "onDataChange: "+jsonObject );

                                                    GsonDate roomBean = mGson.fromJson(jsonObject.toString(), GsonDate.class);

                                                    if (roomBean.getStatus() == 2) {
                                                        hideProgressDialog();
                                                        Room r = new Room();
                                                        r.setObjectId(roomBean.getObjectId());
                                                        r.setStatus(2);
                                                        r.setRoomid(roomBean.getRoomid());
                                                        r.setBlackArray((ArrayList<Point>) roomBean.getMBlackArray());
                                                        r.setWhiteArray((ArrayList<Point>) roomBean.getMWhiteArray());
                                                        r.setGameOver(roomBean.isMIsGameOver());
                                                        r.setWhiteWinner(roomBean.isMIsWhiteWinner());
                                                        mWuziqi.input(r,who,roomBean.getWho());
                                                    }

                                                }
                                            });
                                            showProgressDialog("等待玩家进入");

                                        } else {
                                            Toast.makeText(getApplicationContext(), "房间创建失败"+e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "失败" , Toast.LENGTH_SHORT).show();


                }
            }
        });


        inflate.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
        mDialog.hide();
    }

    private void showProgressDialog(String s) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(s);
        mProgressDialog.show();

    }
}
