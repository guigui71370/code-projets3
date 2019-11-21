package model;

import view.JP3;
import view.Viewplateau;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.ArrayList;
public class Plateau {

	// ---------------------- ATTRIBUTS ---------------------
	private JButton deplacement;
	private boolean dep=false;
	
	
	private JButton posermur;
	private boolean mur=false;
	
	private ArrayList<Case> voisin =new ArrayList<Case>();
	private Case[][] tabCases = new Case[17][17];
	private Pion p1;
	private Pion p2;
	private Viewplateau tab;
	
	
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
		tab=new Viewplateau();

		this.deplacement=tab.getTest();
		
		this.deplacement.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dep=true;
			}

		});
		this.posermur=tab.getMur();
		this.posermur.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				mur=true;
			}
		});
	}

	// ---------------- METHODES / FONCTIONS ----------------
	
	public boolean PartieEnCour()  {// fonction permettant de traiter le tour des deux joueur 
		int i=0;
		this.tab.update2(this.p1.getMurposseder());
		 
		 while(i==0) {
			 try{
					Thread.sleep(10); // sleep de 100 ms
					}catch(InterruptedException e){
					  e.printStackTrace();
					}

			 if(this.dep==true) {
				System.out.println("Tour J1");
				 	this.tourp1(); 
				 	this.dep=false;
				 	i=-1;
			}else if(this.mur==true && this.p1.getMurposseder()>0) {
				 	this.posermur(this.p1);
				 	this.mur=false;
				 	i=-1;
			}
			 
		 }
	
		 int ancX=this.p1.getPosx();
		 int ancY=this.p1.getPosy();
		
		 if(this.tabCases[ancX][ancY].estGagnantbleu()==true) {
			System.out.println("Le joueur bleu a gagné");
			return true;
		}
		 
		 i=0;
		 this.tab.update1(this.p2.getMurposseder());
		 while(i==0) {
		 
			 try{
					Thread.sleep(10); // sleep de 100 ms
					  
					}catch(InterruptedException e){
					  e.printStackTrace();
					}

			 if(this.dep==true) {
			 
				System.out.println("Tour J2");
			 	this.tourp2(); 
			 	this.dep=false;
			 	i=-1;
		}else if(this.mur==true && this.p2.getMurposseder()>0) {
				System.out.println("Poser Mur J2");
			 	this.posermur(this.p2);
			 	this.mur=false;
			 	i=-1;
		}
		
	}
		int ancXp2=this.p2.getPosx();
		int ancYp2=this.p2.getPosy();

		if(this.tabCases[ancXp2][ancYp2].estGagnantvert()==true) {
			System.out.println("Le joueur vert a gagné");
			return true;
		}
		return false;
	}
		
         	public void tourp1() {
		/*cette fonction sert a déplacé le pion du joueur 1 */
		Color c=new Color(0, 114, 54);
		int compteur=0;
		ArrayList<JP3> list =new ArrayList<JP3>();
		int ancX=this.p1.getPosx();
		int ancY=this.p1.getPosy();
		this.voisin=this.estvoisin(ancX, ancY);
		for(int i=0;i<this.voisin.size();i++) {
			list.add(this.tab.getjp3(this.voisin.get(i).getPosx(),this.voisin.get(i).getPosy()));
			this.tab.setColor(this.voisin.get(i).getPosx(),this.voisin.get(i).getPosy(),c);
		}
		boolean tab1[]=new boolean [list.size()];
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setEstvalide(true);
		}
		for (int i=0;i<tab1.length;i++) {
			tab1[i]=list.get(i).isTest();
		}
		
		
		boolean ok=list.get(0).isTest();
		System.out.println("3");
		

		while(ok==false) {
			try{
				Thread.sleep(10); // sleep de 10 ms
				}catch(InterruptedException e){
				  e.printStackTrace();
				}
			
			for(int i=0;i<tab1.length;i++) {
				tab1[i]=list.get(i).isTest();

				if(tab1[i]==true) {
					ok=true;					
				}
				
			}
			
		}
		System.out.println("4");
		while(tab1[compteur]==false) {
			compteur+=1;
		}
		int posX=0;
		int posY=0;
		if(tab1[compteur]){
			posX=list.get(compteur).getPosx();
			posY=list.get(compteur).getPosy();
		}
		this.tabCases[ancX][ancY].setOccupee(false);
		this.tab.setColor(ancX, ancY, Color.black);
		this.tab.setColor(posX, posY, Color.blue);
		this.tabCases[posX][posY].setOccupee(true);
		this.p1.seDeplacer(posX, posY);
		//System.out.println("5");
		for(int i=0;i<this.voisin.size();i++) {
			System.out.println("5");
			list.get(i).setEstvalide(false);
			
		}
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setTest(false);
			if(this.tabCases[list.get(i).getPosx()][list.get(i).getPosy()].estOccupee()==false) {
				this.tab.setColor(list.get(i).getPosx(),list.get(i).getPosy(),Color.BLACK);
			}
		}

		list =new ArrayList<JP3>();
		this.voisin=new ArrayList<Case>();
		System.out.println("Fin");
	}
	
	public void tourp2() {/*cette fonction sert a déplacé le pion du joueur 2 */

		int compteur=0;
		Color c=new Color(0, 114, 54);
		ArrayList<JP3> list =new ArrayList<JP3>();

		int ancX=this.p2.getPosx();
		int ancY=this.p2.getPosy();
		this.voisin=this.estvoisin(ancX, ancY);
		for(int i=0;i<this.voisin.size();i++) {
			list.add(this.tab.getjp3(this.voisin.get(i).getPosx(),this.voisin.get(i).getPosy()));
			this.tab.setColor(this.voisin.get(i).getPosx(),this.voisin.get(i).getPosy(),c);
		}
		boolean tab1[]=new boolean [list.size()];
		
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setEstvalide(true);
			
		}
				
		for (int i=0;i<tab1.length;i++) {
			tab1[i]=list.get(i).isTest();;
		}
		
		
		boolean ok=false;
		while(ok==false) {
			try{
				Thread.sleep(10); // sleep de 10 ms
				  
				}catch(InterruptedException e){
				  e.printStackTrace();
				}
			for(int i=0;i<tab1.length;i++) {
				this.deplacement.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("2");
						dep=true;
					}
				});
				tab1[i]=list.get(i).isTest();

				if(tab1[i]==true) {
					ok=true;					
				}
			}
		}
		while(tab1[compteur]==false) {
			compteur+=1;
		}
		int posX=0;
		int posY=0;
		if(tab1[compteur]){
			posX=list.get(compteur).getPosx();
			posY=list.get(compteur).getPosy();
		}

		this.tabCases[ancX][ancY].setOccupee(false);
		this.tab.setColor(ancX, ancY, Color.black);
		this.tab.setColor(posX, posY, Color.GREEN);
		this.tabCases[posX][posY].setOccupee(true);
		this.p2.seDeplacer(posX, posY);
	
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setEstvalide(false);
			
		}
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setTest(false);
			if(this.tabCases[list.get(i).getPosx()][list.get(i).getPosy()].estOccupee()==false) {
				this.tab.setColor(list.get(i).getPosx(),list.get(i).getPosy(),Color.BLACK);
			}
			
		}
		list =new ArrayList<JP3>();
		this.voisin=new ArrayList<Case>();
	}


	public void posermur(Pion p) {
	/*action: cette fonction sert à poser un  mur  pour un joueur
	  Paramètre : prend le pion du joueur qui veut poser un mur
	*/

		int compteur=0;
		ArrayList<Case> morte=new ArrayList<Case>();
		ArrayList<JP3> list =new ArrayList<JP3>();
		ArrayList<Case> enplacementmur=new ArrayList<Case>();
		for(int y=0;y<this.tabCases.length;y++) {
			for(int x=0;x<this.tabCases.length;x++) {
				if(this.tabCases[x][y].isEstmorte()==true && this.tabCases[x][y].estOccupee()==false) {
					if((this.tabCases[x+1][y].estOccupee()==false && this.tabCases[x-1][y].estOccupee()==false) ) {
					
					morte.add(this.tabCases[x][y]);
					}else if(this.tabCases[x][y-1].estOccupee()==false && this.tabCases[x][y+1].estOccupee()==false){
						morte.add(this.tabCases[x][y]);
					}
				}
			}	
		}
		for(int i=0;i<morte.size();i++) {
			list.add(this.tab.getjp3(morte.get(i).getPosx(),morte.get(i).getPosy()));
		}
		boolean tab1[]=new boolean [list.size()];
		for(int i=0;i<morte.size();i++) {
			list.get(i).setEstvalide(true);
		}	
		for (int i=0;i<tab1.length;i++) {
			tab1[i]=list.get(i).isTest();;
		}
				
		boolean ok=false;
		while(!ok) {
			try{
				Thread.sleep(10); // sleep de 10 ms
				  
				}catch(InterruptedException e){
				  e.printStackTrace();
				}
			
			for(int i=0;i<tab1.length;i++) {

				tab1[i]=list.get(i).isTest();

				if(tab1[i]==true) {
					ok=true;					
				}
				
			}
			
		}
		
		while(tab1[compteur]==false) {
			compteur+=1;
		}
		int posXmorte=0;
		int posYmorte=0;
		if(tab1[compteur]){
			posXmorte=list.get(compteur).getPosx();
			posYmorte=list.get(compteur).getPosy();
		}
		
		if(this.tabCases[posXmorte][posYmorte].estOccupee()==false) {
			this.tabCases[posXmorte][posYmorte].setOccupee(true);
			this.tab.setColor(posXmorte, posYmorte, Color.cyan);
		}
			
		for(int i=0;i<list.size();i++) {
			list.get(i).setEstvalide(false);
		
			
		}
		for(int i=0;i<list.size();i++) {
			list.get(i).setTest(false);
			
		}
		

		list =new ArrayList<JP3>();//supression des donnee
		
		if(this.tabCases[posXmorte-1][posYmorte].isEstmorte()==false && this.tabCases[posXmorte-1][posYmorte].estEmplacementMur()==true && this.tabCases[posXmorte-1][posYmorte].estOccupee()==false  &&this.tabCases[posXmorte+1][posYmorte].estOccupee()==false) {
			enplacementmur.add(this.tabCases[posXmorte-1][posYmorte]);
		}

		if(this.tabCases[posXmorte+1][posYmorte].isEstmorte()==false && this.tabCases[posXmorte+1][posYmorte].estEmplacementMur()==true && this.tabCases[posXmorte-1][posYmorte].estOccupee()==false  &&this.tabCases[posXmorte+1][posYmorte].estOccupee()==false) {
			enplacementmur.add(this.tabCases[posXmorte+1][posYmorte]);
		}

		if(this.tabCases[posXmorte][posYmorte-1].isEstmorte()==false && this.tabCases[posXmorte][posYmorte-1].estEmplacementMur()==true && this.tabCases[posXmorte][posYmorte-1].estOccupee()==false  &&this.tabCases[posXmorte][posYmorte+1].estOccupee()==false) {
			enplacementmur.add(this.tabCases[posXmorte][posYmorte-1]);
		}
		
		if(this.tabCases[posXmorte][posYmorte+1].isEstmorte()==false && this.tabCases[posXmorte][posYmorte+1].estEmplacementMur()==true && this.tabCases[posXmorte][posYmorte-1].estOccupee()==false  &&this.tabCases[posXmorte][posYmorte+1].estOccupee()==false) {
			enplacementmur.add(this.tabCases[posXmorte][posYmorte+1]);
		}

		for(int i=0;i<enplacementmur.size();i++) {
			list.add(this.tab.getjp3(enplacementmur.get(i).getPosx(),enplacementmur.get(i).getPosy()));
		}
		boolean tab2[]=new boolean [list.size()];
				
		for(int i=0;i<list.size();i++) {
			list.get(i).setEstvalide(true);
		}	
		for (int i=0;i<tab2.length;i++) {
			tab2[i]=list.get(i).isTest();;
		}
		
		compteur=0;
		ok=false;
		while(ok==false) {
			try{
				Thread.sleep(10); // sleep de 10 ms 
				}catch(InterruptedException e){
				  e.printStackTrace();
				}
			for(int i=0;i<tab2.length;i++) {
				tab2[i]=list.get(i).isTest();
				if(tab2[i]==true) {
					ok=true;					
				}	
			}
		}
		while(tab2[compteur]==false) {
			compteur+=1;
		}
		
		int posXmur1=0;
		int posYmur1=0;
		if(tab2[compteur]){
			posXmur1=list.get(compteur).getPosx();
			posYmur1=list.get(compteur).getPosy();
		}
		
		int posXmur2=0;
		int posYmur2=0;
		if(posXmur1+1== posXmorte && posYmur1== posYmorte) {
			posXmur2=posXmorte+1;
			posYmur2=posYmorte;
		}
		if(posXmur1-1== posXmorte && posYmur1== posYmorte) {
			posXmur2=posXmorte-1;
			posYmur2=posYmorte;
		}
		
		if(posXmur1== posXmorte && posYmur1+1== posYmorte) {
			posXmur2=posXmorte;
			posYmur2=posYmorte+1;
		}
		
		
		if(posXmur1== posXmorte && posYmur1-1== posYmorte) {
			posXmur2=posXmorte;
			posYmur2=posYmorte-1;
		}
	
		this.tabCases[posXmur1][posYmur1].setOccupee(true);
		
		this.tabCases[posXmur2][posYmur2].setOccupee(true);
		
			
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setEstvalide(false);
		}
		for(int i=0;i<this.voisin.size();i++) {
			list.get(i).setTest(false);
			
		}
		list =new ArrayList<JP3>();//supression des donnee
		if(this.arbitre(this.p1)>0 && this.arbitre(this.p2)>0) {//problème arbitre
			this.tab.setColor(posXmur1, posYmur1, Color.cyan);
			this.tab.setColor(posXmur2, posYmur2, Color.cyan);
			p.setMurposseder(p.getMurposseder()-1);
		}else {
			//System.out.println(posXmorte+ posXmorte);
			this.tab.setColor(posXmorte, posYmorte, Color.red);
			this.tabCases[posXmur1][posYmur1].setOccupee(false);
			this.tabCases[posXmorte][posYmorte].setOccupee(false);
			this.tabCases[posXmur2][posYmur2].setOccupee(false);
		}
		
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
