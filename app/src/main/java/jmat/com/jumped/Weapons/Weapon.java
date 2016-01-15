package jmat.com.jumped.Weapons;

import android.graphics.drawable.BitmapDrawable;
import android.view.animation.Animation;


public  abstract class Weapon {
    private int damagePerHit;
    private BitmapDrawable skin;
    private Animation attackAnim;
    private int range;

    public Weapon (int damagePerHit, BitmapDrawable skin, int range) {
        this.damagePerHit = damagePerHit;
        this.skin = skin;
        this.range = range;
    }

    public int getDamage() {
        return damagePerHit;
    }

    public int getRange() {
        return range;
    }
    public abstract void attack();
}