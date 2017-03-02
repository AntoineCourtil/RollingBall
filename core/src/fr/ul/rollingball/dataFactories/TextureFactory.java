package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Created by Antoine Courtil on 24/01/17.
 */

public class TextureFactory {

    private static TextureFactory instance;

    private ArrayList<String> listLaby;

    private Texture intro;
    private Texture decor;
    private Texture bravo;
    private Texture boule2D;
    private Texture pastilleNormale2D;
    private Texture pastilleTaille2D;
    private Texture pastilleTemps2D;
    private Texture gagne;
    private Texture perdu;

    private BitmapFont police;

    private Array<Sprite> boule3D;
    private Array<Sprite> pastilleNormale3D;
    private Array<Sprite> pastilleTaille3D;
    private Array<Sprite> pastilleTemps3D;


    private TextureFactory() {
        intro = new Texture(Gdx.files.internal("images/Intro.jpg"));
        decor = new Texture(Gdx.files.internal("images/Deco.jpg"));
        gagne = new Texture(Gdx.files.internal("images/Bravo.bmp"));
        perdu = new Texture(Gdx.files.internal("images/Perte.bmp"));
        boule2D = new Texture(Gdx.files.internal("images/badlogic.jpg"));
        pastilleNormale2D = new Texture(Gdx.files.internal("images/pastilleNormale.bmp"));
        pastilleTaille2D = new Texture(Gdx.files.internal("images/pastilleTaille.bmp"));
        pastilleTemps2D = new Texture(Gdx.files.internal("images/pastilleTemps.bmp"));


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Comic_Sans_MS_Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 40;
        parameter.color = new Color(1, 1, 0, 0.75f);
        parameter.borderColor = new Color(0, 0, 0, 0.75f);
        parameter.borderWidth = 3;

        this.police = generator.generateFont(parameter);
        generator.dispose();

        boule3D = new TextureAtlas(Gdx.files.internal("images/boule.pack")).createSprites();
        pastilleNormale3D = new TextureAtlas(Gdx.files.internal("images/pastilleNormale.pack")).createSprites();
        pastilleTaille3D = new TextureAtlas(Gdx.files.internal("images/pastilleTaille.pack")).createSprites();
        pastilleTemps3D = new TextureAtlas(Gdx.files.internal("images/pastilleTemps.pack")).createSprites();


        listLaby = new ArrayList<String>();
        FileHandle[] files = Gdx.files.internal("images/").list();
        for (FileHandle file : files) {
            if (Pattern.matches("Laby[0-9].png", file.name())) {
                listLaby.add(file.toString());
                //Gdx.app.log("DEBUG", file.name());
            }
        }


        Collections.sort(listLaby);
    }

    public static TextureFactory getInstance() {
        if (instance == null) {
            instance = new TextureFactory();
        }
        return instance;
    }

    public Texture getIntro() {
        return intro;
    }

    public Texture getDecor() {
        return decor;
    }

    public Texture getBoule2D() {
        return boule2D;
    }

    public Texture getGagne() {
        return gagne;
    }

    public Texture getPerdu() {
        return perdu;
    }

    public Sprite getBoule3D(int index) {
        return boule3D.get(index);
    }

    public Texture getPastilleNormale2D() {
        return pastilleNormale2D;
    }

    public Sprite getPastilleNormale3D(int index) {
        return pastilleNormale3D.get(index);
    }

    public Texture getPastilleTaille2D() {
        return pastilleTaille2D;
    }

    public Sprite getPastilleTaille3D(int index) {
        return pastilleTaille3D.get(index);
    }

    public Texture getPastilleTemps2D() {
        return pastilleTemps2D;
    }

    public Sprite getPastilleTemps3D(int index) {
        return pastilleTemps3D.get(index);
    }

    public int getNbLaby() {
        return listLaby.size();
    }

    public Texture getTextureLaby(int index) {
        return new Texture(Gdx.files.internal(listLaby.get(index)));
    }

    public Pixmap getPixmapLaby(int index) {
        FileHandle texture = Gdx.files.internal(listLaby.get(index));
        TextureData textureData = TextureData.Factory.loadFromFile(texture, true);
        textureData.prepare();
        Pixmap pixmap = textureData.consumePixmap();

        return pixmap;
    }

    public BitmapFont getPolice() {
        return police;
    }

    public void drawScore() {

    }
}
