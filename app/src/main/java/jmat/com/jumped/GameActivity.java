package jmat.com.jumped;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.jmat.graphicslibrary.CanvasObject;
import com.jmat.graphicslibrary.GamePanel;
import com.jmat.graphicslibrary.Sprite;

public class GameActivity extends Activity {
    GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Gets rid of the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Makes the window fullscreen the flags are like options it will append these options
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Can set this to whatever Surface view or content that you want.
        setContentView(R.layout.activity_game);

        gamePanel = (GamePanel)findViewById(R.id.gamePanel);

        setListeners(); 
        TreeObserver();
    }

    private void TreeObserver () {
        ViewTreeObserver viewTreeObserver = gamePanel.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    gamePanel.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }


    public void setListeners () {
        final Button leftButton = (Button)findViewById(R.id.left);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("MOVE", "LEFT");
                return true;
            }
        });

        Button rightButton = (Button)findViewById(R.id.right);
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("MOVE", "RIGHT");
                return true;
            }
        });

        Button basicButton = (Button)findViewById(R.id.fire);
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sprite sprite = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.test_sprite),1,1);
                TestObject testObject = new TestObject(sprite,new Point(0,0),true);
                testObject.setCallback(new CanvasObject.Callback() {
                    @Override
                    public void onActionCall(CanvasObject canvasObject) {

                    }

                    @Override
                    public void onDestroyCall(CanvasObject canvasObject) {
                        Log.d("REMOVED", "Call to Remove");
                        gamePanel.remove(canvasObject);
                    }
                });
                gamePanel.add(testObject);
                Log.d("Fire", "Action");
            }
        });

        Button ability1Button = (Button)findViewById(R.id.ability1);
        ability1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ability 1", "Clicked");
            }
        });

        Button ability2Button = (Button)findViewById(R.id.ability2);
        ability2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ability 2", "Clicked");
            }
        });
    }
}
