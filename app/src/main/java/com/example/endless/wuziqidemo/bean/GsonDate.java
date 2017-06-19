package com.example.endless.wuziqidemo.bean;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class GsonDate {

    /**
     * createdAt : 2017-06-19 20:21:25
     * mBlackArray : []
     * mIsGameOver : false
     * mIsWhiteWinner : false
     * mWhiteArray : []
     * objectId : 66db405e9e
     * roomid : 106
     * status : 2
     * updatedAt : 2017-06-19 20:21:41
     * who : 1
     */

    private String createdAt;
    private boolean mIsGameOver;
    private boolean mIsWhiteWinner;
    private String objectId;
    private int roomid;
    private int status;
    private String updatedAt;
    private int who;
    private List<Point> mBlackArray;
    private List<Point> mWhiteArray;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isMIsGameOver() {
        return mIsGameOver;
    }

    public void setMIsGameOver(boolean mIsGameOver) {
        this.mIsGameOver = mIsGameOver;
    }

    public boolean isMIsWhiteWinner() {
        return mIsWhiteWinner;
    }

    public void setMIsWhiteWinner(boolean mIsWhiteWinner) {
        this.mIsWhiteWinner = mIsWhiteWinner;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public List<?> getMBlackArray() {
        return mBlackArray;
    }

    public void setMBlackArray(List<Point> mBlackArray) {
        this.mBlackArray = mBlackArray;
    }

    public List<?> getMWhiteArray() {
        return mWhiteArray;
    }

    public void setMWhiteArray(List<Point> mWhiteArray) {
        this.mWhiteArray = mWhiteArray;
    }
}
