package bitinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener 	/*Runnable, Commons*/ {
	Queue<BitAlien> alienQueue = new LinkedList<BitAlien>();
	protected Player player;
	boolean ingame = true;
	private Timer timer;
	public static final int SPEED = 1000;
	//delay changes the speed of the aliens falling
	private final int DELAY = 100;
	
	// testing
	private Dimension d;
	int testCount = 0;
	
	public  Board(){
		initBoard();
	}
	
	public void initBoard(){
		// adding timer
		setFocusable(true);
		timer = new Timer(DELAY, this);
		timer.start();
		alienQueue = new LinkedList<BitAlien>();
		player = new Player(0,0);
		
		//testAlien = new BitAlien(10);
		//alienQueue.add(testAlien);

		// testing
		d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		setBackground(Color.black);
	}

	public boolean randomAdd(int min, int max){
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
		int r = ThreadLocalRandom.current().nextInt(min, max + 1);
	    if(r == max)
	    	return true;
	    return false;
	}

	public Player getPlayer(){
		return player;
	}

	public void checkInput(int keyTyped) {
		boolean found = false;
		for (BitAlien a: alienQueue) {
			if (keyTyped == a.getMyDecimalValue()) {
				alienQueue.remove(a);
				found = true;
				break;
			} else {
				found = false;
			}
		}
		if (found) {
			System.out.println("You found " + keyTyped);
			//increase player's score by the alien's decimal value
			player.updateScore(keyTyped);
			repaint();
			
		} else {
			//decrease player's score by that 
			player.updateScore(-5);
			System.out.println(keyTyped + " is INCORRECT");
		}

	}

	public Queue<BitAlien> getAlienQueue() {
		return alienQueue;
	}
	
	public void paint(Graphics g)
    {
      super.paint(g);
      
      g.setColor(Color.black);
      g.fillRect(0, 0, d.width, d.height);
      g.setColor(Color.green);   

      if (ingame) {
        //g.drawLine(0, Commons.GROUND, Commons.BOARD_WIDTH, Commons.GROUND);
        //g.drawImage(testAlien.getImage(), testAlien.getX(), testAlien.getY(), this);
     
        /*
        drawAliens(g);
        drawPlayer(g);
        drawShot(g);
        drawBombing(g);
        */
      }
      
      doDrawing(g);
     // g.drawImage(testAlien.getImage(), testAlien.getX(), testAlien.getY(), this);
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//testAlien.drawAlien(g);
		for(BitAlien a: alienQueue){
			a.drawAlien(g);
		}
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		makeAliens();
		
    }
	public void makeAliens(){
		if(randomAdd(1,4)){
			Random ran = new Random();
			int i = BitAlien.getID();
			//generates a number between half of the aliens generated and the number of aliens generated
			i=ran.nextInt(i+1)+i/4;
			//rounds up to the next 5
			i = i-i%5+5;
			//cut off at speed 20
			if (i>20){ i=20;}
			BitAlien newA = new BitAlien(i);
			alienQueue.add(newA);
		}
		testCount++;
		for(BitAlien a: alienQueue){
			a.move();
		}
        repaint();  
	}

	//only used for testing
	public void addAlien(BitAlien a){
		alienQueue.add(a);
	}
	public void pause(){
		timer.stop();
		for(BitAlien a: alienQueue){
			a.setVisible(false);
		}
			repaint();
		
	}

	public void unpause() {
		for(BitAlien a: alienQueue){
			a.setVisible(true);
		}
			repaint();
		
		timer.start();
	}
	
}
