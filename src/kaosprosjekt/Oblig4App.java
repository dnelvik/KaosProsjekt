package kaosprosjekt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * App klassen som kjører programmet
 * @author Danay Nelvik
 */

public class Oblig4App extends Application {

    TabPane tabPane;
    Tab mandelbrot, ca, gol;
    BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) {

        //Lager tabs
        tabPane = new TabPane();
        mandelbrot = new Tab("Mandelbrot");
        ca = new Tab("Cellular Automation");
        gol = new Tab("Game of Life");
        tabPane.getTabs().addAll(mandelbrot, ca, gol);
        mandelbrot.setClosable(false);
        ca.setClosable(false);
        gol.setClosable(false);
        
        //Oppretter GUI'ene
        MandelGUI m = new MandelGUI();
        CellAGUI c = new CellAGUI();
        GOLView g = new GOLView();
        //Kobler GUI til tabs
        tabPane.setOnMouseClicked((MouseEvent event) -> {
            if (mandelbrot.isSelected()) {
                Mandelbrot.clear();
                m.clear();
                primaryStage.setHeight(660);
                primaryStage.setWidth(900);
                root.setCenter(m);
            } else if (ca.isSelected()) {
                primaryStage.setWidth(900);
                primaryStage.setHeight(660);
                c.clear();
                root.setCenter(c);
            } else if (gol.isSelected()){
                primaryStage.setWidth(800);
                primaryStage.setHeight(810);
                root.setCenter(g);
            }
        });
        
        root.setTop(tabPane);
        root.setCenter(m);
        
        Scene scene = new Scene(root, 900, 625);
        primaryStage.setWidth(900);
        primaryStage.setHeight(660);
        primaryStage.setTitle("Oblig 4 - Kaos prosjekt");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main metode
     * @param args
     */ 
    public static void main(String[] args) {
        launch(args);
    }
}
