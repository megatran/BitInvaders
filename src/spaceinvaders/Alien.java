package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Alien extends Sprite {
	int speed;
	Color color;
	
	private int dx = 0;
    private int dy = 10;
    private int x;
    private int y;
    private Image image;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    
	
	public Alien(int row, int col, int speed){
		super(row, col);
		this.speed = speed;
		setSpeed();
		initAlien();
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
		y += dy;
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
			//g.fillRect(x, y, WIDTH, HEIGHT);
			g.drawString("1010101010", x, y);
		}
	}
	
	
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		
	}
}
