package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Antoine Courtil on 24/01/17.
 */

public abstract class GameElement {

    private World world;
    private Vector2 position;

    public GameElement(World world, Vector2 position) {
        this.world = world;
        this.position = position;
    }

    public World getWorld() {
        return this.world;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public abstract void draw(SpriteBatch spriteBatch);

}
