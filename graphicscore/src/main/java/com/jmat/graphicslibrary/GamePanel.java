package com.jmat.graphicslibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.LinkedList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainTread thread;
    private LinkedList<CanvasObject> canvasObjects, addBuffer, removeBuffer;

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        canvasObjects = new LinkedList<>();
        addBuffer = new LinkedList<>();
        removeBuffer = new LinkedList<>();
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        canvasObjects = new LinkedList<>();
        addBuffer = new LinkedList<>();
        removeBuffer = new LinkedList<>();
    }

    public GamePanel(Context context) {
        super(context);
        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        canvasObjects = new LinkedList<>();
        addBuffer = new LinkedList<>();
        removeBuffer = new LinkedList<>();
    }

    /**
     *  Adds to the add buffer. So that CanvasObject can be added to the screen
     *  @param canvasObject CanvasObject
     */
    public void add (CanvasObject canvasObject) {
        addBuffer.add(canvasObject);
    }

    /**
     * Adds to the remove buffer. So that CanvasObject can be removed from screen
     * @param canvasObject CanvasObject
     */
    public void remove(CanvasObject canvasObject) {
        removeBuffer.add(canvasObject);
    }

    /**
     * Update function used by MainThread
     */
    public void update(){
        // Add Objects
        canvasObjects.addAll(addBuffer);
        addBuffer.clear();

        // Remove Objects
        canvasObjects.removeAll(removeBuffer);
        removeBuffer.clear();

        for (CanvasObject object : canvasObjects) {
            object.update();
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        for (CanvasObject object : canvasObjects) {
            object.draw(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Start the game loop
        if (thread == null) {
            thread = new MainTread(getHolder(), this);
        }
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try{
                thread.setRunning(false);
                thread.join();
                thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
