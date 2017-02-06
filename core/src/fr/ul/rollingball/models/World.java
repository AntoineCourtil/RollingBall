package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import fr.ul.rollingball.dataFactories.SoundFactory;
import fr.ul.rollingball.dataFactories.TextureFactory;
import fr.ul.rollingball.views.GameScreen;

/**
 * Created by Antoine Courtil on 30/01/17.
 */

public class World {
    private GameScreen gameScreen;
    private Boule boule;
    private int labyCourant;
    private Pixmap pixmap;
    private Texture textureLaby;

    public World(GameScreen gameScreen, int laby){
        this.gameScreen = gameScreen;
        this.labyCourant = laby;

        Vector2 position = new Vector2(this.gameScreen.getRollingBall().getWidth()/2, this.gameScreen.getRollingBall().getHeight()/2);
        this.boule = new Boule(this, position);

        this.pixmap = TextureFactory.getInstance().getPixmapLaby(laby);
        this.textureLaby = TextureFactory.getInstance().getTextureLaby(laby);

    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(this.textureLaby, 0, 0);
        this.boule.draw(spriteBatch);
    }

    public Boule getBoule() {
        return boule;
    }
}
