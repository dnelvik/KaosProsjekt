package kaosprosjekt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Åsmund Norderud
 * Game of life - Simulering av celler som enten lever, dør eller
 * blir født for hver generasjon som går
 *
 */
public class GameOfLife extends Canvas {

    private final int HØYDE = 700;
    private final int BREDDE = 800;
    private double rutebredde;

    private boolean[][] gen, nesteGen;

    private int str;
    private int antGen;

    private Timeline loop;
    private GraphicsContext gc;

    /**
     * Konstruktøren til GameOfLife-objektet
     */
    public GameOfLife() {
        setHeight(HØYDE);
        setWidth(BREDDE);
        this.str = 100;
        this.antGen = 0;
        gen = new boolean[str][str];
        nesteGen = new boolean[str][str];

        gc = this.getGraphicsContext2D();

        loop = new Timeline(new KeyFrame(Duration.millis(50), e -> nesteGen()));
        resetGOL(str);
    }

    /* 
    * Metode for å teste alle cellene og legge resultatet i en ny matrise(nesteGen),
    * kopiere nesteGen[][] over til gen[][] og tegne rutenettet på nytt.
    */
    void nesteGen() {
        for (int rad = 1; rad < str - 1; rad++) {
            for (int kol = 1; kol < str - 1; kol++) {
                testCelle(rad, kol);
            }
        }
        antGen++;
        gen = nesteGen;
        nesteGen = tømMatrise(nesteGen);
        repaint();
    }

    /*
    * Metoden som tester en enkelt celle, teller naboer og 
    * bestemmer om den skal leve, dø eller bli født
    */ 
    private void testCelle(int rad, int kol) {
        int antNaboer = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (gen[rad + i][kol + j] == true) {
                    antNaboer++;
                }
            }
        }
        if (gen[rad][kol] == true) {
            antNaboer--;
            if (antNaboer < 2 || antNaboer > 3) {
                nesteGen[rad][kol] = false; // Dør
            } else {
                nesteGen[rad][kol] = true; // Lever
            }
        } else {
            if (antNaboer == 3) {
                nesteGen[rad][kol] = true; // Blir født
            }
        }
    }

    /** 
     * Metode for å tegne brettet ut i fra verdiene i gen[][]
     */
    private void repaint() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, BREDDE, HØYDE);
        for (int rad = 1; rad < str - 1; rad++) {
            for (int kol = 1; kol < str - 1; kol++) {
                double x = rutebredde * rad;
                double y = rutebredde * kol;
                if (gen[rad][kol] == true) {
                    gc.setFill(Color.GREENYELLOW);
                } else {
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect(x, y, rutebredde + 1, rutebredde + 1);
            }
        }
        gc.setFill(Color.WHITE);
        gc.fillText(antGen + "", BREDDE - 50, HØYDE - 10);
    }

    /**
     * Metode for å kjøre nesteGen() med en valgt hastighet
     *
     * @param h Hastigheten som brukeren velger
     */
    public void start(double h) {
        double hastighet = (1 / h) * 10000;
        loop.stop();
        loop = new Timeline(new KeyFrame(Duration.millis(hastighet), e -> nesteGen()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    /**
     * Metode for å stoppe loopen som kjører
     */
    public void stopp() {
        loop.stop();
    }

    /** Metode for å tegne på rutenettet og oppdatere gen[][] matrisen.
     * 
     * @param x x-verdien der brukeren trykker
     * @param y y-verdien der brukeren trykker
     */
    void fyllRute(double x, double y) {
        int rad = (int) (x / rutebredde);
        int kol = (int) (y / rutebredde);

        gen[rad][kol] = true;
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(rad * rutebredde, kol * rutebredde, rutebredde, rutebredde);
    }

    // Fyller brettet med tilfeldig valgte lever/død celler
    void fyllRandom() {
        for (int rad = 0; rad < str; rad++) {
            for (int kol = 0; kol < str; kol++) {
                double random = Math.random() * 10 / 5;
                int rundet = (int) random;
                if (rundet == 0) {
                    gen[rad][kol] = true;
                } else {
                    gen[rad][kol] = false;
                }
            }
        }
        repaint();
    }

    /** Metode for å fylle matrisene med false-verdier(død) og tegne det på nytt
     * 
     * @param s Størrelsen til det nye brettet
     */
    void resetGOL(int s) {
        str = s;
        gen = new boolean[str][str];
        nesteGen = new boolean[str][str];
        rutebredde = (double) HØYDE / str;
        gen = tømMatrise(gen);
        nesteGen = tømMatrise(nesteGen);
        repaint();
    }

    /** Metode for å tømme en boolean-matrise(Fylle med false-verdier)
     * 
     * @param matrise matrisen som skal tømmes
     * @return returnerer en matrise med bare false-verdier
     */
    private boolean[][] tømMatrise(boolean[][] matrise) {
        boolean tomMatrise[][] = new boolean[matrise.length][matrise.length];
        for (int rad = 0; rad < matrise.length; rad++) {
            for (int kol = 0; kol < matrise.length; kol++) {
                tomMatrise[rad][kol] = false;
            }
        }
        return tomMatrise;
    }
}
