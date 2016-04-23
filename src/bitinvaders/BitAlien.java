package bitinvaders;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;


public class BitAlien extends Sprite {
	Color color;
	
	private int dx = 0;
    private int dy = 10;
    private int speed;
    private int x;
    private int y;
    private String myBits;
    
    private static int id = 0;
    
    private static final int WIDTH = 35;
    private static final int HEIGHT = 20;
    private static final int OFFSET = 5;
    
	
	public BitAlien(int speed){
		this.speed = speed;
		this.dy = speed;
		setSpeed();
		initAlien();
		this.id = id++;
	}
	
	private void randomize(){
		x = randomInt(0,Commons.BOARD_WIDTH - WIDTH);
		y = OFFSET;
		int binary = randomInt(0,15);
		myBits = Integer.toString(binary, 2);
		if(myBits.length() < 4)
			addPadding(myBits);
	}
	public void addPadding(String s){
		String temp = myBits;
		if(s.length() == 1){
			String t = "000";
			myBits = t + temp;
		}
		if(s.length() == 2){
			String t = "00";
			myBits = t + temp;
		}
		if(s.length() == 3){
			String t = "0";
			myBits = t + temp;
		}
	}
	
	public int randomInt(int min, int max){
		Random rand = null;

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

	    return randomNum;
	}
	
	private void initAlien(){

	}
	public void setSpeed(){
		switch (speed){
		case 1: color=Color.BLUE;
		case 2: color = Color.RED;
		}
	}
	public void move() {
		x += dx;
		y += speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
	public void drawAlien(Graphics g){
		if(!dying){
			g.setColor(Color.YELLOW);
			g.drawRect(x, y, WIDTH, HEIGHT);
			g.setColor(Color.YELLOW);
			g.drawString(myBits, x + OFFSET, y + HEIGHT - OFFSET);
		}
	}
	
	

//	@Override
//	public int compareTo(Alien arg0) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
