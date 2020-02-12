package com.example.proyectstikd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GameplayTest extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    private Context context;
    private firstThread physics;
    private int screenWidth = 1;
    private int screenHeight = 1;
    private boolean funcionando = false;



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

    }

    public void drawIt(Canvas c) {
        try{
            c.drawColor(Color.WHITE);
            c.drawRect(new Rect(),null);
            drawIt(c);
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

    public class firstThread extends Thread{


        public firstThread(){
        }


        public boolean isFuncionando() {
            return funcionando;
        }

        public void setFuncionando(boolean state) {
            funcionando = state;
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
