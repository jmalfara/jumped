package jmat.com.jumped.Unit;

import android.graphics.Bitmap;
import android.graphics.Point;

import jmat.com.jumped.Weapons.Gun;
import jmat.com.jumped.Weapons.Melee;

public class Player extends Unit {
    private Melee melee;
    private Gun gun;

/* This isnt complete */
    public Player(int maxHealth, float movementSpeed, Point location, Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        super(maxHealth, movementSpeed, location, spriteSheet, spriteColumns, spriteRows);
    }
}
