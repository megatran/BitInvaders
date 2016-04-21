package InvaderTests;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.BeforeClass;
import org.junit.Test;

public class RunTests {
	
	@BeforeClass
	public static void setUp() {
		spaceinvaders.Board board = new spaceinvaders.Board();
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
	}
	
	public void testGameOver(){
		//test that Game Over displays correctly
		
	}
}

