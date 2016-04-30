package bitinvaders;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class SpecialBitAlien extends Sprite{
	private BitAlien top;
	private BitAlien bot;
	private int dx = 0;
    private int dy = 40;
    private int x;
    private int y;
    private int speed;
    private String topBits;
    private int topDecimalValue;
    private String botBits;
    private int botDecimalValue;
    private int totalDecimalValue;
    
    //one alien measurements
    private static final int WIDTH = 35;
    private static final int HEIGHT = 20;
    private static final int OFFSET = 5;

	public SpecialBitAlien(int speed) {
		this.speed = speed;
		this.dy = speed;
		top = new BitAlien(speed);
		this.x = top.getX();
		this.y = top.getY();
		initTopBot();
	}
	
	
	private void initTopBot(){
		bot = new BitAlien(speed);
		bot.setX(this.x);
		bot.setY(this.y + HEIGHT);
		topBits = top.getMyBits();
		botBits = bot.getMyBits();
		topDecimalValue = top.getMyDecimalValue();
		botDecimalValue = bot.getMyDecimalValue();
		totalDecimalValue = topDecimalValue + botDecimalValue;
	}
	
	public int getTotalDecimalValue() {
		return totalDecimalValue;
	}


	public void drawSpecialAlien(Graphics g){
		if(!dying && isVisible()){
			g.setColor(Color.RED);
			g.drawRect(x, y, WIDTH, HEIGHT * 2);
			// lets draw top one
			g.setColor(Color.RED);
			g.drawRect(x, y, WIDTH, HEIGHT);
			g.setColor(Color.RED);
			g.drawString(topBits, x + OFFSET, y + HEIGHT - OFFSET);
			// now bot
			g.setColor(Color.RED);
			g.drawRect(x, y + HEIGHT, WIDTH, HEIGHT);
			g.setColor(Color.RED);
			g.drawString(botBits, x + OFFSET, y + HEIGHT - OFFSET + HEIGHT);
		}

	}
	public void move() {
		x += dx;
		y += speed;
		if (y == Commons.BOARD_HEIGHT - 65){
			BitInvaders.board.getPlayer().updateLife(-1);
			BitInvaders.displayLife();
		}
		if (BitInvaders.board.getPlayer().getNumLives() <= 0 && !BitInvaders.isGameOver()){
			BitInvaders.setGameOverTrue();
			dying = true;
			JOptionPane.showMessageDialog(null,"You Die!"  +"!","Game Over!",JOptionPane.INFORMATION_MESSAGE);	
		}
		if (BitInvaders.isGameOver()){
			
		}
//		if (dying) {
//			JOptionPane.showMessageDialog(null,"You Die!"  +"!","Game Over!",JOptionPane.INFORMATION_MESSAGE);
//		}
		System.out.println(y);
	}
	public void printBits(){
		System.out.println("topBits = " + topBits);
		System.out.println("botBits = " + botBits);
	}
	public void printDecimals(){
		System.out.println("topDec = " + topDecimalValue);
		System.out.println("botDec = " + botDecimalValue);
	}
}
