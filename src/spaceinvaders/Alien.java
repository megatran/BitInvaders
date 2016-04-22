package spaceinvaders;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Alien extends Sprite {
	Color color;
	
	private int dx = 0;
    private int dy = 10;
    private int speed;
    private int x;
    private int y;
    private Image image;
    private static final int WIDTH = 35;
    private static final int HEIGHT = 20;
    private static final int OFFSET = 5;
    
	
	public Alien(int speed){
		this.speed = speed;
		this.dy = speed;
		setSpeed();
		initAlien();
	}
	
	private void randomize(){
		x = randomInt(1,1);
	}
	
	public int randomInt(int min, int max){
		Random rand = null;

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private void initAlien(){
		String path = "/images/alien.png";
		ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        x = 100;
        y = 100; 
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

	public Image getImage() {
		return image;
	}
	
	public void drawAlien(Graphics g){
		if(!dying){
			g.setColor(Color.YELLOW);
			g.drawRect(x, y, WIDTH, HEIGHT);
			g.setColor(Color.YELLOW);
			g.drawString("1010", x + OFFSET, y + HEIGHT - OFFSET);
		}
	}
	
	
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		
	}
}
