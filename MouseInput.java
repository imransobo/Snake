package com.snake.dijelovi_zmije;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.projekat.snake.logika.Logika;
import com.projekat.snake.logika.Logika.STANJE;



/**
 * Pomocu MouseEventa dobijamo (x,y) koordinate i provjeravamo da li smo stisnuli na Igraj 1 <br>
 * ili Igraj 2.U zavisnosti na sta smo kliknuli, mijenjamo stanje igrice na STANJE.IGRICA1 ili STANJE.IGRICA2
 *
 */
public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	/**
	 *Pomocu MouseEventa dobijamo x i y koordinate i provjeravamo da li smo stisnuli na Igraj 1 ili
	 * Igraj 2.U zavisnosti na sta smo kliknuli , mijenjamo stanje igrice na STANJE.IGRICA1 ili STANJE.IGRICA2
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();          //x koordinata misa
		int my = e.getY();          //y koordinata misa
		
		
	
		//Igraj 1 - bez ograde
		if(mx >= Logika.sirina/2 - 30 && mx <= Logika.sirina/2+220) {
			if(my >= 150 && my <= 200) {
				//stisnuo play 1
				Logika.stanje = Logika.STANJE.IGRICA;
			}
		}
		
		//Igraj 2 - sa ogradom
		if(mx >= Logika.sirina/2-800 && mx <= Logika.sirina/2+700) {
			if(my >= 250 && my <= 300) {
				//stisnuo play 2
				Logika.stanje = Logika.STANJE.IGRICA2;

			}
		}
		
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
