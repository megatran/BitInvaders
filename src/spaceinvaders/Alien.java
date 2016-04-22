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
	
	private int dx = 1;
    private int dy = 1;
    private int x;
    private int y;
    private Image image;
	
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

	/* private Bomb bomb;
	    private final String shot = "../spacepix/alien.png";

	    public Alien(int x, int y) {
	        this.x = x;
	        this.y = y;

	        bomb = new Bomb(x, y);
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
	        setImage(ii.getImage());

	    }

	    public void act(int direction) {
	        this.x += direction;
	    }

	    public Bomb getBomb() {
	        return bomb;
	    }

	    public class Bomb extends Sprite {

	        private final String bomb = "../spacepix/bomb.png";
	        private boolean destroyed;

	        public Bomb(int x, int y) {
	            setDestroyed(true);
	            this.x = x;
	            this.y = y;
	            ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
	            setImage(ii.getImage());
	        }

	        public void setDestroyed(boolean destroyed) {
	            this.destroyed = destroyed;
	        }

	        public boolean isDestroyed() {
	            return destroyed;
	        }
	    }
	  */
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		
	}
}
