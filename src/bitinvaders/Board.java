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
	public static final int SPEED = 20;
	//delay changes the speed of the aliens falling
	private final int DELAY = 1000;
	
	// testing
	private Dimension d;
	int testCount = 0;
	SpecialBitAlien special;
	ArrayList<SpecialBitAlien> specialList = new ArrayList<SpecialBitAlien>();
	
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
//		testing = new SpecialBitAlien(20);
//		testList.add(testing);
		BitAlien first = new BitAlien(SPEED);
		alienQueue.add(first);
		

		// testing
		d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		setBackground(Color.black);
	}

	public boolean randomAdd(int min, int max){
		Random rand = null;

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
		int r = ThreadLocalRandom.current().nextInt(min, max + 1);
	    //int randomNum = rand.nextInt((max - min) + 1) + min;
	    
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
		for(SpecialBitAlien s: specialList){
			if (keyTyped == s.getTotalDecimalValue()) {
				specialList.remove(s);
				found = true;
				alienQueue.clear();
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
		for(SpecialBitAlien s: specialList){
			s.drawSpecialAlien(g);
		}
		for(BitAlien a: alienQueue){
			a.drawAlien(g);
		}
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		makeAliens();
		
    }
	public void makeAliens(){
		if(alienQueue.size() > 4 && specialList.size() < 1){
			special = new SpecialBitAlien(SPEED/2);
			specialList.add(special);
		}

		else if(randomAdd(1,2)){
			BitAlien newA = new BitAlien(SPEED);
			alienQueue.add(newA);
		}
		testCount++;
		//System.out.println(testCount);
		for(BitAlien a: alienQueue){
			a.move();
		}
		for(SpecialBitAlien s: specialList){
			s.move();
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
		for(SpecialBitAlien s: specialList){
			s.setVisible(false);
		}
			repaint();
		
	}

	public void unpause() {
		for(BitAlien a: alienQueue){
			a.setVisible(true);
		}
		for(SpecialBitAlien s: specialList){
			s.setVisible(true);
		}
			repaint();
		
		timer.start();
	}
	
}
