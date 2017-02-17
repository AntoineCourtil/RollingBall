package fr.ul.rollingball.models.pills;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import fr.ul.rollingball.dataFactories.TextureFactory;
import fr.ul.rollingball.models.World;

/**
 * Created by Antoine Courtil on 17/02/17.
 */

public class PillTemps extends Pill {

    private int noSprite;

    public PillTemps(World world, Vector2 position) {
        super(world, position);
        this.noSprite = 0;
    }

    @Override
    public void effect() {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

        float x = this.getPosition().x;
        float y = this.getPosition().y;

        //Texture texture = TextureFactory.getInstance().getPastilleTemps2D();
        Sprite s = TextureFactory.getInstance().getPastilleTemps3D(this.noSprite);
        spriteBatch.draw(s, x, y, this.getRayon(), this.getRayon());
    }
}
