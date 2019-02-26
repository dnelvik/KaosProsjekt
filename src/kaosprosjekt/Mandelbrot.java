package kaosprosjekt;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Klasse for å tegne Mandelbrot figuren
 * @author Danay Nelvik
 */

public class Mandelbrot {
    
    private static Canvas canvas = new Canvas(800, 600);
    
    final static double BREDDE = canvas.getWidth();
    final static double HØYDE = canvas.getHeight();
    final static int ITER = 1500;
    
    //verdi brukt for utregning av differansen mellom punkter
    private static double xPos = 0;
    private static double yPos = 0;
    
    //Maksverdien for grafen som vises
    private static double xMax = 2;
    private static double yMax = 2;
    
    //Minverdien for grafen som vises
    private static double xMin = -2;
    private static double yMin = -2;
    
    //Totalverdier for lengden av grafen
    private static double total_x = xMax - xMin;
    private static double total_y = yMax - yMin;

    /**
     *Metode som regner ut antall iterasjoner et punkt kommer gjennom
     * @param c Punktet som skal testes
     * @param max Maks antall iterasjoner som utføres
     * @return Returnerer antall iterasjoner punktet kom seg gjennom
     */
    public static int mandel(Complex c, int max) {
        Complex z = new Complex(0, 0);
        for (int i = 0; i < max; i++) {
            if (z.lengde() > 4) {
                return i;
            }
            z = z.gange(z).addere(c);
        }
        return max;
    }

    /**
     * Metoden tar inn en GraphicsContext og tegner figuren på denne
     * @param gc GraphicsContext-parameter som brukes til å tegnes på
     */
    public static void mandelBrot(GraphicsContext gc) {
        //Går gjennom hver piksel på canvas
        for (int rad = 0; rad < HØYDE; rad++) {
            for (int kol = 0; kol < BREDDE; kol++) {
                //Gjør om fra skjermkoordinater til complex koordinat
                double x = ((kol * 0.00125) * total_x) + xMin;
                double y = ((rad * 0.0017) * total_y) + yMin;
                Complex z = new Complex(x, y);
                
                //Brukes for å vurdere om det er en del av mandelbrot eller ikke
                int it = mandel(z, ITER);
                //verdier som brukes til fargelegging
                double t1 = (double) it / ITER;
                double hue = Color.LIGHTGRAY.getHue() + (Color.BLUE.getHue() - Color.PURPLE.getHue()) * (ITER - 100) / (it - 100) ;
                double c1 = Math.min(255 * 10 * t1, 255);
                //double c2 = Math.max(255 * (1.5 * t1 - 4), 0); gammel fargeverdi
                if (it != ITER) {
                    //gc.setFill(Color.color(c1 / 255, c2 / 255, c1 / 255)); // Gammel farge
                    gc.setFill(Color.hsb(hue, 1, c1/255)); // Ny farge
                } else {
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect(kol, rad, 1, 1);
            }
        }
    }

    /**
     * Metode for å zoome ut med klikk som senterpunkt
     * @param x x-verdien for museklikket
     * @param y y-verdien for museklikket
     */
    public static void zoomUt(double x, double y) {
        //forstørrer grafområdet slik at det zoomes ut
        xMin -= total_x * 1.10;
        yMin -= total_y * 1.10;
        xMax += total_x * 1.10;
        yMax += total_y * 1.10;
        
        panorer(x, y);
        
        //regner ut ny totalverdi
        total_x = xMax - xMin;
	total_y = yMax - yMin;
    }

    /**
     * Metode for å zoome inn på et museklikk
     * @param x x-verdien for museklikket
     * @param y y-verdien for museklikket
     */
    public static void zoomClick(double x, double y){
        //Minsker grafområdet slik at det zoomes
        xMin += total_x / 4;
        yMin += total_y / 4;
        xMax -= total_x / 4;
        yMax -= total_y / 4;
        
        panorer(x, y);

        //regner ut ny totalverdi
        total_x = xMax - xMin;
	total_y = yMax - yMin;
    }

    /**
     * Metode for å kun panorere ved museklikk
     * @param x x-verdien for museklikket
     * @param y y-verdien for museklikket
     */
    public static void panorer(double x, double y){
        //Regner ut differansen mellom klikk og forrige midtpunkt
        xPos = (xPos - ((BREDDE/2) - x)) * 0.00125 * total_x;
        yPos = (yPos - ((HØYDE/2) - y)) * 0.0017 * total_y;
        
        //Flytter figuren slik at museklikket blir midtpunkt
        xMin += xPos;
        xMax += xPos;
        yMin += yPos;
        yMax += yPos;
    }

    /**
     * Returnerer alle verdier til opprinnelig verdi
     * slik at figuren blir resatt
     */
    public static void clear(){
        xPos = 0;
        yPos = 0;
        xMin = -2;
        xMax = 2;
        yMin = -2;
        yMax = 2;
        total_x = xMax - xMin;
	total_y = yMax - yMin;
    }
}
