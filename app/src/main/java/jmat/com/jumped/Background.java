package jmat.com.jumped;

import android.graphics.Canvas;
import android.graphics.Point;

import com.jmat.graphicslibrary.CanvasObject;
import com.jmat.graphicslibrary.Sprite;

public class Background extends CanvasObject {
    private int x2;
    private int imageWidth;

    public Background(Sprite spriteSheet) {
        super(spriteSheet, new Point(0,0), false);
        imageWidth = getWidth();
        x2 = imageWidth;
    }

    @Override
    public void update() {
        /* don't call super. Because we don't want to update every cycle */
        //super.update();
        getLocation().x -= 5;
        x2-= 5;
        if (getLocation().x <= imageWidth*(-1)) {
            getLocation().x = imageWidth;
        }

        if (x2 <= imageWidth*(-1)) {
            x2 = imageWidth;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(getSprite(), x2, getLocation().y, null);
    }
}

