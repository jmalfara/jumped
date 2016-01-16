package jmat.com.jumped.Unit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import jmat.com.jumped.Assets.Sprite;


public class Unit {

    private int currentHealth;
    private int maxHealth;
    private float movementSpeed;
    private Point location;
    private boolean dead;
    private boolean facingRight;
    private Sprite sprite;
    private Sprite arms;
    private int viewWidth;
    private int viewHeight;
    private int spriteWidth;
    private int spriteHeight;

    public Unit(int maxHealth, float movementSpeed, Bitmap spriteSheet, int spriteColumns, int spriteRows) {
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        sprite = new Sprite(spriteSheet, spriteColumns, spriteRows);
        currentHealth = maxHealth;
        facingRight = true;
        dead = false;
        sprite.updateSprite(facingRight);
        setSpriteWidth(spriteSheet.getWidth());
        setSpriteHeight(spriteSheet.getHeight());
    }

    public void setSpriteWidth(int spriteWidth){
        this.spriteWidth = spriteWidth;
    }

    public void setSpriteHeight(int spriteHeight){
        this.spriteHeight = spriteHeight;
    }


    public int getSpriteHeight() { return spriteHeight; }

    public void viewDimensions(int viewWidth, int viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        location = new Point(viewWidth / 2,viewHeight - sprite.getSprite().getHeight() / sprite.getNumberOfRows());
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
        location.x -= (int)(viewWidth * movementSpeed);
        if (location.x < 0) {
            location.x = 0;
        }
        facingRight = false;
    }

    public void stepRight(){
        /* Increment the sprite here */
        sprite.nextSprite();
        location.x += (int)(viewWidth * movementSpeed);
        if (location.x > viewWidth - spriteWidth) {
            location.x = viewWidth - spriteWidth;
        }
        facingRight = true;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void update(boolean isRight, boolean isStopped) {
        if (isStopped)
            return;

        if (!isRight)
            stepLeft();

        if (isRight)
            stepRight();

        sprite.updateSprite(facingRight);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite.getSprite(), location.x, location.y, null);
    }



}
