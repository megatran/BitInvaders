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
			try {
				//ImageIcon ii = new ImageIcon(this.getClass().getResource("../spacepix/alien.png"));
			} catch (Exception NoImage) {

			} 
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

