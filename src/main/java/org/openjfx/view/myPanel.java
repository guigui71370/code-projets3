package org.openjfx.view;


import javafx.scene.layout.Pane;

public class myPanel extends Pane {
    private int x,y;

    myPanel(int x, int y){
        super();
        System.out.println("---init---");
        this.x=x;
        this.y=y;

    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void active(){
        this.setDisabled(true);
    }
}
