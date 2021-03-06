package com.example.proyectstikd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Random;

public class GameplayTest extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    private Context context;
    private firstThread physics;
    private int screenWidth = 1;
    private int screenHeight = 1;
    private boolean funcionando = false;
    HitboxTest1 test1;
    LayoutTest layTest1;
    Bitmap bitmapTest1;



    public GameplayTest(Context context) {
        super(context);

        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.context = context;
        physics = new firstThread();
        setFocusable(true);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        Paint p1 = new Paint();
    }

    public void updatePhysics(){
        test1.moveHitbox(screenHeight,screenWidth);
    }

    public void drawIt(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        try{
            c.drawColor(Color.GREEN);
            c.drawBitmap(test1.image,test1.position.x,test1.position.y,null);
            //c.drawRect(test1.rectangles[0],p);
            p.setColor(Color.RED);

            c.drawRect(layTest1.layRectangles[0],p);
            c.drawRect(layTest1.layRectangles[1],p);
            c.drawRect(layTest1.layRectangles[2],p);
            c.drawRect(layTest1.layRectangles[3],p);
            c.drawRect(layTest1.layRectangles[4],p);
            c.drawRect(layTest1.layRectangles[5],p);
            c.drawRect(layTest1.layRectangles[6],p);
            c.drawRect(layTest1.layRectangles[7],p);
            //c.drawRect(new Rect(),null);
            //drawIt(c);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        physics.setFuncionando(true);
        if(physics.getState()==Thread.State.NEW)physics.start();
        if(physics.getState()==Thread.State.TERMINATED){
            physics = new firstThread();
            physics.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //screenWidth = width;
        //screenHeight = height;
        physics.setSurfaceSize(screenWidth,screenHeight);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        physics.setFuncionando(false);
        try{
            physics.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        synchronized (surfaceHolder) {
            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if(layTest1.layRectangles[2].contains(x,y)) {
                        test1.setSpeed(10);
                    }
                    if(layTest1.layRectangles[1].contains(x,y)){
                        test1.setSpeed(-10);
                    }
                    if(layTest1.layRectangles[0].contains(x,y)){
                        test1.setVertSpeed(400);//TODO booleanas con fisicas mas realistas (salto acelerado)
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    test1.setSpeed(0);
            }
            return true;
        }
    }

    public class firstThread extends Thread{


        public firstThread(){
        }


        public boolean isFuncionando() {
            return funcionando;
        }

        public void setFuncionando(boolean state) {
            funcionando = state;
        }

        public void setSurfaceSize(int width, int height){
            Log.i("entreo","entro");
            bitmapTest1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.bluesquare);
            test1 = new HitboxTest1(bitmapTest1,0,0);
            layTest1 = new LayoutTest(0,0,screenWidth,screenHeight);
        }


        @Override
        public void run(){
            while(funcionando){
                Canvas c = null;
                try{
                    if(!surfaceHolder.getSurface().isValid()) continue;
                    //c = sf.lockCanvas(); lienzo con aceleración de software inferior a API 26
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        c = surfaceHolder.lockHardwareCanvas();
                    } else c = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder){
                        updatePhysics();
                        drawIt(c);
                    }
                } finally{
                    if(c!=null){
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}
