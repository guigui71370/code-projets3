package org.openjfx.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.openjfx.controler.Controler;

import java.util.ArrayList;
import java.util.List;


public class RootContent {
    @FXML
    private Label label;
    @FXML
    private AnchorPane test;


   private myPanel tab[][] = new myPanel[17][17];
    private GridPane grid=new GridPane();
    private Controler controler;

    public RootContent(){
        controler=new Controler();
    }

    public void initialize() {
        grid.setPrefSize(700, 700);
        for (int y = 0; y < 17; y++) {
            for (int x = 0; x < 17; x++) {
                myPanel pane = new myPanel(x, y);
                tab[x][y] = pane;
                ColorInput value;
                if (y % 2 == 0) {
                    pane.setPrefSize(60, 60);
                     value= new ColorInput(0,0,60,60, Color.BLACK);
                    if (x % 2 != 0) {
                         value = new ColorInput(0,0,10,60, Color.RED);
                        pane.setPrefSize(10, 60);
                    }
                } else {
                    if (x % 2 != 0) {
                        pane.setPrefSize(10, 10);
                        value = new ColorInput(0,0,10,10, Color.RED);
                    } else {
                        pane.setPrefSize(60, 10);
                        value = new ColorInput(0,0,60,10, Color.RED);
                    }
                }
                pane.setEffect(value);
                grid.add(pane,x,y);// rempli le tableau de cases


            }
        }




        System.out.println(        grid.getColumnCount()+" "+       grid.getRowCount());
        System.out.println(        grid.getColumnCount()+" "+       grid.getRowCount());
        //grid.setEffect(value);
        test.getChildren().add(grid);
    }

    private EventHandler<MouseEvent> mouseEvent= new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            myPanel panel = (myPanel) event.getSource();
            System.out.println(panel.getX());
            System.out.println(panel.getY());
            for (int[] coordonecase : caseValide) {
                myPanel panel2 = tab[coordonecase[0]][coordonecase[1]];
                panel2.setEffect(new ColorInput(0,0,60,60,  Color.BLACK));
                panel2.setOnMouseClicked(null);

            }

        }
    };

    private List<int[]> caseValide;
    @FXML
    private void handleButtonAction(ActionEvent event) {
       label.setText("Hello World!");
       // AnchorPane.setTopAnchor(grid,10.0);
        Object t=event.getSource();
        t.toString();
        this.caseValide=controler.getCaseValide();
        for (int[] coordonecase : caseValide) {
            myPanel panel = tab[coordonecase[0]][coordonecase[1]];
            Color color = Color.rgb(0, 114, 54);
            panel.setEffect(new ColorInput(0,0,60,60, color));
            panel.setOnMouseClicked(mouseEvent);

        }
    }




}
