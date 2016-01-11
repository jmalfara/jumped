package jmat.com.jumped;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Jordan on 1/10/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainTread thread;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        thread = new MainTread(getHolder(), this);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        thread = new MainTread(getHolder(), this);
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        thread = new MainTread(getHolder(), this);
    }

    public GamePanel(Context context) {
        super(context);

        //SurfaceHolder will intercept events
        getHolder().addCallback(this);
        thread = new MainTread(getHolder(), this);
    }

    public void update(){
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Start the game loop
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
