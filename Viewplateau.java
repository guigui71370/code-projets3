package Classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

@SuppressWarnings({ "serial", "unused" })
public class Viewplateau extends JFrame  {

	private JP3 tab[][]=new JP3[17][17];
	private JButton dep;
	private JButton mur;
	private JLabel j1;
	private JLabel j2;
	
	public Viewplateau() {
		
		setTitle("Quoridor");// nom de la fenetre
		setSize(1200,700);//taille de la fennetre
		setResizable(false);//modifier ou non la taille de la fennetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//position  d'origine
		
		
		
		GridBagConstraints gbc = new GridBagConstraints();
	
		
		
		 
		
		dep=new JButton();
		dep.setText("se déplacé");
		this.mur=new JButton("poser un mur");
		j1=new 	 JLabel("joueur bleu a"+" 10 mur");
		j2=new 	 JLabel("joueur vert a"+" 10 mur");
		j2.setVisible(false);
		
		JPanel content = new JPanel();//contenu principal
		
		
		
		JPanel contentlisteaction = new JPanel();// liste des action
		contentlisteaction.setLayout(new GridBagLayout());
		gbc.gridx=0;
		gbc.gridy=1;
		contentlisteaction.add(dep,gbc);
		
		dep.setPreferredSize(new Dimension(300, 100));
		gbc.gridx=0;
		gbc.gridy=2;
		contentlisteaction.add(mur,gbc);
		
		mur.setPreferredSize(new Dimension(300, 100));
		
		
		


		gbc.gridx=0;
		gbc.gridy=0;
		contentlisteaction.add(j2,gbc);
		contentlisteaction.add(j1,gbc);
		
			
		JPanel contentplateau = new JPanel();
		
		
		
		contentplateau.setPreferredSize(new Dimension(700, 700));
		contentplateau.setBackground(Color.WHITE);
	    //On d�finit le layout manager
	    
	    
	    
	    
		contentplateau.setLayout(new GridBagLayout());
	    
	    //L'objet servant  positionner les composants
	 
			
	    //On positionne la case de d�part du composant

	    //La taille en hauteur et en largeur

	    
	    for (int y=0 ; y<tab.length ; y++) {
			
			for (int x=0 ; x<tab.length ; x++) {
				tab[x][y] = new JP3(x,y);					// rempli le tableau de cases
			}
		}
	    
	    
	    
	 
	    
	    
		for (int y=0 ; y<tab.length ; y++) {
			for (int x=0 ; x<tab.length ; x++) {
				if(y%2==0) {
					tab[x][y].setBackground(Color.BLACK);
					tab[x][y].setPreferredSize(new Dimension(60, 60));
					if(x%2!=0) {
						tab[x][y].setBackground(Color.RED);
						tab[x][y].setPreferredSize(new Dimension(10, 60));
					}
				}else
				{
					tab[x][y].setBackground(Color.RED);
					if(x%2!=0 && y%2!=0) {
						tab[x][y].setPreferredSize(new Dimension(10, 10));
					}else {
						tab[x][y].setPreferredSize(new Dimension(60, 10));	}
				}
				
				gbc.gridx=2+x;
				gbc.gridy=y;
				
				
				contentplateau.add(tab[x][y], gbc);
				
			    
			}

			
			  


		}
		
		content.add(contentlisteaction);
		
		content.add(contentplateau);
		this.add(content);
		//this.setContentPane(content);
		
		tab[8][0].setBackground(Color.BLUE); //Utilisateur haut
		tab[8][16].setBackground(Color.green);// Utilisateur bas
		
		
		
		/*contentlisteaction.setVisible(true); 
		contentplateau.setVisible(true);
		content.setVisible(true);*/
		setVisible(true);
		
		
		
		
		
		
	}
	
	
	public JButton getMur() {
		return mur;
	}


	public JButton getTest() {
		return dep;
	}


	public void setColor(int posx, int posy, Color e) {
		
		this.tab[posx][posy].setBackground(e);
		
	}
	
	
	
	




	
	public JP3 getjp3(int posx,int posy) {
		return this.tab[posx][posy];
	}


	public void update1(int i) {
		if(this.j1.isVisible()==true){
			this.j1.setVisible(false);
			this.j2.setVisible(true);
			this.j2.setText("joueur vert a "+i+" mur");
		}
			
		
	}


	public void update2(int i) {
		// TODO Auto-generated method stub
		if(this.j2.isVisible()==true){ 
			this.j2.setVisible(false);
			this.j1.setVisible(true);
			this.j1.setText("joueur bleu a "+i+" mur");
		}
	}
	
	
}
