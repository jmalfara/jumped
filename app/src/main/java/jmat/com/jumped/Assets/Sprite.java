package jmat.com.jumped.Assets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Sprite {

    private Bitmap spriteSheet;
    private Bitmap sprite;
    private int row = 0;
    private int column = 0;
    private int spriteColumns;
    private int spriteRows;
    private int xCut = 0;
    private int yCut = 0;
    private int height;
    private int width;

    public Sprite(Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        this.spriteSheet = spriteSheet;
        this.spriteColumns = spriteColumns;
        this.spriteRows = spriteRows;
        height = spriteSheet.getHeight();
        width = spriteSheet.getWidth();
        xCut = width/spriteColumns;
        yCut = height/spriteRows;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public int getNumberOfRows () { return spriteRows; }

    public void updateSprite (boolean movingRight){
        int startX = column * xCut;
        int startY = row  * yCut;
        int endX = xCut;
        int endY = yCut;

        sprite = Bitmap.createBitmap(spriteSheet, startX, startY, endX, endY);

        if (!movingRight){
            /* Flip Image */
            Matrix m = new Matrix();
            m.setScale( -1 , 1 );
            sprite = Bitmap.createBitmap(sprite,0,0, sprite.getWidth(), sprite.getHeight(),m,true);
        }
    }

    public void nextSprite() {
        column++;
        if (column >= spriteColumns)
            column = 0;
    }

}
