package spaceinvaders;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Alien extends Sprite {
	int speed;
	Color color;
	public Alien(int row, int col, int speed){
		super(row, col);
		this.speed = speed;
		setSpeed();
	}
	public void setSpeed(){
		switch (speed){
		case 1: color=Color.BLUE;
		case 2: color = Color.RED;
		}
	}
	public void move(){
		
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
	public Object getImage() {
		// TODO Auto-generated method stub
		return null;
	}  
}
