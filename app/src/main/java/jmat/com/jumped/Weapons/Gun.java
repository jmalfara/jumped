package jmat.com.jumped.Weapons;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;

public class Gun extends Weapon {

    private Point spawnPoint;   //The location for the bullet
    private Bitmap image;       //Source Image to give the bullet

    public Gun(int damagePerHit, BitmapDrawable skin, Resources image, int imgId, int range, Point spawnPoint) {
        super(damagePerHit, skin, range);
        this.spawnPoint = spawnPoint;
        this.image = BitmapFactory.decodeResource(image,imgId);
    }
    public void attack () {
        //Create a new bullet
        //draw it to the screen at the postition of the gun
        //Make it move across the screen until it dies.
    }

    public Point getSpawnPoint() {
        return spawnPoint;
    }

}
