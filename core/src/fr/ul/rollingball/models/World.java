package fr.ul.rollingball.models;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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

    public World(GameScreen gameScreen, int laby){
        this.gameScreen = gameScreen;
        this.labyCourant = laby;

        Vector2 position = new Vector2(this.gameScreen.getRollingBall().getWidth()/2, this.gameScreen.getRollingBall().getHeight()/2);
        this.boule = new Boule(this, position);

        this.pixmap = TextureFactory.getInstance().getPixmapLaby(laby);
        this.textureLaby = TextureFactory.getInstance().getTextureLaby(laby);

        this.pills = new ArrayList<>();
        this.extractPills();

    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(this.textureLaby, 0, 0);
        this.drawPills(spriteBatch);
        this.boule.draw(spriteBatch);
    }

    public void drawPills(SpriteBatch spriteBatch){
        for(Pill p : this.pills){
            p.draw(spriteBatch);
        }
    }

    public Boule getBoule() {
        return boule;
    }

    public void extractPills(){

        int height = pixmap.getHeight();

        for(int x=0; x<pixmap.getWidth(); x++){
            for(int y=0; y<pixmap.getHeight(); y++){

                int pixel = pixmap.getPixel(x,y) & 0xFF;

                switch (pixel){
                    case 128:
                        //normal
                        Vector2 pos = new Vector2(x, height-y);
                        this.pills.add(new PillNormal(this, pos));
                        this.deletePill(pos, pixmap, pixel);
                        break;

                    case 200:
                        //taille
                        Vector2 pos2 = new Vector2(x, height-y);
                        this.pills.add(new PillTaille(this, pos2));
                        this.deletePill(pos2, pixmap, pixel);
                        break;

                    case 225:
                        //temps
                        Vector2 pos3 = new Vector2(x, height-y);
                        this.pills.add(new PillTemps(this, pos3));
                        this.deletePill(pos3, pixmap, pixel);
                        break;
                }
            }
        }
    }

    public void deletePill(Vector2 pos, Pixmap pixmap, int pixel){
        pixmap.drawPixel(((int) pos.x), ((int) pos.y), pixel);
    }
}
