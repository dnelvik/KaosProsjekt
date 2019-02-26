package kaosprosjekt;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Danay Nelvik
 * Klasse for å tegne og opprette et GUI-objekt for Cellular Automation
 */
public class CellAGUI extends HBox {
    
    Canvas tegnVindu = new Canvas(800, 600);
    VBox venstre = new VBox();
    RadioButton r1, r2, r3, r4, r5, r6, r7, r8;
    boolean re1, re2, re3, re4, re5, re6, re7, re8;
    
    /**
     * Konstruktør som oppretter et GUI for Cellular Automation
     * ved å kalle på tegnCAGUI() metoden
     */
    public CellAGUI(){
        tegnCAGUI();
    }
    
    /**
     * Metode som tegner GUI'en for Cellular Automation
     */
    public void tegnCAGUI() {
        //Radiobuttons
        r1 = new RadioButton("SSS");
        r2 = new RadioButton("SSH");
        r3 = new RadioButton("SHS");
        r4 = new RadioButton("SHH");
        r5 = new RadioButton("HSS");
        r6 = new RadioButton("HSH");
        r7 = new RadioButton("HHS");
        r8 = new RadioButton("HHH");
        //Legger padding på radiobuttons
        r1.setPadding(new Insets(0, 0, 20, 0));
        r2.setPadding(new Insets(0, 0, 20, 0));
        r3.setPadding(new Insets(0, 0, 20, 0));
        r4.setPadding(new Insets(0, 0, 20, 0));
        r5.setPadding(new Insets(0, 0, 20, 0));
        r6.setPadding(new Insets(0, 0, 20, 0));
        r7.setPadding(new Insets(0, 0, 20, 0));
        r8.setPadding(new Insets(0, 0, 20, 0));

        //tegn knappen
        Button tegn = new Button("Tegn");
        tegn.setText("Tegn");
        tegn.setOnAction((ActionEvent ActionEvent) -> {
            if (r1.isSelected()) re1 = true; else re1 = false;
            if (r2.isSelected()) re2 = true; else re2 = false;
            if (r3.isSelected()) re3 = true; else re3 = false;
            if (r4.isSelected()) re4 = true; else re4 = false;
            if (r5.isSelected()) re5 = true; else re5 = false;
            if (r6.isSelected()) re6 = true; else re6 = false;
            if (r7.isSelected()) re7 = true; else re7 = false;
            if (r8.isSelected()) re8 = true; else re8 = false;
            CellularAutomation.regler(re1, re2, re3, re4, re5, re6, re7, re8);
            clear();
            CellularAutomation.tegnCA(tegnVindu.getGraphicsContext2D());
        });
        
        //Bygger venstre side av GUI
        venstre.setPrefWidth(100);
        venstre.setPadding(new Insets(100, 0, 100, 10));
        venstre.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, tegn);
        venstre.setStyle("-fx-background-color: gray;");
        
        this.getChildren().addAll(venstre, tegnVindu);
    }

    /**
     * Metode som gjør visker ut feltet som skal tegnes på
     */
    public void clear(){
        tegnVindu.getGraphicsContext2D().clearRect(0, 0, tegnVindu.getWidth(), tegnVindu.getHeight());
    }
    
}
