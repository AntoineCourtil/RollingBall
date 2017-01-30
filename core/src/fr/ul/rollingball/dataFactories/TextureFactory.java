package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Antoine Courtil on 24/01/17.
 */

public class TextureFactory {

    private static TextureFactory instance;

    private static Texture intro;
    private static Texture decor;
    public static Texture boule2D;

    private TextureFactory(){
        this.intro = new Texture(Gdx.files.internal("images/Intro.jpg"));
        this.decor = new Texture(Gdx.files.internal("images/Deco.jpg"));
        this.boule2D = new Texture(Gdx.files.internal("images/badlogic.jpg"));
    }

    public static TextureFactory getInstance(){
        if(instance==null){
            instance = new TextureFactory();
        }
        return instance;
    }

    public Texture getIntro(){
        return this.intro;
    }

    public Texture getDecor(){
        return this.decor;
    }

    public Texture getBoule2D(){
        return this.boule2D;
    }

}
