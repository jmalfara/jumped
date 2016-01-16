package jmat.com.jumped.Unit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import jmat.com.jumped.Assets.Sprite;

import jmat.com.jumped.Weapons.Gun;
import jmat.com.jumped.Weapons.Melee;

public class Player extends Unit {
    private Melee melee;
    private Gun gun;
    private Sprite arms;
    private Point alignArms;

/* This isnt complete */
    public Player(int maxHealth, float movementSpeed, Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        super(maxHealth, movementSpeed, spriteSheet, spriteColumns, spriteRows);
    }

    @Override
    public void update(boolean isRight, boolean isStopped) {
        super.update(isRight, isStopped);
        alignArms.x = getLocation().x;
        arms.updateSprite(isFacingRight());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (arms != null)
            canvas.drawBitmap(arms.getSprite(), alignArms.x, alignArms.y, null);
    }

    public void applyArmsSprite(Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        arms = new Sprite(spriteSheet, spriteColumns, spriteRows);
        arms.updateSprite(isFacingRight());
        setSpriteWidth(arms.getSprite().getWidth());
        System.out.print("Arms " + (getSpriteHeight()/2 - arms.getSprite().getHeight() / 2));
        alignArms = new Point (0,(getLocation().y + arms.getSprite().getHeight()));
    }




}
