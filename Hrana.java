package com.snake.dijelovi_zmije;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Kreiramo klasu Hrana koja sadrzi koordinate, konstruktor sa parametrima i metodu za crtanje hrane.
 *
 */
public class Hrana {
	
	private int xK, yK, sirina, visina;
	
	public Hrana(int xK, int yK, int vel) {
		this.xK = xK;
		this.yK = yK;
		sirina = vel;
		visina = vel;
		
	}
	
	
	public void update() {
		
	}
	
	/**
	 * @param g
	 * Ova metoda namjesta boju hrane na crvenu i postavlja je na datoj x i y koordinati
	 */
	public void crtajHranu(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xK * sirina,  yK * visina, sirina, visina); 
	}

	
	
	/**
	 * @return xK 
	 * Vraca x koordinatu
	 */
	public int getxK() {
		return xK;
	}


	/**
	 * @param xK
	 * Postavlja x koordinatu
	 */
	public void setxK(int xK) {
		this.xK = xK;
	}


	/**
	 * @return
	 * Vraca y koordinatu
	 */
	public int getyK() {
		return yK;
	}

	
	/**
	 * @param yK
	 * Postavlja y koordinatu
	 */
	public void setyK(int yK) {
		this.yK = yK;
	}

}
