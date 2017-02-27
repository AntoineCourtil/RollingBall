package fr.ul.rollingball.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fr.ul.rollingball.RollingBall;
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

    public GameScreen(RollingBall rb) {
        this.rollingBall = rb;
        this.spriteBatch = new SpriteBatch();
        this.world = new World(this, 0);
        this.gameState = new GameState();

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
        this.spriteBatch.end();
    }

    public void update() {
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

        Vector2 acceleration = new Vector2(accelerometerX, accelerometerY);

        this.world.getBoule().acceleration(acceleration);
        this.world.getBoule().update();

        this.world.eatPills();

    }

    public void mangePillNormal(){
        this.gameState.mangePillNormal();
    }

    public void mangePillTemps(){
        this.gameState.mangePillTemps();
    }

    public void dispose() {
        this.spriteBatch.dispose();
    }

    public RollingBall getRollingBall() {
        return rollingBall;
    }
}
