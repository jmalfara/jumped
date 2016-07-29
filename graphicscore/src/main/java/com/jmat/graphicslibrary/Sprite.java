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

    /**
     * Constructor for Sprite
     * @param spriteSheet Bitmap
     * @param spriteColumns int
     * @param spriteRows int
     */
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

        /* Initialize First Sprite */
        sprite = Bitmap.createBitmap(spriteSheet,
                column * xCut,
                row  * yCut,
                xCut,
                yCut);
    }

    /**
     * Function returns the sprite as a bitmap
     * @return Bitmap
     */
    public Bitmap getCurrentSprite() {
        return sprite;
    }

    /**
     * Function used to update the sprite object. If spriteSheet is larger than 1x1 get the next
     * sprite
     * @param flipped isFlipped
     */
    public void updateSprite (boolean flipped){
        if (spriteRows == 1 && spriteColumns == 1)
            return;

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
    private void nextSprite() {
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