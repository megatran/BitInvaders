package bitinvaders;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite{
	public boolean death = false;
	public static int numLives=3;
	private String input;
	public Player(int row, int col){
		super(row, col);
		
	}
	public int getNumLives(){
		return numLives;
	}
	public void maim(){
		//numLives--;
		if (numLives == 0){
			death=true;
		}
	}
	public void addHealth(){
		//numLives++;
	}
	public boolean isDead(){
		return death;
	}
}
