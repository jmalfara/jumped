package jmat.com.jumped.Assets;

import android.graphics.Bitmap;

public class Sprite {

    private Bitmap spriteSheet;
    private Bitmap sprite;
    private int row = 0;
    private int column = 0;
    private int spriteColumns;
    private int xCut = 0;
    private int yCut = 0;
    private int height;
    private int width;

    public static Bitmap MergeSprite(Sprite arms, Sprite body) {
        Bitmap bmOverlay = Bitmap.createBitmap(arms.getSprite().getWidth(), body.getSprite().getHeight());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

    public Sprite(Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        this.spriteSheet = spriteSheet;
        this.spriteColumns = spriteColumns;
        height = spriteSheet.getHeight();
        width = spriteSheet.getWidth();
        xCut = width/spriteColumns;
        yCut = height/spriteRows;
    }

    public Bitmap getSprite(boolean movingRight) {
        if (movingRight) {
            row = 0;
        } else {
            row = 1;
        }

        int startX = column * xCut;
        int startY = row  * yCut;
        int endX = xCut;
        int endY = yCut;

        sprite = Bitmap.createBitmap(spriteSheet, startX, startY, endX, endY);
        return sprite;
    }

    public void nextSprite() {
        column++;
        if (column >= spriteColumns)
            column = 0;
    }

}
