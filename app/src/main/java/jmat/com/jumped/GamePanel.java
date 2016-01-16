package jmat.com.jumped;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import jmat.com.jumped.Assets.Background;
import jmat.com.jumped.Unit.Player;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainTread thread;
    private Player player;
    private Background background;
    private boolean stepRight = false;
    private boolean stopSprite = true;

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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void requestPlayerLeft() {
        stepRight = false;
        stopSprite = false;
    }

    public void requestPlayerRight() {
        stepRight = true;
        stopSprite = false;
    }

    public void stopSprite() {
        stopSprite = true;
    }

    //This is where the logic is handled for the game
    public void update(){
      // background.update();
        player.update(stepRight, stopSprite);
        // background.update();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        //background.draw(canvas);
        player.draw(canvas);
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
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
