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
        position.y = height-image.getHeight();
        if(vertSpeed!=0) {
            position.y -= vertSpeed;
            if (vertSpeed > 10) {
                vertSpeed -= gravity;
            }
        }
        this.setRectangles();
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
        rectangles[0] = new Rect((int)(position.x+image.getWidth()/3.5),
        (int)position.y+10,
        (int)(position.x+image.getWidth()/3*2.25),
        (int)(position.y+image.getHeight()/3));
    }
}
