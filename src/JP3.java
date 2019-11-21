import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JP3 extends JPanel implements MouseListener {
	private boolean test=false;
	private boolean estvalide=false;
	private int posx;
	private int posy;
	public JP3(int posx1,int posy2) {
		super();
		this.addMouseListener(this);
		this.posx=posx1;
		this.posy=posy2;
		}
	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("ok");
		System.out.println(this.estvalide);
		if(this.estvalide==true) {
			System.out.println("sa fonctione normalement");		
			this.test=true;
		}
	}


	public boolean isEstvalide() {
		return estvalide;
	}
	public void setEstvalide(boolean estvalide) {
		this.estvalide = estvalide;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	
	
	
}
