package org.openjfx.model;

import org.openjfx.view.JP3;
import org.openjfx.view.Viewplateau;

import java.awt.Color;
import java.util.ArrayList;
public class Plateau {

	// ---------------------- ATTRIBUTS ---------------------

	private ArrayList<Case> voisin =new ArrayList<Case>();
	private Case[][] tabCases = new Case[17][17];
	private Pion p1;
	private Pion p2;
	private Viewplateau tab;
	Color c=new Color(0, 114, 54);

	// -------------------- CONSTRUCTEUR --------------------

	public Plateau () {
		// Creation pions :
		this.p1=new Pion (8,0,1);
		this.p2=new Pion (8,16,2);
		// Creation plateau de 17x17 cases :
		for (int y=0 ; y<tabCases.length ; y++) {
			for (int x=0 ; x<tabCases.length ; x++) {
				tabCases[x][y] = new Case(x,y);
			}
		}
		// Cases gagnantes de p1 :
		for (int i=0 ; i<16 ; i=i+2) {
			this.p1.casesGagnantesPion.add(tabCases[i][16]);
		}
		// Cases gagnantes de p2 :
		for (int i=0 ; i<16 ; i=i+2) {
			this.p2.casesGagnantesPion.add(tabCases[i][0]);
		}
		// MaJ plateau :
		tabCases[8][0].setOccupee(true);						// il y a le pion p1
		tabCases[8][16].setOccupee(true); 						// il y a le pion p2



	}

	// ---------------- METHODES / FONCTIONS ----------------

	public boolean PartieEnCour()  {// fonction permettant de traiter le tour des deux joueur
		return false;
	}

	public void tourp1() {

	}

	public void tourp2() {/*cette fonction sert a déplacé le pion du joueur 2 */

	}


	public void posermur(Pion p) {
	/*action: cette fonction sert à poser un  mur  pour un joueur
	  Paramètre : prend le pion du joueur qui veut poser un mur
	*/



	}

	protected int arbitre(Pion pion) {
		// Si possible de gagner : renvoie distance entre 'pion' et premiere case gagnante
		// Sinon, renvoie -1
		int z=0;
		ArrayList<Case> atester = new ArrayList<Case>();// Liste des cases a  tester
		ArrayList<Case> dejateste = new ArrayList<Case>();// Liste des cases deja  testees
		ArrayList<Case> listevoisins=new ArrayList<Case>();
		atester.add(this.tabCases[pion.getPosx()][pion.getPosy()]);// Ajoute la case actuelle a  atester
		// Tant qu'il reste des cases a tester ou qu'il n'a pas acces a une case gagnante
		this.tabCases[pion.getPosx()][pion.getPosy()].distance=0;
		while(atester.size()>0) {
			if(listevoisins.size()==0) {
				listevoisins =this.estvoisin(atester.get(0).getPosx(), atester.get(0).getPosy());
			}

			while(atester.size()>z && listevoisins.size()!=0 ) {
				if(listevoisins.get(0)==atester.get(z)) {
					listevoisins.remove(0);
				}
				z++;
			}
			z=0;
			while( dejateste.size()>z && listevoisins.size()!=0 ) {
				if(listevoisins.get(0)==dejateste.get(z)) {
					listevoisins.remove(0);
				}
				z++;
			}
			if(listevoisins.size()!=0) {
				listevoisins.get(0).distance=atester.get(0).distance+1;
				atester.add(listevoisins.get(0));
				listevoisins.remove(0);
			}
			z=0;
			if(listevoisins.size()==0) {
				while( z!=pion.casesGagnantesPion.size()  &&  atester.get(0)!=pion.casesGagnantesPion.get(z)) {
					z++;
				}
				if(pion.casesGagnantesPion.size()>z) {
					System.out.println("taille "+atester.get(0).distance);

					int result=atester.get(0).distance;
					for (int y=0 ; y<tabCases.length ; y++) {
						for (int x=0 ; x<tabCases.length ; x++) {
							tabCases[x][y].distance=-1;
						}
					}




					return result;
				}
				dejateste.add(atester.get(0));
				atester.remove(0);
			}
			System.out.println(atester.size());
		}
		return -1;
	}

	public ArrayList<Case> estvoisin(int posx, int posy) {
		// Renvoie la liste des voisins accessibles depuis la case dont les positions sont en entrée

		ArrayList<Case> courant = new ArrayList<Case>();

		if (posy<=14 && !this.tabCases[posx][posy + 1].estOccupee()) {    						 // S'il n'est pas sur un bord et pas de mur qui bloque
			if (!this.tabCases[posx][posy + 2].estOccupee()) {   								 // Si la case est libre
				courant.add(this.tabCases[posx][posy+2]); }    									// On l'ajoute
			else {    													 // Si la case est occupée par le pion adverse
				if (posy<=12 && !this.tabCases[posx][posy + 4].estOccupee() && !this.tabCases[posx][posy + 3].estOccupee()) courant.add(this.tabCases[posx][posy+4]);
				else {
					if (posx<=14 && !this.tabCases[posx + 1][posy + 2].estOccupee()) {   							 // S'il n'y a pas de mur à droite du pion adverse
						courant.add(this.tabCases[posx+2][posy+2]); }   												 // On ajoute case à droite de l'adversaire
					if (posx>=2 && !this.tabCases[posx - 1][posy + 2].estOccupee()) {   								 // S'il n'y a pas de mur à gauche de l'adversaire
						courant.add(this.tabCases[posx-2][posy+2]); }   												 // On ajoute la case à gauche de l'adversaire
				}
			}
		}
		if (posy>=2 && !this.tabCases[posx][posy - 1].estOccupee()) {// en bas
			if (!this.tabCases[posx][posy - 2].estOccupee()) {
				courant.add(this.tabCases[posx][posy-2]); }
			else {
				if (posy>=4 && !this.tabCases[posx][posy - 4].estOccupee() && !this.tabCases[posx][posy - 3].estOccupee()) courant.add(this.tabCases[posx][posy-4]);
				else {
					if (posx<=14 && !this.tabCases[posx + 1][posy - 2].estOccupee()) {
						courant.add(this.tabCases[posx+2][posy-2]); }
					if (posx>=2 && !this.tabCases[posx - 1][posy - 2].estOccupee()) {
						courant.add(this.tabCases[posx-2][posy-2]); }
				}
			}
		}
		if (posx<=14 && !this.tabCases[posx + 1][posy].estOccupee()) {// a droite
			if (!this.tabCases[posx + 2][posy].estOccupee()) {
				courant.add(this.tabCases[posx+2][posy]); }
			else {
				if (posx<=12 && !this.tabCases[posx + 4][posy].estOccupee() && !this.tabCases[posx + 3][posy].estOccupee()) courant.add(this.tabCases[posx+4][posy]);
				else {
					if (posy >= 2 && !this.tabCases[posx + 2][posy - 1].estOccupee()) {
						courant.add(this.tabCases[posx+2][posy-2]); }
					if (posy <= 14 && !this.tabCases[posx + 2][posy + 1].estOccupee()) {
						courant.add(this.tabCases[posx+2][posy+2]); }
				}
			}
		}
		if (posx>=2 && !this.tabCases[posx - 1][posy].estOccupee()) { // a gauche
			if (!this.tabCases[posx - 2][posy].estOccupee()) {
				courant.add(this.tabCases[posx-2][posy]); }
			else {
				if (posx>=4 && !this.tabCases[posx - 4][posy].estOccupee() && !this.tabCases[posx - 3][posy].estOccupee()) courant.add(this.tabCases[posx-4][posy]);
				else {
					if (posy>=2 && !this.tabCases[posx - 2][posy - 1].estOccupee()) {
						courant.add(this.tabCases[posx-2][posy-2]); }
					if (posy<=14 && !this.tabCases[posx - 2][posy + 1].estOccupee()) {
						courant.add(this.tabCases[posx-2][posy+2]); }
				}
			}
		}
		return courant; }
}
