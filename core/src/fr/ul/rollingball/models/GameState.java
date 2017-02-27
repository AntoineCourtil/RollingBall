package fr.ul.rollingball.models;

import com.badlogic.gdx.Gdx;

import fr.ul.rollingball.dataFactories.SoundFactory;

/**
 * Created by antoine on 27/02/17.
 */

public class GameState {

    private static enum State {
        Running,
        Victory,
        Loss
    }

    public static float temps = 300;
    private State etat;


    private int score;
    private float tempsRestant;
    private int nbPillNormale;

    public GameState() {
        this.score = 0;
        this.nbPillNormale = 0;
        this.tempsRestant = temps;
        this.etat = State.Running;
    }

    public void mangePillNormal() {
        this.score += 10;
        this.nbPillNormale++;
    }

    public void mangePillTemps() {
        this.tempsRestant += 5;
    }

    public boolean isRunning() {
        return this.etat == State.Running;
    }

    public boolean isWon() {
        return this.etat == State.Victory;
    }

    public boolean isLost() {
        return this.etat == State.Loss;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getTempsRestant() {
        return this.tempsRestant;
    }

    public void setTempsRestant(float tempsRestant) {
        this.tempsRestant = tempsRestant;
    }

    public int getNbPillNormale() {
        return this.nbPillNormale;
    }

    public void setNbPillNormale(int nbPillNormale) {
        this.nbPillNormale = nbPillNormale;
    }
}