package jmat.com.jumped;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


public class MainTread extends Thread {
    private int FPS = 30;
    private double avergaeFPS;
    private final SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainTread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            //Lock Canvas for update
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Locking", "Error Locking Canvas");
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e){
                        Log.e("Unlocking", "Error Unlocking");
                    }
                }
            }

            // How long it took to update and draw 1 frame in nano seconds
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime >= 0) {
                    sleep(waitTime);
                } else {
                    Log.d("Dropped", "Frame Dropped, Catching Up");
                }
            } catch (InterruptedException e) {
                Log.e ("Wait Time", "Error waiting");
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == FPS) {
                avergaeFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avergaeFPS);
            }
        }
    }
}
