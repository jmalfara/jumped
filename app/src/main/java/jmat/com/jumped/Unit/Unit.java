package jmat.com.jumped.Unit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import jmat.com.jumped.Assets.Sprite;


public class Unit {

    interface onDrawnListener {
        boolean onDrawComplete();
    }

    private int currentHealth;
    private int maxHealth;
    private float movementSpeed;
    private Point location;
    private boolean dead;
    private boolean facingRight;
    private Sprite sprite;

    public Unit(int maxHealth, float movementSpeed, Point location, Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        sprite = new Sprite(spriteSheet, spriteColumns, spriteRows);
        currentHealth = maxHealth;
        this.location = location;
        facingRight = true;
        dead = false;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isDead() {
        return dead;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void addHealth(int health) {
        currentHealth += health;
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0)
            dead = true;
    }

    public void stepLeft(){
        /* Increment the sprite here */
        sprite.nextSprite();
        location.x -= (int)movementSpeed;
        facingRight = false;
    }

    public void stepRight(){
        /* Increment the sprite here */
        sprite.nextSprite();
        location.x += (int)movementSpeed;
        facingRight = true;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void update() {
        
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite.getSprite(facingRight), location.x, location.y, null);
    }



}
