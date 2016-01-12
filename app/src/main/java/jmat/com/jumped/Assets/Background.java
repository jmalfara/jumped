package jmat.com.jumped.Assets;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Background {
    private Bitmap image;
    private int x,y;

    public Background(Bitmap image) {
        this.image = image;
    }

    public void update() {
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,null);
    }
}
