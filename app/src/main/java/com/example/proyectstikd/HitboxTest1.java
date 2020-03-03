package com.example.proyectstikd;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class HitboxTest1 {
    public PointF position;
    public Bitmap image;
    public Rect[] rectangles = new Rect[4];
    private Random g;
    public int gravity = 40;
    public int speed = 0;
    public int vertSpeed = 0;

    public HitboxTest1(Bitmap image, float x, float y){
        this.image = image;
        this.position = new PointF(x,y);
        g = new Random();
        this.setRectangles();
    }

    public void moveHitbox(int height, int width){
        position.x+=speed;
        //if(position.y>height){
        Log.i("muevo", "moveHitbox: "+position.x);
        position.y = height-image.getHeight();
        if(vertSpeed!=0) {
            position.y -= vertSpeed;
            if (vertSpeed > 10) {
                vertSpeed -= gravity;
            }
        }
        setRectangles();
        //}
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVertSpeed(){
        return vertSpeed;
    }

    public void setVertSpeed(int vertSpeed){
        this.vertSpeed = vertSpeed;
    }

    public void setRectangles(){
    }
}
