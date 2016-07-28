package jmat.com.jumped;

import android.graphics.Point;

import com.jmat.graphicslibrary.CanvasObject;
import com.jmat.graphicslibrary.Sprite;

/**
 * Test Object for test purposes
 * Created by Jordan on 7/27/2016.
 */
public class TestObject extends CanvasObject {
    public TestObject(Sprite spriteSheet, Point location, boolean collidable) {
        super(spriteSheet, location, collidable);
    }

    @Override
    public void update() {
        super.update();
        getLocation().x += 10;
        if (getLocation().x > 3000) {
            onDestroy(100);
        }
    }
}
