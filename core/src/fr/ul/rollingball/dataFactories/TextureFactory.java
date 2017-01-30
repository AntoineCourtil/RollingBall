package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by courtil1u on 24/01/17.
 */

public class TextureFactory {

    private static TextureFactory instance;

    public static Texture intro;
    public static Texture decor;

    private TextureFactory(){
        this.intro = new Texture(Gdx.files.internal("images/Intro.jpg"));
        this.decor = new Texture(Gdx.files.internal("images/Deco.jpg"));
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

}
