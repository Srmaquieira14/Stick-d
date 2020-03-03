package com.example.proyectstikd;

import android.graphics.PointF;
import android.graphics.Rect;

public class LayoutTest {
    public Rect[] layRectangles = new Rect[8];
    public int screenWidth;
    public int screenHeight;
    public PointF position;

    public LayoutTest(float x, float y,int screenWidth,int screenHeight){
        this.position = new PointF(x,y);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setLayRectangles();

    }

    public void setLayRectangles(){//De la izquierda superior a la derecha inferior siempre
        //Cruceta direccional
        layRectangles[0] = new Rect((int)(position.x+screenWidth/20*2.5),//Movimiento arriba
                (int)(position.y+screenHeight/10*5),
                (int)(position.x+screenWidth/20*4),
                (int)(position.y+screenHeight/10*6.5));
        layRectangles[1] = new Rect((int)(position.x+screenWidth/20*1),//Movimiento izquierda
                (int)(position.y+screenHeight/10*6.5),
                (int)(position.x+screenWidth/20*2.5),
                (int)(position.y+screenHeight/10*8));
        layRectangles[2] = new Rect((int)(position.x+screenWidth/20*4),//Movimiento derecha
                (int)(position.y+screenHeight/10*6.5),
                (int)(position.x+screenWidth/20*5.5),
                (int)(position.y+screenHeight/10*8));
        layRectangles[3] = new Rect((int)(position.x+screenWidth/20*2.5),//Movimiento abajo
                (int)(position.y+screenHeight/10*8),
                (int)(position.x+screenWidth/20*4),
                (int)(position.y+screenHeight/10*9.5));

        //Botones de acción
        layRectangles[4] = new Rect((int)(position.x+screenWidth/20*12.5),//Puñetazo 1
                (int)(position.y+screenHeight/10*5),
                (int)(position.x+screenWidth/20*14),
                (int)(position.y+screenHeight/10*6.5));
        layRectangles[5] = new Rect((int)(position.x+screenWidth/20*12.5),//Patada 1
                (int)(position.y+screenHeight/10*8),
                (int)(position.x+screenWidth/20*14),
                (int)(position.y+screenHeight/10*9.5));

        //Botones especiales
        layRectangles[6] = new Rect((int)(position.x+screenWidth/20*16.5),//Esp 1
                (int)(position.y+screenHeight/10*5),
                (int)(position.x+screenWidth/20*18),
                (int)(position.y+screenHeight/10*6.5));
        layRectangles[7] = new Rect((int)(position.x+screenWidth/20*16.5),//Esp 2
                (int)(position.y+screenHeight/10*8),
                (int)(position.x+screenWidth/20*18),
                (int)(position.y+screenHeight/10*9.5));
    }
}
