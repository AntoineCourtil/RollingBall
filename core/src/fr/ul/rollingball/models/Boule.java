package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import fr.ul.rollingball.dataFactories.TextureFactory;

/**
 * Created by Antoine Courtil on 30/01/17.
 */

public class Boule extends MovableElement {

    private static float rayon = 20;
    private float rayonCourant;


    public Boule(World world, Vector2 position) {
        super(world, position);
        this.rayonCourant = Boule.rayon;
    }

    public float getRayonCourant() {
        return rayonCourant;
    }

    public void setRayonCourant(float rayonCourant) {
        this.rayonCourant = rayonCourant;
    }

    public void draw(SpriteBatch spriteBatch){
        Texture boule2D = TextureFactory.getInstance().getBoule2D();
        float x = this.getPosition().x;
        float y = this.getPosition().y;

        //spriteBatch.begin();
        spriteBatch.draw(boule2D, x, y, this.rayonCourant, this.rayonCourant);
        //spriteBatch.end();
    }


}
