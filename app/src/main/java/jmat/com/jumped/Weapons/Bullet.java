package jmat.com.jumped.Weapons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;


/**
 * Created by Patrick Kochan on 1/14/2016.
 *
 * Bullet si the projectile that comes out of the gun and damages the enemy
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
        this.deathTick = deathTick;
    }

    public void move() {
        if (!facingRight) {
            location.x = -1 * speed;
        } else {
            location.y = speed;
        }

    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, location.x, location.y, null);
    }

    public Point getLocation() {
        return location;
    }
    public Bitmap getImage() {
        return image;
    }
    public int getSpeed() {
        return speed;
    }
    public int getDeathTick() {
        return deathTick;
    }



}
