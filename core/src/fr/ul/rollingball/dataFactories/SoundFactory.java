package fr.ul.rollingball.dataFactories;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Antoine Courtil on 06/02/17.
 */

public class SoundFactory {

    private static SoundFactory instance;

    private static Sound alerte;
    private static Sound collision;
    private static Sound pastille;
    private static Sound perte;
    private static Sound ptaille;
    private static Sound ptemps;
    private static Sound victoire;

    private SoundFactory() {
        alerte = Gdx.audio.newSound(Gdx.files.internal("sounds/alerte.mp3"));
        collision = Gdx.audio.newSound(Gdx.files.internal("sounds/collision.wav"));
        pastille = Gdx.audio.newSound(Gdx.files.internal("sounds/pastille.wav"));
        perte = Gdx.audio.newSound(Gdx.files.internal("sounds/perte.mp3"));
        ptaille = Gdx.audio.newSound(Gdx.files.internal("sounds/ptaille.wav"));
        ptemps = Gdx.audio.newSound(Gdx.files.internal("sounds/ptemps.wav"));
        victoire = Gdx.audio.newSound(Gdx.files.internal("sounds/victoire.mp3"));
    }

    public static SoundFactory getInstance() {
        if (instance == null) {
            instance = new SoundFactory();
        }
        return instance;
    }

    public void playAlerte(float volume) {
        alerte.play(volume);
    }

    public void playCollision(float volume) {
        collision.play(volume);
    }

    public void playPastille(float volume) {
        pastille.play(volume);
    }

    public void playPerte(float volume) {
        perte.play(volume);
    }

    public void playPtaille(float volume) {
        ptaille.play(volume);
    }

    public void playPtemps(float volume) {
        ptemps.play(volume);
    }

    public void playVictoire(float volume) {
        victoire.play(volume);
    }
}
