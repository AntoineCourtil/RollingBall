package fr.ul.rollingball.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import fr.ul.rollingball.dataFactories.TextureFactory;

/**
 * Created by Antoine Courtil on 30/01/17.
 */

public class Boule extends MovableElement {

    private static float rayon = 20;
    private float rayonCourant;
    private int noSprite;


    public Boule(World world, Vector2 position) {
        super(world, position);
        this.rayonCourant = Boule.rayon;
        this.noSprite = 0;
    }

    public float getRayonCourant() {
        return rayonCourant;
    }

    public void setRayonCourant(float rayonCourant) {
        this.rayonCourant = rayonCourant;
    }

    public void changeTaille() {
        if (this.rayonCourant > rayon) {
            this.rayonCourant = rayon;
        } else {
            this.rayonCourant = rayon * 2;
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        //Texture boule2D = TextureFactory.getInstance().getBoule2D();

        changeNoSprite();

        Sprite s = TextureFactory.getInstance().getBoule3D(this.noSprite);
        float x = this.getPosition().x;
        float y = this.getPosition().y;

        //spriteBatch.begin();
        spriteBatch.draw(s, x, y, this.rayonCourant, this.rayonCourant);
        //spriteBatch.end();
    }

    public boolean isOut() {
        return this.getPosition().x < 0 || this.getPosition().x > this.getWorld().getWidth() || this.getPosition().y < 0 || this.getPosition().y > this.getWorld().getHeight();
    }

    public void changeNoSprite() {
        this.noSprite++;

        if (this.noSprite >= 32) {
            this.noSprite = 0;
        }
    }

}
