package com.example.endless.wuziqidemo.bean;

import android.graphics.Point;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/6/19.
 */

public class Room extends BmobObject{
    int roomid;
    int who;
    int status;//房间状态
    private ArrayList<Point> mWhiteArray = new ArrayList<>();
    private ArrayList<Point> mBlackArray = new ArrayList<>();

    private boolean mIsGameOver;
    private boolean mIsWhiteWinner;

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Point> getWhiteArray() {
        return mWhiteArray;
    }

    public void setWhiteArray(ArrayList<Point> whiteArray) {
        mWhiteArray = whiteArray;
    }

    public ArrayList<Point> getBlackArray() {
        return mBlackArray;
    }

    public void setBlackArray(ArrayList<Point> blackArray) {
        mBlackArray = blackArray;
    }

    public boolean isGameOver() {
        return mIsGameOver;
    }

    public void setGameOver(boolean gameOver) {
        mIsGameOver = gameOver;
    }

    public boolean isWhiteWinner() {
        return mIsWhiteWinner;
    }

    public void setWhiteWinner(boolean whiteWinner) {
        mIsWhiteWinner = whiteWinner;
    }
}
