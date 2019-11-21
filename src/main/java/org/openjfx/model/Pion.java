package org.openjfx.model;

import java.util.ArrayList;
public class Pion {
	
	// ---------------------- ATTRIBUTS ---------------------
	
	private int posx;
	private int posy;
	protected ArrayList<Case> casesGagnantesPion = new ArrayList<Case>();
	private int numero;
	private int murposseder=10;
	
	// -------------------- CONSTRUCTEUR --------------------
	


	public int getNumero() {
		return numero;
	}


	public Pion (int x, int y, int num) {
		this.posx = x;
		this.posy = y;
		this.numero = num;
	}

	
	// ---------------- METHODES / FONCTIONS ----------------
	
	public boolean seDeplacer(int posX, int posY) {
		
			
				this.posx=posX;
				this.posy=posY;
				return true;

		
		
	}
	
	
	public void deplacementpardessus(int posX, int posY) {
		this.posx=posX;
		this.posy=posY;
	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}
	
	
	public int getMurposseder() {
		return murposseder;
	}

	public void setMurposseder(int murposeder) {
		this.murposseder = murposeder;
	}
	
	
}
