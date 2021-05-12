package com.snake.dijelovi_zmije;

import java.awt.Color;
import java.awt.Graphics;

/**
 * U ovoj klasi kreiramo dijelove tijela zmije.
 *
 */
public class BodyPart {
	
	private int xK, yK, sirina, visina;
	
	
	
	/**
	 * 
	 * @param xK
	 * @param yK
	 * @param vel
	 */
	public BodyPart(int xK, int yK, int vel) {
		this.xK = xK;
		this.yK = yK;
		sirina = vel;            
		visina = vel;
		
	}
	
	public void update() {
		
	}
	
	/**
	 * @param g
	 * Ovom metodom crtamo zmiju na koordinatama x i ys
	 */
	public void crtaj(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xK*sirina, yK * visina, sirina, visina);
		g.setColor(Color.BLUE);
		g.fillRect(xK*sirina+2, yK*visina+2, sirina-4, visina-4);
		
	}
	

	/**
	 * @return Vraca x koordinatu
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
	 * @return Vraca y koordinatu
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
