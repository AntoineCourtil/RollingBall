package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

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
    private Texture boule3D;
    private Texture pastilleNormale2D;
    private Texture pastilleNormale3D;
    private Texture pastilleTaille2D;
    private Texture pastilleTaille3D;
    private Texture pastilleTemps2D;
    private Texture pastilleTemps3D;


    private TextureFactory() {
        intro = new Texture(Gdx.files.internal("images/Intro.jpg"));
        decor = new Texture(Gdx.files.internal("images/Deco.jpg"));
        bravo = new Texture(Gdx.files.internal("images/Bravo.bmp"));
        boule2D = new Texture(Gdx.files.internal("images/badlogic.jpg"));
        boule3D = new Texture(Gdx.files.internal("images/boule.bmp"));
        pastilleNormale2D = new Texture(Gdx.files.internal("images/pastilleNormale.bmp"));
        pastilleNormale3D = new Texture(Gdx.files.internal("images/pastilleNormale.png"));
        pastilleTaille2D = new Texture(Gdx.files.internal("images/pastilleTaille.bmp"));
        pastilleTaille3D = new Texture(Gdx.files.internal("images/pastilleTaille.png"));
        pastilleTemps2D = new Texture(Gdx.files.internal("images/pastilleTemps.bmp"));
        pastilleTemps3D = new Texture(Gdx.files.internal("images/pastilleTemps.png"));

        listLaby = new ArrayList<String>();
        FileHandle[] files = Gdx.files.internal("images/").list();
        for(FileHandle file: files) {
            if(Pattern.matches("Laby[0-9].png", file.name())) {
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

    public Texture getBravo() { return bravo; }

    public Texture getBoule3D() { return boule3D; }

    public Texture getPastilleNormale2D() { return pastilleNormale2D; }

    public Texture getPastilleNormale3D() { return pastilleNormale3D; }

    public Texture getPastilleTaille2D() { return pastilleTaille2D; }

    public Texture getPastilleTaille3D() { return pastilleTaille3D; }

    public Texture getPastilleTemps2D() { return pastilleTemps2D; }

    public Texture getPastilleTemps3D() { return pastilleTemps3D; }

    public int getNbLaby(){ return listLaby.size(); }

    public Texture getTextureLaby(int index){ return new Texture(Gdx.files.internal(listLaby.get(index))); }

    public Pixmap getPixmapLaby(int index){
        FileHandle texture = Gdx.files.internal(listLaby.get(index));
        TextureData textureData = TextureData.Factory.loadFromFile(texture, true);
        textureData.prepare();
        Pixmap pixmap = textureData.consumePixmap();

        return pixmap;
    }

}
