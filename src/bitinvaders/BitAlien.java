package bitinvaders;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BitAlien extends Sprite {
	private Color color;
	
	private int dx = 0;
    private int dy = 40;
    private int speed;
    private int x;
    private int y;
    private String myBits;
    private int myDecimalValue;
    
   
    
 
    
    private static final int WIDTH = 35;
    private static final int HEIGHT = 20;
    private static final int OFFSET = 5;
    
	
    public BitAlien(){
    	
    }
	public BitAlien(int speed){
		this.speed = speed;
		this.dy = speed;
		setColor();
		randomize();
	}
	
	public int getMyDecimalValue() {
		return myDecimalValue;
	}
	public Color getColor(){return color;};

	public void randomize(){
		x = randomInt(0,Commons.BOARD_WIDTH - WIDTH);
		y = OFFSET;
		myDecimalValue = randomInt(0,15);
		myBits = Integer.toString(myDecimalValue, 2);
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
	public void setMyBits(String t){
		myBits = t;
	}
	public int randomInt(int min, int max){
		Random rand = null;

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

	    return randomNum;
	}
	
	public void setColor(){
		switch (speed){
		case 5: color = Color.cyan;
			break;
		case 10: color = Color.green;
			break;
		case 15: color = Color.yellow;
			break;
		case 20: color = Color.magenta;
			break;
		}
	}
	
	public void move() {
		x += dx;
		y += speed;
		if (y >= Commons.BOARD_HEIGHT - 200){
//			BitInvaders.board.getPlayer().updateLife(-1);
//			BitInvaders.displayLife();
			dying = true;		
		}
//		if (BitInvaders.board.getPlayer().getNumLives() <= 0 && !BitInvaders.isGameOver()){
//			BitInvaders.setGameOverTrue();
//			dying = true;
////			String s = (String)JOptionPane.showConfirmDialog(
////				    null,
////				    "Would you like green eggs and ham?",
////				    "An Inane Question",
////				    JOptionPane.YES_NO_OPTION);
//			Object[] possibilities = {"Yes", "No"};
//			String s1 = (String)JOptionPane.showInputDialog(
//			                    null,
//			                    "Game over! Would you like to restart?",
//			                    "BitInvaders",
//			                    JOptionPane.YES_NO_OPTION,
//			                    null,
//			                    possibilities,
//			                    "ham");
//			//JOptionPane.showMessageDialog(null,"You Die!"  +"!","Game Over!",JOptionPane.INFORMATION_MESSAGE);
//			if ((s1 != null) && (s1.length() > 0)) {
//			    if (s1 == "Yes"){
//			    	BitInvaders.disposeJFrame(BitInvaders.getFrames());
//			    	BitInvaders.board.alienQueue.clear();
//			    	BitInvaders.board.specialList.clear();
//			    	BitInvaders game = new BitInvaders();
//			    	//setId(0);
//			    	BitInvaders.setGameOver(false);
//			    }
//			    if (s1 == "No"){
//			    	System.exit(dx);
//			    }
//			}
//			else{
//				System.exit(dx);
//			}
		
//		if (dying) {
//			JOptionPane.showMessageDialog(null,"You Die!"  +"!","Game Over!",JOptionPane.INFORMATION_MESSAGE);
//		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
	public void setX(int x) {
		this.x = x;
	}

	public String getMyBits() {
		return myBits;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void drawAlien(Graphics g){
		if(!dying && isVisible()){
			g.setColor(color);
			g.drawRect(x, y, WIDTH, HEIGHT);
			g.setColor(color);
			g.drawString(myBits, x + OFFSET, y + HEIGHT - OFFSET);
		}

	}

//	@Override
//	public int compareTo(Alien arg0) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}