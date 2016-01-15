package jmat.com.jumped;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
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

import jmat.com.jumped.Assets.Background;
import jmat.com.jumped.Unit.Player;

public class Game extends Activity {

    GamePanel gamePanel;
    Player player;
    int viewHeight = 0;

    @Override //IS entry point to the program
    protected void onCreate(Bundle savedInstanceState) {
        //Initialize with super
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
                    int width = gamePanel.getWidth();
                    viewHeight = gamePanel.getHeight();
                    createPlayer();
                    setBackground();
                }
            });
        }
    }

    private void createPlayer() {
        Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheets);
        player = new Player(30,10,(new Point(50,viewHeight - bp.getHeight())),bp,11,2);
        gamePanel.setPlayer(player);
    }

    private void setBackground() {
        Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheets);
        Background bg = new Background(bp);
        gamePanel.setBackground(bg);
    }

    public void setListeners () {
        final Button leftButton = (Button)findViewById(R.id.left);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gamePanel.requestPlayerLeft();
                return true;
            }
        });

        Button rightButton = (Button)findViewById(R.id.right);
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gamePanel.requestPlayerRight();
                return true;
            }
        });

        Button basicButton = (Button)findViewById(R.id.fire);
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
