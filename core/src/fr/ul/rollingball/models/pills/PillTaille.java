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

public class PillTaille extends Pill {

    private int noSprite;

    public PillTaille(World world, Vector2 position) {
        super(world, position);
        this.noSprite = 0;
    }

    @Override
    public void effect() {
        this.getWorld().mangePillTaille();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

        this.noSprite++;
        if (this.noSprite >= 60) {
            this.noSprite = 0;
        }

        float x = this.getPosition().x;
        float y = this.getPosition().y;

        //Texture texture = TextureFactory.getInstance().getPastilleTaille2D();
        Sprite s = TextureFactory.getInstance().getPastilleTaille3D(this.noSprite / 10);
        spriteBatch.draw(s, x, y, this.getRayon(), this.getRayon());
    }
}
