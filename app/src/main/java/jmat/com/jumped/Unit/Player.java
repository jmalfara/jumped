package jmat.com.jumped.Unit;

import android.graphics.Bitmap;
import android.graphics.Point;
import jmat.com.jumped.Assets.Sprite;

import jmat.com.jumped.Weapons.Gun;
import jmat.com.jumped.Weapons.Melee;

public class Player extends Unit {
    private Melee melee;
    private Gun gun;
    private Sprite arms;

/* This isnt complete */
    public Player(int maxHealth, float movementSpeed, Point location, Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        super(maxHealth, movementSpeed, location, spriteSheet, spriteColumns, spriteRows);

    }

    public void fire () {
    	/* Put fire sprite here */

    }

    public void flattenSprite () {
 		
    }

    @override
    public void update () {
    	
    }


}
