package com.jmat.graphicslibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainTread thread;
    private List<CanvasObject> canvasObjects, bufferedObjects;

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        canvasObjects = new LinkedList<>();
        bufferedObjects = new LinkedList<>();
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        canvasObjects = new LinkedList<>();
        bufferedObjects = new LinkedList<>();
    }

    public GamePanel(Context context) {
        super(context);
        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        canvasObjects = new LinkedList<>();
        bufferedObjects = new LinkedList<>();
    }

    public void add (CanvasObject canvasObject) {
        bufferedObjects.add(canvasObject);
    }

    public void remove(CanvasObject canvasObject) {
        canvasObjects.remove(canvasObject);
    }

    //This is where the logic is handled for the game
    public void update(){
        canvasObjects.addAll(bufferedObjects);
        bufferedObjects.clear();
        try {
            for (CanvasObject object : canvasObjects) {
                object.update();
            }
        } catch (Exception e) {
            // Catch and ignore if iterator is null
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        try {
            for (CanvasObject object : canvasObjects) {
                object.draw(canvas);
            }
        } catch (Exception e) {
            // Catch and ignore if iterator is null
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
