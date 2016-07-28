package com.jmat.graphicslibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.Log;

/**
 * This is the base class for CanvasObject objects
 * Objects that are to be drawn to the screen extend this class
 * @author Jordan Malfara
 */
public abstract class CanvasObject {
    private Point location;
    private Rect bounds;
    private Sprite spriteSheet;
    private Bitmap sprite;
    private boolean collidable;
    private boolean flipped;
    private boolean isGone;
    private Callback mCallback;
    private int framesTillGone;
    private Paint paint;

    public interface Callback {
        void onActionCall(CanvasObject canvasObject);
        void onDestroyCall(CanvasObject canvasObject);
    }

    public void onAction() {
        if (mCallback != null)
            mCallback.onActionCall(this);
    }

    public void setCallback(Callback mCallback) { this.mCallback = mCallback; }

    public CanvasObject(Sprite spriteSheet, Point location, boolean collidable) {
        this.spriteSheet = spriteSheet;
        this.location = location;
        this.collidable = collidable;
        this.paint = new Paint();
        sprite = spriteSheet.getSprite();
        isGone = false;
        flipped = false;
        bounds = new Rect(location.x, location.y, sprite.getWidth()+location.x,
                sprite.getHeight()+location.y);
    }

    public boolean getCollidable() { return collidable; }

    public void setSpriteSheet(Sprite spriteSheet) {
        this.spriteSheet = spriteSheet;
        sprite = spriteSheet.getSprite();
    }

    public Paint getPaint() { return paint; }

    public void setPaint(Paint paint) { this.paint = paint; }

    /**
     * Get unit location as Point
     * @return Point
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Function used to get the width of the unit
     * @return float
     */
    public int getWidth() {
        return sprite.getWidth();
    }

    /**
     * Function used to get the height of the unit
     * @return float
     */
    public int getHeight() {
        return sprite.getHeight();
    }

    public void setFlipped(boolean flipped) { this.flipped = flipped; }

    public void onDestroy(int framesTillGone) {
        if (isGone)
            return;

        this.isGone = true;
        this.framesTillGone = framesTillGone;
    }

    public Bitmap getSprite() { return sprite; }

    public Boolean intersects(Rect bounds) {
        return this.bounds.intersect(bounds);
    }

    public Rect getBounds() { return bounds; }

    /**
     * Update the spriteSheet for the next tick
     */
    public void update() {
        if (isGone && framesTillGone <= 0) {
            if (mCallback != null) {
                mCallback.onDestroyCall(this);
            }
            return;
        } else if (isGone) {
            framesTillGone--;
        }

        spriteSheet.updateSprite(flipped);
        sprite = spriteSheet.getSprite();
        bounds.set(location.x, location.y, sprite.getWidth()+location.x,
                sprite.getHeight()+location.y);
    }


    /**
     * Draw the current unit sprite to the canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, location.x, location.y, paint);
    }

}
