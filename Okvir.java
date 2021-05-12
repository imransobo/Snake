package com.projekat.snake;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.projekat.snake.logika.Logika;

/**
 * U klasi Okvir pravimo novi okvir koristeci JFrame i pravimo instancu klase Logika
 *
 */
public class Okvir extends JFrame {
	
	/**
	 * Konstruktor Okvir()
	 */
	public Okvir() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		
		
		ucitaj();
	}
	
	
	/**
	 * U ovoj metodi pravimo novi objekat s.
	 */
	public void ucitaj() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Logika s = new Logika();
		add(s);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	
	/**
	 * @param args
	 * Pravimo novi Okvir
	 */
	public static void main(String[] args) {
		new Okvir();
	}
	
	
	
}
