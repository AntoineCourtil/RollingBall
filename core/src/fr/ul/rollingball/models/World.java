package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import fr.ul.rollingball.dataFactories.TextureFactory;
import fr.ul.rollingball.models.pills.Pill;
import fr.ul.rollingball.models.pills.PillNormal;
import fr.ul.rollingball.models.pills.PillTaille;
import fr.ul.rollingball.models.pills.PillTemps;
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
    private ArrayList<Pill> pills;

    public World(GameScreen gameScreen, int laby) {
        this.gameScreen = gameScreen;
        this.labyCourant = laby;

        Vector2 position = new Vector2(this.gameScreen.getRollingBall().getWidth() / 2, this.gameScreen.getRollingBall().getHeight() / 2);
        this.boule = new Boule(this, position);

        this.pixmap = TextureFactory.getInstance().getPixmapLaby(laby);

        this.pills = new ArrayList<>();
        this.extractPills();

        this.textureLaby = new Texture(this.pixmap);

    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(this.textureLaby, 0, 0);
        this.drawPills(spriteBatch);
        this.boule.draw(spriteBatch);
    }

    public void drawPills(SpriteBatch spriteBatch) {
        for (Pill p : this.pills) {
            p.draw(spriteBatch);
        }
    }

    public Boule getBoule() {
        return boule;
    }

    public void extractPills() {


        int height = this.pixmap.getHeight();
        int width = this.pixmap.getWidth();
        ByteBuffer buffer = this.pixmap.getPixels();

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {

                int pixel = buffer.get(x * width + y) & 0xFF;

                if (pixel == 128 || pixel == 200 || pixel == 225) {

                    Vector2 pos = new Vector2(y - 5, height - x - 12);

                    switch (pixel) {

                        case 128:
                            //normal
                            this.pills.add(new PillNormal(this, pos));
                            break;

                        case 200:
                            //taille
                            this.pills.add(new PillTaille(this, pos));
                            break;

                        case 225:
                            //temps
                            this.pills.add(new PillTemps(this, pos));
                            break;
                    }
                    deletePill(new Vector2(y + 1, x + 5), buffer);
                }
            }
        }
    }

    public void deletePill(Vector2 pos, ByteBuffer buf) {
        int width = this.pixmap.getWidth();
        int posX = (int) pos.x - 5;
        int posY = (int) pos.y - 5;
        for (int i = posY; i < posY + 11; i++) {
            for (int j = posX; j < posX + 11; j++) {
                buf.putInt(i * width + j, -1);
            }
        }
    }

    public void eatPills() {

        for (int i = 0; i < this.pills.size(); i++) {
            Pill pill = this.pills.get(i);
            Vector2 posBoule = this.boule.getPosition();
            Vector2 posPill = pill.getPosition();
            float distance = Vector2.dst(posBoule.x, posBoule.y, posPill.x, posPill.y);
            if (distance < this.boule.getRayonCourant() + pill.getRayon()) {
                pill.effect();
                this.pills.remove(i);
            }
        }
    }

    public void mangePillNormal(){
        this.gameScreen.mangePillNormal();
    }

    public void mangePillTemps(){
        this.gameScreen.mangePillTemps();
    }

    public void mangePillTaille(){
        this.boule.changeTaille();
    }
}
