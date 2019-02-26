package kaosprosjekt;

import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.paint.Color.WHITE;

/**
 * @author Danay
 */
public class Bifurcation {

    private static final int ANTITER = 100;
    private final static int BREDDE = 800;
    private final static int HØYDE = 600;
    
    private static double xMax = 4;
    private static double xMin = 2;
    
    private static double yMax = 1;
    private static double yMin = 0;
    
    private static double totalX = xMax - xMin;
    private static double totalY = yMax - yMin;
    
    private static double xPos = BREDDE/2;
    private static double yPos = HØYDE/2;
    private static double yMidt = (HØYDE/2)/HØYDE*totalY;
    
    
    private static double iter(double r, double x){
        if (x>4)
            return 0;
        return 1-(r*x*(1-x));
    }
    private static double iterMye(double r){
        double x=0.5;
        for (int i=0; i<ANTITER; i++){
            x = iter(r, x);
        }
        return x;
    }
    
    public static void bifurcation(GraphicsContext gc){
        for (int kol=0; kol<BREDDE; kol++){
            double x = ((kol * 0.00125*totalX) + xMin);
            double y = (iterMye(x));
            for (int i=0; i<1000; i++){
                double yFill = y * HØYDE / totalY;
                gc.fillRect(kol, yFill, 1, 1);
                y = iter(x, y);
            }
        }
        System.out.println("\n");
    }
    
    public static void zoomClick(double x, double y){
        xMin += totalX / 8;
        yMin += totalY * 0.125;
        xMax -= totalX / 8;
        yMax -= totalY * 0.125;
        totalX = xMax-xMin;
        totalY = yMax-yMin;        
       // System.out.println("x: " + x + " y: " + y);
        xPos = (xPos - ((BREDDE/2) - x)) * 0.00125 * totalX;
        yPos = ((HØYDE/2) - y) / HØYDE * totalY;
       // System.out.println("YMIDT " + yMidt);
        yMidt = yMidt - yPos;
       // System.out.println("YMIDT " + yMidt);
       // System.out.println("YPOS" + yPos);
        
        xMin += xPos;
        xMax += xPos;
        yMax = yMidt + (0.5 * totalY);
        yMin = yMidt - (0.5 * totalY);
        
        
        //yMin = (((y - (HØYDE/2)) / HØYDE)*totalY);
       // yMax = (((y + (HØYDE/2)) /HØYDE)*totalY);
        
        totalX = xMax-xMin;
        totalY = yMax-yMin;
        //System.out.println(totalX + " " + totalY + " xMin " + xMin + " xMax " + xMax + " yMin " + yMin + " yMax " + yMax +"\n");
    }
    
}
