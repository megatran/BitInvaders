package InvaderTests;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.BeforeClass;
import org.junit.Test;

import spaceinvaders.Board;

public class RunTests {
	Board board;
	@BeforeClass
	public void setUp() {
	board = new Board();
	}

		
	

	@Test
	public void testImage() {
		// board was "this", might have to change.
		ImageIcon ii = new ImageIcon(board.getClass().getResource("../spacepix/alien.png"));
		Alien alien = new Alien(0, 0);
		alien.setImage(ii.getImage());
		if(alien.getImage() != null)
			assertTrue("This is true", true);
		Player p = new Player();
		if(p.getImage() != null)
			assertTrue("This is true", true);
		Shot shot = new Shot();
		if(shot.getImage() != null)
			assertTrue("This is true", true);
	}
	
	public void testInit(){
		//test that the Aliens alien array is not empty
		//spaceinvaders.Board.
		assertTrue(board.getAliens().size() !=0);
	}
	
	public void testGameOver(){
		//test that Game Over displays correctly
		
	}
}

