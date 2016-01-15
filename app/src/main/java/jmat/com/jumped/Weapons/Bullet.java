package jmat.com.jumped.Weapons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;


/**
 * Created by Patrick on 1/14/2016.
 */
public class Bullet {

    private Point location;     //position of the bullet
    private boolean facingRight;//The direction that the bullet is facing
    private Bitmap image;       //The image of the bullet
    private int deathTick;      //The number of frames the that bullet has until it dies
    private int speed;          //The speed per frame of the bullet

    Bullet (Point location, Bitmap image, int deathTick) {
        this.location = location;
        this.image = image;
    }

    public void move() {
        if (!facingRight) {
            location.x = -1 * speed;
        } else {
            location.y = 1;
        }

    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, location.x, location.y, null);
    }



}
