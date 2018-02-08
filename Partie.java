package Classes;

public class Partie {
	
	// ---------------------- ATTRIBUTS ---------------------
	
	private boolean estFinie;
	private Plateau plateau;
	// -------------------- CONSTRUCTEUR --------------------
	
	public Partie () {
		this.estFinie = false;
			// pion en bas au centre
		
		this.plateau = new Plateau ();	// plateau avec les deux pions pos�s
		
	}
	
	// ---------------- METHODES / FONCTIONS ----------------
	
	public void jouer () {
		
		
		
		
		
		while(this.estFinie==false) {
				this.estFinie=this.plateau.PartieEnCour();
		}
		System.exit(0);
	}
	
	
	
	public static void pause(long l) {
		try{
			Thread.sleep(l); // sleep de 100 ms
			  
			}catch(InterruptedException e){
			  e.printStackTrace();
			}
	}
		
	
}
