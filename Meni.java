package com.snake.dijelovi_zmije;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Koristeci klasu Rectangle,pravimo pocetni meni.
 *
 */
public class Meni {
	
	
	
	/**
	 * Ove dimenzije nam odreduju sirinu i visinu pocetnog menija
	 */
	public static final int sirina = 800, visina = 800;
	

	
	
	/**
	 * Kreiramo pravougaonik na koji mozemo kliknuti da bi odabrali game-mode bez ograde
	 */
	public Rectangle igraj = new Rectangle(370, 150, 100, 50);
	
	
	
	/**
	 * Kreiramo pravougaonik na koji mozemo kliknuti da bi odabrali game-mode sa ogradom
	 */
	public Rectangle igrajSaOgradom = new Rectangle(370, 250, 100, 50);

	
	
	/**
	 * Generisemo pocetni game meni.Imamo mogucnost da izaberemo game mode bez ograde ili sa ogradom.
	 * Implementirano pomocu klase Rectangle.
	 */
	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Snake", 350, visina/7);
		
		//nivo bez ograda
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.drawString("Igraj 1", igraj.x+5, igraj.y+30);
		
		//nivo sa ogradama
		g.setFont(font1);
		g.drawString("Igraj 2", igrajSaOgradom.x+5, igrajSaOgradom.y+30);
		
			
		g2.draw(igraj);
		g2.draw(igrajSaOgradom); 
		
	}

}
