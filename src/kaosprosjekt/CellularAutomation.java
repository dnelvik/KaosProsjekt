package kaosprosjekt;

import javafx.scene.canvas.GraphicsContext;

/**
 * Cellular Automation tegner en figur basert på et sett med 8 regler.
 * @author Danay Nelvik
 */
public class CellularAutomation {
    private static final int kol = 31;
    private static final int rad = 16;
    private static boolean r1, r2, r3, r4, r5, r6, r7, r8;
    
    private static final int KVADRAT = 24;
    
    /**
     * Metoden oppretter en matrise som representerer figuren
     * og fyller den ut med 1 der det skal tegnes 
     * og 0 der det ikke skal tegnes
     * @return Returnerer en matrise som representerer figuren
     */
    public static int[][] lagMatrise () {
        int [][] matrise = new int [rad][kol];
        matrise[0][15] = 1;
        
        //går gjennom hver kolonne i matrisen
        for (int i=0; i<rad; i++){
            for (int x=0; x<kol; x++){
                
                //verdier for om det er svar eller hvit til venstre eller høyre
                int v = 0;
                int vv = 0;
                int h = 0;
                int hh = 0;
                
                //fyller verdiene
                if (x<kol-2 && x > 1){
                    v = matrise[i][x-1];
                    vv = matrise[i][x-2];
                    h = matrise[i][x+1];
                    hh = matrise[i][x+2];
                } else if (x == 1){
                    h = matrise[i][x+1];
                    hh = matrise[i][x+2];
                    v = matrise[i][x-1];
                } else if (x==kol-2){
                    v = matrise[i][x-1];
                    vv = matrise[i][x-2];
                    h = matrise[i][x+1];
                }
                
                //bestemmer om det skal tegnes eller ikke
                if (matrise[i][x] == 1 && i<rad-1) {
                    //sss
                    if (r1)
                        if (v == 1 && h == 1)
                            matrise[i+1][x] = 1;
                    //ssh
                    if (r2)
                        if (v == 1 && h == 0)
                            matrise[i+1][x] = 1;
                    //shs
                    if (r3)
                        if (h == 0 && hh == 1)
                            matrise[i+1][x+1] = 1;
                    //shh
                    if (r4 && x<kol-1)
                        if (h == 0 && hh == 0)
                            matrise[i+1][x+1] = 1;

                    if (r5)
                        if (v == 0 && h == 1)
                            matrise[i+1][x] = 1;
                    //hsh
                    if (r6)
                        if (v == 0 && h == 0)
                            matrise[i+1][x] = 1;
                    //hhs
                    if (r7)
                        if (vv == 0 && v == 0 && x>0)
                            matrise[i+1][x-1] = 1;
                }
                //hhh
                if (r8 && x>=0 && x<kol && i<rad-1)
                    if (v == 0 && matrise[i][x] == 0 && h == 0){
                        matrise[i+1][x] = 1;
                    }
            }
        }
        return matrise;
    }

    /**
     * Metoden tar inn en GraphicsContext og tegner figuren på denne
     * @param gc GraphicsContext-parameter som brukes til å tegnes på
     */
    public static void tegnCA(GraphicsContext gc){
        int[][] tabell = lagMatrise();
        for (int y=0; y<rad; y++){
            for (int x=0; x<kol; x++){
                double xKor = (28-KVADRAT/2) + (KVADRAT*(x+1));
                double yKor = (108-KVADRAT/2) + ((KVADRAT)*(y+1));
                if (tabell[y][x] == 1)
                    gc.fillRect(xKor, yKor, 24, 24);
            }
        }
    }
    
    /**
     * Metode tar inn verdier for reglene for figuren som skal lages
     * @param re1 Regel nr. 1
     * @param re2 Regel nr. 2
     * @param re3 Regel nr. 3
     * @param re4 Regel nr. 4
     * @param re5 Regel nr. 5
     * @param re6 Regel nr. 6
     * @param re7 Regel nr. 7
     * @param re8 Regel nr. 8
     */
    public static void regler(boolean re1, boolean re2, boolean re3, boolean re4,
            boolean re5, boolean re6, boolean re7, boolean re8){
        r1 = re1;
        r2 = re2;
        r3 = re3;
        r4 = re4;
        r5 = re5;
        r6 = re6;
        r7 = re7;
        r8 = re8;
    }
}
