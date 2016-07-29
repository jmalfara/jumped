package com.jmat.graphicslibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * This is the base class for CanvasObject objects
 * Objects that are to be drawn to the screen extend this class
 * @author Jordan Malfara
 */
public abstract class CanvasObject implements Cloneable{
    private Point location;
    private Rect bounds;
    private Sprite sprite;
    private boolean collidable;
    private boolean flipped;
    private boolean isGone;
    private Callback mCallback;
    private int framesTillGone;
    private Paint paint;

    /**
     * Callback interface
     */
    public interface Callback {
        void onActionCall(CanvasObject canvasObject);
        void onDestroyCall(CanvasObject canvasObject);
    }

    /**
     * Generic Action Call. Use on your own terms
     */
    public void onAction() {
        if (mCallback != null)
            mCallback.onActionCall(this);
    }

    /**
     * Set the Callback
     * @param mCallback Callback
     */
    public void setCallback(Callback mCallback) { this.mCallback = mCallback; }

    /**
     * This function is used for cloning and position new CanvasObjects. Better performance!
     * @param location Point
     * @return CanvasObject
     */
    public CanvasObject cloneObject(Point location) {
        CanvasObject newObject = null;
        try {
            newObject = (CanvasObject) this.clone();
            newObject.location = location;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    /**
     * Constructor for CanvasObject
     * @param spriteSheet Sprite
     * @param location Point
     * @param collidable boolean
     */
    public CanvasObject(Sprite spriteSheet, Point location, boolean collidable) {
        this.sprite = spriteSheet;
        this.location = location;
        this.collidable = collidable;
        this.paint = new Paint();
        Bitmap spriteImage = spriteSheet.getCurrentSprite();
        isGone = false;
        flipped = false;
        bounds = new Rect(location.x, location.y, spriteImage.getWidth()+location.x,
                spriteImage.getHeight()+location.y);
    }

    /**
     * Is the CanvasObject Collidable
     * @return boolean
     */
    public boolean getCollidable() { return collidable; }

    /**
     * Set a new spriteSheet
     * @param sprite Sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Get Paint
     * @return Paint
     */
    public Paint getPaint() { return paint; }

    /**
     * Set paint for the sprite bitmap. Usefull for tinting
     * @param paint Paint
     */
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
        return sprite.getCurrentSprite().getWidth();
    }

    /**
     * Function used to get the height of the unit
     * @return float
     */
    public int getHeight() {
        return sprite.getCurrentSprite().getHeight();
    }

    /**
     * Set if the bitmap needs to be flipped.
     * @param flipped boolean
     */
    public void setFlipped(boolean flipped) { this.flipped = flipped; }

    /**
     * This sets in place the time until destroy. It indirectly trigger the onDestroyCall Listener
     * @param framesTillGone The number of frame until the object is destroyed
     */
    public void onDestroy(int framesTillGone) {
        if (isGone)
            return;

        this.isGone = true;
        this.framesTillGone = framesTillGone;
    }

    /**
     * Get Sprite
     * @return Bitmap
     */
    public Sprite getSprite() { return sprite; }

    /**
     * Function used to determine if the bounds of two objects intersect
     * @param bounds Rect
     * @return boolean
     */
    public Boolean intersects(Rect bounds) {
        return this.bounds.intersect(bounds);
    }

    /**
     * Get Bounds
     * @return Rect
     */
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

        sprite.updateSprite(flipped);
        Bitmap spriteImg = sprite.getCurrentSprite();
        bounds.set(location.x, location.y, spriteImg.getWidth()+location.x,
                spriteImg.getHeight()+location.y);
    }


    /**
     * Draw the current unit sprite to the canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite.getCurrentSprite(), location.x, location.y, paint);
    }


}
