package org.openjfx.controler;

import org.openjfx.model.Case;
import org.openjfx.model.Plateau;

import java.util.ArrayList;
import java.util.List;

public class Controler {
    private Plateau p;
    public Controler(){
         p=new Plateau();
    }
    public List<int[]> getCaseValide(){
        List<Case> estvoisin=p.estvoisin(8,8);
        ArrayList<int[]> result=new ArrayList<>();
        for (Case aCase : estvoisin) {
             result.add(aCase.getCoordone());

        }


        return result;
    }

}
