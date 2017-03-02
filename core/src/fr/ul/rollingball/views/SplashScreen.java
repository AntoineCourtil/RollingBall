package fr.ul.rollingball.views;

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

/**
 * Created by Antoine Courtil on 24/01/17.
 */

public class SplashScreen extends ScreenAdapter {

    private RollingBall rollingBall;
    private SpriteBatch spriteBatch;
    private float duree;
    private Camera camera;

    public SplashScreen(RollingBall rb) {
        this.rollingBall = rb;
        this.spriteBatch = new SpriteBatch();

        this.camera = new OrthographicCamera(this.rollingBall.getWidth(), this.rollingBall.getHeight());
        Viewport vp = new FitViewport(this.rollingBall.getWidth(), this.rollingBall.getHeight());
        this.camera.position.set(new Vector2(this.camera.viewportWidth / 2f, this.camera.viewportHeight / 2f), 0f);
        this.camera.update();
        this.spriteBatch.setProjectionMatrix(this.camera.combined);
    }

    @Override
    public void show() {
        this.duree = 0;
    }

    @Override
    public void render(float delta) {
        Texture intro = TextureFactory.getInstance().getIntro();

        this.duree += delta;

        if (this.duree >= 3) {
            this.rollingBall.displayGame();
        }

        this.spriteBatch.begin();
        this.spriteBatch.draw(intro, 0, 0);
        this.spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }

}
