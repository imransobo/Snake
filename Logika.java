package com.projekat.snake.logika;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.snake.dijelovi_zmije.*;



/**
 * Predmet: Razvoj softvera
 * Igrica: Snake
 * @author Korisnik
 * 
 *
 */
public class Logika extends JPanel implements Runnable {
	
	/**
	 * Ovim varijablama deklarisemo sirinu i visinu prozora igrice
	 */
	public static final int sirina = 800, visina = 800;      

	private Thread thread;
	private boolean pokrenuto = false;
	
	private Random r;
	
	private BodyPart b;
	private ArrayList<BodyPart> zmija;
	
	/**
	 * Instanca klase Hrana
	 */
	public Hrana hrana;
	
	
	/**
	 * Niz u koji pohranjujemo hranu za zmiju i u nizu se uvijek nalazi 1 element.
	 */
	public ArrayList<Hrana> hranaN;
	
	private int xK = 10, yK = 10, velicina = 5;
	
	private boolean desno = true, lijevo = false, gore = false, dolje = false;   //kretnje
	
	private int promjena = 0;
	
	private Key key;	
	
	private int skor;
	
	
	
	/**
	 * Kreiramo enum, tj grupu konstanti pomocu kojih lakse kontrolisemo prijelaz izmedju game-modova
	 *
	 */
	public static enum STANJE{
		MENI,
		IGRICA,
		IGRICA2
	};
	
	
	/**
	 * Postavljamo defaultno stanje na MENI kako bi prilikom pokretanja dobili game menu screen.
	 */
	public static STANJE stanje = STANJE.MENI;
	
	private Meni meni;
	
	
	/**
	 * Pravimo konstruktor Screen koji pozivamo u klasi Okvir
	 */
	public Logika() {
		
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(sirina, visina));
		r = new Random();
		
		hranaN = new ArrayList<Hrana>();
		zmija = new ArrayList<BodyPart>();
		
		meni = new Meni();
		
		this.addMouseListener(new MouseInput());

		start();
	}
	
	
	/**
	 * Metoda start() iz klase Thread.Kada je pozovemo zapocinje se izvrsavanje threada.
	 */
	public void start() {
		pokrenuto = true;
		thread = new Thread(this, "Igrica");
		thread.start();
		
	}
	
	
	/**
	 * Ova metoda provjerava sljedece:
	 * -ako je zmija prazna, napravi novi dio tijela <br>
	 * -ako je niz hranaN prazan, na (x,y) koordinati generisi hranu <br>
	 * -ako je hrana pojedena, povecaj velicinu zmije, skor i iz niza hranaN obrisi tu hranu da bi se generisala nova<br>
	 * -ako zmija krene da se vrati suprotno od smjera u kojem ide odnosno u sebe, poziva se metoda stop i izbacuje nam gameover<br>
	 * -sa logickim varijablama odredujemo kako se krecemo po x i y osi<br>
	 * -ako zmija izadje izvan mape , nastavlja sa druge strane<br>
	 * -ako zmija udari u zid, poziva se metoda stop i dobijamo game over<br>
	 */
	public void update() {
		
		// Ako je zmija prazna, napravi novi dio tijela
		if(zmija.size() == 0) {                      
			b = new BodyPart(xK, yK, 10);
			zmija.add(b);
		}
		
		
		
		// Korisnik Ako je niz hranaN prazan, na x i y koordinati generisi hranu 
		if(hranaN.size() == 0) {
			int xK = r.nextInt(79);
			int yK = r.nextInt(79);
					
			hrana = new Hrana(xK, yK, 10);
			hranaN.add(hrana);
		
			//timer
			//pokreniTimer();
			
		}
				
		
		// Ako je hrana pojedena, povecaj velicinu zmije i skor i iz niza hranaN obrisi tu hranu da bi se generisala nova
		for(int i=0; i<hranaN.size(); i++) {
			if(xK == hranaN.get(i).getxK() && yK == hranaN.get(i).getyK()) {
				velicina++;
				skor++;
				hranaN.remove(i);
				i--;
			}
		}
		
		
		
		// Ako zmija krene da se vrati suprotno od smjera u kojem ide odnosno u sebe, poziva se metoda stop i izbacuje nam gameover
		for(int i=0; i<zmija.size(); i++) {
			if(xK == zmija.get(i).getxK() && yK == zmija.get(i).getyK()) {
				if(i != zmija.size() - 1) {
					stop();
				}
			}
		}
		
		promjena++;
		
		// Sa logickim varijablama odredujemo kako se krecemo po x i y osi
		if(promjena > 620000) {
			if(desno) xK++;
			if(lijevo) xK--;
			if(gore) yK--;
			if(dolje) yK++;
			
			promjena = 0;
			
			// na trenutnim koordinatama pravimo novi dio tijela, tj. pomjeramo se
			b = new BodyPart(xK, yK, 10);
			zmija.add(b);
			
			// Obrisi zadnji dio tijela
			if(zmija.size() > velicina) {   
				zmija.remove(0);
			}
		}
		
		
		// Ako zmija izadje izvan mape , nastavlja sa druge strane
		switch (stanje) {
		case IGRICA:
			if(xK < 0) xK = 79;
			if(xK > 79) xK = 0;
			if(yK < 0) yK = 79;
			if(yK > 79) yK = 0;
			break;
		}
		
		// Ako zmija udari u zid, poziva se metoda stop i dobijamo game over
		switch (stanje) {
		case IGRICA2:
			if(xK < 0 || xK > 79 || yK < 0 || yK > 79) {
				stop();
			}
			break;
		}
		
				
	}
	
	
	/**
	 * Brisemo hranu ako stoji predugo, te generisemo novu na random x,y koordinati
	 */
	public void pokreniTimer() {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		
			@Override
			public void run() {
				hranaN.remove(0);
				hrana = new Hrana(xK, yK, 10);
				hranaN.add(hrana);
			}
		
		};
		
		timer.scheduleAtFixedRate(task, 10000, 10000);
		
	}
	
	

	/**
	 * Prikazivanje zmije i hrane na ploci
	 */
	public void paint(Graphics g) {
		
		// U zavisnosti na sta smo kliknuli , prelazimo u odgovarajuci game-mode
		if(stanje == STANJE.IGRICA || stanje == STANJE.IGRICA2) {		
			g.clearRect(0, 0, sirina, visina);
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, sirina, visina);
			
			g.setColor(Color.black);
			
			
			for(int i=0; i<zmija.size(); i++) {
				zmija.get(i).crtaj(g);
			}
			
			for(int i=0; i<hranaN.size(); i++) {
				hranaN.get(i).crtajHranu(g);
			}
			
		
			// Brojac score-a i prikaz gameOver
			if(pokrenuto) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Ink Free", Font.BOLD, 30));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Score:" + skor, (sirina - metrics.stringWidth("Score" + skor))/2, g.getFont().getSize());				
			}
			else {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Ink Free", Font.BOLD, 30));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Game over!", (sirina - metrics.stringWidth("Game over!"))/2, visina/2);
				g.drawString("Score: " + skor, (sirina - metrics.stringWidth("Score: "+ skor))/2, visina);
				
			}
				
		} 
		else if(stanje == STANJE.MENI) {
			meni.render(g);
		}
		
	
	}
	
	
	
	/**
	 * Metoda run() iz klase Thread.Kada je pozovemo , izvrsava se kod unutar nje.
	 */
	public void run() {
		while(pokrenuto) {
			update();
			repaint();
			
		}
		
	}
	
	
	/**
	 * Metoda stop() iz klase Thread.Kada je pozovemo, terminira se thread i mijenjamo logicku vrijednost pokrenuto na false.
	 */
	public void stop() {
		pokrenuto = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	private class Key implements KeyListener {
			
		/**
		 *Provjeravamo da li je stanje igrice 1 ili 2 i if-strukturom provjeravamo koja tipka na tastaturi je stisnuta i u zavisnosti od toga postavljamo log. vrijednosti(true i false)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			if (stanje == STANJE.IGRICA || stanje == STANJE.IGRICA2) {
				if(key == KeyEvent.VK_LEFT) {
					gore = false;
					desno = false;
					dolje = false;
					lijevo = true;
				}
				if(key == KeyEvent.VK_RIGHT) {
					lijevo = false;
					dolje = false;
					gore = false;
					desno = true;
				}
				if(key == KeyEvent.VK_DOWN) {
					lijevo = false;
					gore = false;
					desno = false;
					dolje = true;
				}
				if(key == KeyEvent.VK_UP) {
					desno = false;
					lijevo = false;
					dolje = false;
					gore = true;
				}
			}
			
			
		}	
	
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		
	
	
	}
	
}
