package bitinvaders;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite{
	private int score = 0;
	public boolean death = false;
	public static int numLives=3;
	private String input;
	public Player(int row, int col){
		super(row, col);
		score = 0;
		numLives = 3;
	}
	public int getNumLives(){
		return numLives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void updateLife(int increment){
		numLives = numLives + increment;
		if (numLives<0) death=true;
		
	}
	
	public void updateScore(int increment) {
		score = score+increment;
		if (score < 0) {
			death = true;
		}
	}
	
	public void addHealth(){
		numLives++;
	}
	public boolean isDead(){
		return death;
	}
	public void resetScore(){
		this.score = 0;
	}
	
}
