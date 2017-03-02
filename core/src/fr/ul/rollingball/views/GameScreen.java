package fr.ul.rollingball.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fr.ul.rollingball.RollingBall;
import fr.ul.rollingball.dataFactories.SoundFactory;
import fr.ul.rollingball.dataFactories.TextureFactory;
import fr.ul.rollingball.models.GameState;
import fr.ul.rollingball.models.World;

/**
 * Created by Antoine Courtil on 24/01/17.
 */

public class GameScreen extends ScreenAdapter {


    private RollingBall rollingBall;
    private SpriteBatch spriteBatch;
    private Camera camera;
    private World world;
    private GameState gameState;
    private BitmapFont police;
    private float chrono;

    public GameScreen(RollingBall rb) {
        this.rollingBall = rb;
        this.spriteBatch = new SpriteBatch();
        this.world = new World(this, 0);
        this.gameState = new GameState();
        this.chrono = 0;
        this.police = TextureFactory.getInstance().getPolice();


        this.camera = new OrthographicCamera(this.rollingBall.getWidth(), this.rollingBall.getHeight());
        Viewport vp = new FitViewport(this.rollingBall.getWidth(), this.rollingBall.getHeight());
        this.camera.position.set(new Vector2(this.camera.viewportWidth / 2f, this.camera.viewportHeight / 2f), 0f);
        this.camera.update();
        this.spriteBatch.setProjectionMatrix(this.camera.combined);
    }

    public void render(float delta) {

        this.update();

        Texture decor = TextureFactory.getInstance().getDecor();

        this.spriteBatch.begin();

        this.spriteBatch.draw(decor, 0, 0);
        this.world.draw(this.spriteBatch);
        renderTexte();

        this.spriteBatch.end();
    }

    public void update() {

        this.world.getBoule().acceleration(getAcceleration());
        this.world.getBoule().update();

        this.world.eatPills();

        checkGameState();

    }

    public void renderTexte() {
        police.draw(this.spriteBatch, "Score : " + this.gameState.getScore(), this.rollingBall.getWidth() - 250, this.rollingBall.getHeight() - 10);
        police.draw(this.spriteBatch, "" + this.gameState.getTempsRestant(), (this.rollingBall.getWidth() / 2) - 150, this.rollingBall.getHeight() - 10);

        if (this.gameState.isWon()) {
            //police.draw(this.spriteBatch, "VICTOIRE", (this.rollingBall.getWidth() / 2) - 150, this.rollingBall.getHeight() / 2);
            this.spriteBatch.draw(TextureFactory.getInstance().getGagne(), 0, 0);
            police.draw(this.spriteBatch, "Pastilles avalées : " + this.gameState.getNbPillNormale(), (this.rollingBall.getWidth() / 2) - 150, (this.rollingBall.getHeight() / 2) - 60);
        }

        if (this.gameState.isLost()) {
            //police.draw(this.spriteBatch, "Perdu...", (this.rollingBall.getWidth() / 2) - 150, this.rollingBall.getHeight() / 2);
            this.spriteBatch.draw(TextureFactory.getInstance().getPerdu(), 0, 0);
        }
    }

    public void checkGameState() {

        checkBallIsOut();

        checkTempsRestant();

        checkIsWon();

        checkIsLost();
    }

    public void checkBallIsOut(){
        if (this.world.getBoule().isOut()) {
            if (this.gameState.isRunning()) {
                SoundFactory.getInstance().playVictoire(1);
            }
            this.gameState.setVictory();
        }
    }

    public void checkTempsRestant(){
        if (this.gameState.getTempsRestant() <= 0) {
            if (this.gameState.isRunning()) {
                SoundFactory.getInstance().playPerte(1);
            }
            this.gameState.setLoss();
        }

        if (this.gameState.isRunning()) {
            this.gameState.decreaseTemps(Gdx.graphics.getDeltaTime());
        }
    }

    public void checkIsWon(){
        if (this.gameState.isWon()) {
            this.chrono += Gdx.graphics.getDeltaTime();

            if (this.chrono >= 3) {
                this.gameState.newLevel();
                changeLaby(this.world.getLabyCourant() + 1);
                this.chrono = 0;
            }
        }
    }

    public void checkIsLost(){
        if (this.gameState.isLost()) {
            this.chrono += Gdx.graphics.getDeltaTime();

            if (this.chrono >= 3) {
                this.gameState.resetGameState();
                changeLaby(0);
                this.chrono = 0;
            }
        }
    }

    public void changeLaby(int index) {
        World newLevel = new World(this, index);
        this.world = newLevel;
    }

    public void mangePillNormal() {
        this.gameState.mangePillNormal();
    }

    public void mangePillTemps() {
        this.gameState.mangePillTemps();
    }

    public void dispose() {
        this.spriteBatch.dispose();
    }

    public RollingBall getRollingBall() {
        return rollingBall;
    }

    private Vector2 getAcceleration() {
        float accelerometerX = Gdx.input.getAccelerometerY();
        float accelerometerY = Gdx.input.getAccelerometerX();

        if (accelerometerX < 1 && accelerometerX > -1) {
            accelerometerX = 0;
        } else {
            accelerometerX = accelerometerX / 40;
        }

        if (accelerometerY < 1 && accelerometerY > -1) {
            accelerometerY = 0;
        } else {
            accelerometerY = accelerometerY / 40 * -1;
        }

        return new Vector2(accelerometerX, accelerometerY);
    }


}
