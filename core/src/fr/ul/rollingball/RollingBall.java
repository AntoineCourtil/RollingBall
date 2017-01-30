package fr.ul.rollingball;

import com.badlogic.gdx.Game;

import fr.ul.rollingball.views.GameScreen;
import fr.ul.rollingball.views.SplashScreen;

public class RollingBall extends Game {
    SplashScreen splashScreen;
    GameScreen gameScreen;
    private int width = 1024;
    private int height = 720;

    @Override
    public void create() {
        this.splashScreen = new SplashScreen(this);
        this.gameScreen = new GameScreen(this);

        this.setScreen(this.splashScreen);
    }

    public void displayGame(){
        this.setScreen(this.gameScreen);
    }

    @Override
    public void dispose() {
        this.splashScreen.dispose();
        this.gameScreen.dispose();
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
}