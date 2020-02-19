package com.example.proyectstikd;

import android.graphics.PointF;
import android.graphics.Rect;

public class LayoutTest {
    public Rect[] layRectangles = new Rect[4];
    public int screenWidth;
    public int screenHeight;
    public PointF position;

    public LayoutTest(float x, float y,int screenWidth,int screenHeight){
        this.position = new PointF(x,y);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setLayRectangles();

    }

    public void setLayRectangles(){
        layRectangles[0] = new Rect((int)(position.x+screenWidth/7),
                (int)(position.y+screenHeight/1.7),
                (int)(position.x+screenWidth/4.5),
                (int)(position.y+screenHeight/1.4));
    }
}
