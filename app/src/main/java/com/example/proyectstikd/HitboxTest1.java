package com.example.proyectstikd;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.Random;

public class HitboxTest1 {
    public PointF position;
    public Bitmap image;
    public Rect[] rectangles = new Rect[4];
    private Random g;
    public int speed = 0;

    public HitboxTest1(Bitmap image, float x, float y){
        this.image = image;
        this.position = new PointF(x,y);
        g = new Random();
    }

    public void moveHitbox(int height, int width){
        position.x+=speed;
        //if(position.y>height){
        position.y = height-image.getHeight();
        //}
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
