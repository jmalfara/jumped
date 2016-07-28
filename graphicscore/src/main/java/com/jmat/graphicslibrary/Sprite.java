package com.jmat.graphicslibrary;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * This class is used for the creation and maintenance of sprite objects
 * @author Jordan Malfara
 * @version 1.0
 */
public class Sprite {

    private Bitmap spriteSheet;
    private Bitmap sprite;
    private int row;
    private int column;
    private int spriteColumns;
    private int spriteRows;
    private int xCut;
    private int yCut;

    public Sprite(Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        this.spriteSheet = spriteSheet;
        this.spriteColumns = spriteColumns;
        this.spriteRows = spriteRows;
        int height = spriteSheet.getHeight();
        int width = spriteSheet.getWidth();
        xCut = width/spriteColumns;
        yCut = height/spriteRows;
        row = 0;
        column = 0;
        updateSprite(false);
    }

    /**
     * Function returns the sprite as a bitmap
     * @return Bitmap
     */
    public Bitmap getSprite() {
        return sprite;
    }

    /**
     * Returns the number of rows in the spriteSheet
     * @return int
     */
    public int getNumberOfRows () { return spriteRows; }

    /**
     * Function used to update the sprite object.
     * @param flipped isFlipped
     */
    public void updateSprite (boolean flipped){
        nextSprite();
        int startX = column * xCut;
        int startY = row  * yCut;
        int endX = xCut;
        int endY = yCut;

        sprite = Bitmap.createBitmap(spriteSheet, startX, startY, endX, endY);

        if (!flipped){
        /* Flip Image */
            Matrix m = new Matrix();
            m.setScale( -1 , 1 );
            sprite = Bitmap.createBitmap(sprite,0,0, sprite.getWidth(), sprite.getHeight(),m,true);
        }
    }

    /**
     * Function used to increment the sprite to the next image
     */
    public void nextSprite() {
        column++;
        if (column >= spriteColumns) {
            column = 0;
            row++;

            if (row >= spriteRows) {
                column = 0;
                row = 0;
            }
        }
    }
}