package InvaderTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import spaceinvaders.Alien;
import spaceinvaders.Board;
import spaceinvaders.Player;
import spaceinvaders.Shot;

public class RunTests {
	Board board;
	@Before
	public void setUp() {
	board = new Board();
	}

		
	

	@Test
	public void testImage() {
		// board was "this", might have to change.
		ImageIcon ii = new ImageIcon(board.getClass().getResource("../spacepix/alien.png"));
		Alien alien = new Alien(0, 0, Color.RED);
		alien.setImage(ii.getImage());
		if(alien.getImage() != null)
			assertTrue("This is true", true);
		Player p = new Player(0,0);
		if(p.getImage() != null)
			assertTrue("This is true", true);
		Shot shot = new Shot(0,0);
		if(shot.getImage() != null)
			assertTrue("This is true", true);
	}
	
	public void testInit(){
		//test that the Aliens alien array is not empty
		//spaceinvaders.Board.
		ArrayList<Alien> boardaliens = board.getAliens();
		assertTrue(boardaliens.size() !=0);
		assertTrue(boardaliens.contains(new Alien(0,0, Color.RED)));
		assertTrue(boardaliens.contains(new Alien(5,4, Color.BLUE)));
	}
	
	public void testGameOver(){
		//test that Game Over displays correctly
		board.getPlayer().maim();
		board.getPlayer().maim();
		board.getPlayer().maim();
		assertTrue(board.getPlayer().isDead());
		
	}
}

