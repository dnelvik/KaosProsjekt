package kaosprosjekt;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Klasse for å lage GUI'en som benyttes i Mandelbrot fanen
 * @author Danay Nelvik
 */
public class MandelGUI extends HBox {
        Canvas tegnVindu = new Canvas(800, 600);
        Button tegn;
        RadioButton zoomInn, zoomUt, panorer;
        ToggleGroup toggle;
        VBox venstre = new VBox();
        
    /**
     * Konstruktør for å opprette et
     * GUI-Objekt ved å kalle på tegnMandelGUI() metoden
     */
    public MandelGUI(){
        tegnMandelGUI();
    }
    
    /**
     * Metode som lager GUI'en for Mandelbrot fanen
     */
    public void tegnMandelGUI() {
        // oppretter Radiobuttons 
        toggle = new ToggleGroup();
        zoomInn = new RadioButton("Zoom inn");
        zoomUt = new RadioButton("Zoom ut");
        panorer = new RadioButton("Panorer");
        //samler radiobuttonsa
        zoomInn.setToggleGroup(toggle);
        zoomUt.setToggleGroup(toggle);
        panorer.setToggleGroup(toggle);
        //Legger padding på radiobuttons
        zoomInn.setPadding(new Insets(25, 0, 25, 0));
        zoomUt.setPadding(new Insets(0, 0, 25, 0));
        panorer.setPadding(new Insets(0, 0, 25, 0));
        
         //tegn knappen
        tegn = new Button("Tegn");
        tegn.setText("Tegn");
        tegn.setOnAction((ActionEvent ActionEvent) -> {
            Mandelbrot.clear();
            Mandelbrot.mandelBrot(tegnVindu.getGraphicsContext2D());
        });
        
        //Bygger venstre siden av GUI
        venstre.setPrefWidth(100);
        venstre.setPadding(new Insets(150, 0, 100, 10));
        venstre.getChildren().addAll(zoomInn, zoomUt, panorer, tegn);
        venstre.setStyle("-fx-background-color: gray;");
       
        //Setter mouseevent på museklikk på canvas
        tegnVindu.setOnMouseClicked((MouseEvent e) -> {
            if (zoomInn.isSelected()) {
                Mandelbrot.zoomClick(e.getX(), e.getY());
                Mandelbrot.mandelBrot(tegnVindu.getGraphicsContext2D());
            } else if (panorer.isSelected()) {
                Mandelbrot.panorer(e.getX(), e.getY());
                Mandelbrot.mandelBrot(tegnVindu.getGraphicsContext2D());
            } else if (zoomUt.isSelected()) {
                Mandelbrot.zoomUt(e.getX(), e.getY());
                Mandelbrot.mandelBrot(tegnVindu.getGraphicsContext2D());
            }
        });
        //Legger til venstre GUI og canvas
        this.getChildren().addAll(venstre, tegnVindu);
    }

    /**
     * Metode som sletter eksisterende elementer på canvaset, slik
     * at det kan tegnes over på nytt
     */
    public void clear(){
        tegnVindu.getGraphicsContext2D().clearRect(0, 0, tegnVindu.getWidth(), tegnVindu.getHeight());
    }
}
