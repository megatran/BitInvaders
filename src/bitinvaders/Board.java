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
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener 	/*Runnable, Commons*/ {
	Queue<BitAlien> alienQueue = new LinkedList<BitAlien>();
	ArrayList<SpecialBitAlien> specialList = new ArrayList<SpecialBitAlien>();
	protected Player player;
	boolean ingame = true;
	boolean boardGameOver = false;
	private Timer timer;
	public static final int SPEED = 1000;
	//delay changes the speed of the aliens falling
	private final int DELAY = 100;

	// testing
	private Dimension d;
	int testCount = 0;

	public  Board(){
		alienQueue.clear();
		specialList.clear();
		initBoard();
	}

	public void initBoard(){
		boardGameOver = false;
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
		if (timer.isRunning()){
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
			if (s.dying){
				alienQueue.remove(s);
				s = null;
			}
			if (keyTyped == s.getTotalDecimalValue()) {
				specialList.remove(s);
				alienQueue.clear();
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
		}
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
		for(SpecialBitAlien s: specialList){
			s.drawSpecialAlien(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clearAliens();
		makeAliens();
//		BitInvaders.makeNewGame();

	}
	public void updatePlayerLife(){
		if (player.getNumLives() == 0) {
			pause();
			boardGameOver = true;
			//pause();
			alienQueue.clear();
			specialList.clear();
			//BitInvaders.makeNewGame();
			makeNewGame();
		}
		else if (!boardGameOver) {
			player.updateLife(-1);
		}

		BitInvaders.displayLife(player.getNumLives());
	}

	public void makeNewGame(){
		initBoard();

//		String s = (String)JOptionPane.showConfirmDialog(
//			    null,
//			    "Would you like green eggs and ham?",
//			    "An Inane Question",
//			    JOptionPane.YES_NO_OPTION);
		Object[] possibilities = {"Yes", "No"};
		pause();
		String s1 = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Game over! Would you like to restart?",
		                    "BitInvaders",
		                    JOptionPane.YES_NO_OPTION,
		                    null,
		                    possibilities,
		                    "ham");
		//JOptionPane.showMessageDialog(null,"You Die!"  +"!","Game Over!",JOptionPane.INFORMATION_MESSAGE);
		if ((s1 != null) && (s1.length() > 0)) {
		    if (s1 == "Yes"){
		    	//BitInvaders.disposeJFrame(BitInvaders.getFrames());
//		    	board.alienQueue.clear();
//		    	board.specialList.clear();
		    	//BitInvaders game = new BitInvaders();
		    	//BitInvaders.board.setI
		    	//BitInvaders.setGameOver(false);
		    	unpause();
		    }
		    if (s1 == "No"){
		    	System.exit(1);
		    }
		}
		else{
			System.exit(1);
		}
	}

	public void clearAliens(){
		for (BitAlien a: alienQueue) {
			if (a.dying){
				updatePlayerLife();
				a = null;
				alienQueue.remove(a);
//				a = new BitAlien();
//				alienQueue.remove(a);

			}
		}
		for(SpecialBitAlien s: specialList){
			if (s.dying){
				updatePlayerLife();
				//s = new SpecialBitAlien();
				s = null;
				alienQueue.remove(s);

			}
		}
	}
	public void makeAliens(){

		if(alienQueue.size() > 4 && specialList.size() < 1){
			SpecialBitAlien special = new SpecialBitAlien(10);
			specialList.add(special);
		}
		else if(randomAdd(1,4)){
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
	
	public boolean isBoardGameOver() {
		return boardGameOver;
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