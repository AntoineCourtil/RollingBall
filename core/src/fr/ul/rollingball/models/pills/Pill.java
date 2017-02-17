package fr.ul.rollingball.models.pills;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.rollingball.models.GameElement;
import fr.ul.rollingball.models.World;

/**
 * Created by Antoine Courtil on 17/02/17.
 */

public abstract class Pill extends GameElement {

    private static float rayon = 10;


    public Pill(World world, Vector2 position) {
        super(world, position);
    }

    public static float getRayon() {
        return rayon;
    }

    public abstract void effect();

    @Override
    public abstract void draw(SpriteBatch spriteBatch);
}
