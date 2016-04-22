package spaceinvaders;

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
	/* private final int START_Y = 280; 
	    private final int START_X = 270;

	    private final String player = "../spacepix/player.png";
	    private int width;

	    public Player(){

	        ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

	        width = ii.getImage().getWidth(null); 

	        setImage(ii.getImage());
	        setX(START_X);
	        setY(START_Y);
	    }

	    public void act() {
	        x += dx;
	        if (x <= 2) 
	            x = 2;
	        if (x >= BOARD_WIDTH - 2*width) 
	            x = BOARD_WIDTH - 2*width;
	    }

	    public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT)
	        {
	            dx = -2;
	        }

	        if (key == KeyEvent.VK_RIGHT)
	        {
	            dx = 2;
	        }
	    }

	    public void keyReleased(KeyEvent e) {
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT)
	        {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_RIGHT)
	        {
	            dx = 0;
	        }
	    }
*/
//	public Object getImage() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public void updateInput() {
		// TODO Auto-generated method stub
		
	}
	public String getInput() {
		// TODO Auto-generated method stub
		return null;
	}
	public void flushInput() {
		// TODO Auto-generated method stub
		
	}
}
