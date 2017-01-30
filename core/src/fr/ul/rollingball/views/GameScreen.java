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
 * Created by courtil1u on 24/01/17.
 */

public class GameScreen extends ScreenAdapter {
    private RollingBall rollingBall;
    private SpriteBatch spriteBatch;
    private Camera camera;

    public GameScreen(RollingBall rb){
        this.rollingBall = rb;
        this.spriteBatch = new SpriteBatch();

        this.camera = new OrthographicCamera(this.rollingBall.getWidth(), this.rollingBall.getHeight());
        Viewport vp = new FitViewport(this.rollingBall.getWidth(), this.rollingBall.getHeight());
        this.camera.position.set(new Vector2(this.camera.viewportWidth/2f, this.camera.viewportHeight/2f), 0f);
        this.camera.update();
        this.spriteBatch.setProjectionMatrix(this.camera.combined);
    }

    public void render(float delta) {
        Texture decor = TextureFactory.getInstance().getDecor();

        this.spriteBatch.begin();
        this.spriteBatch.draw(decor, 0, 0);
        this.spriteBatch.end();
    }

    public void dispose(){
        this.spriteBatch.dispose();
    }
}
