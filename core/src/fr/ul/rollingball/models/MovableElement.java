package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by courtil1u on 24/01/17.
 */

public class MovableElement extends GameElement {

    private Vector2 vitesse;

    public MovableElement(World world, Vector2 position) {
        super(world, position);
        vitesse = new Vector2();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

    }

    public Vector2 getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(Vector2 vitesse) {
        this.vitesse = vitesse;
    }

    public void acceleration(Vector2 acceleration){
        this.vitesse.add(acceleration);
    }

    public void update(){
        this.setPosition(this.getPosition().add(this.vitesse));
    }
}
