package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.rollingball.RollingBall;
import fr.ul.rollingball.views.GameScreen;

/**
 * Created by Antoine Courtil on 30/01/17.
 */

public class World {
    private GameScreen gameScreen;
    private Boule boule;

    public World(GameScreen gameScreen){
        this.gameScreen = gameScreen;

        Vector2 position = new Vector2(this.gameScreen.getRollingBall().getWidth()/2, this.gameScreen.getRollingBall().getHeight()/2);
        this.boule = new Boule(this, position);


    }

    public void draw(SpriteBatch spriteBatch){
        this.boule.draw(spriteBatch);
    }

    public Boule getBoule() {
        return boule;
    }
}
