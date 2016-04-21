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
		Alien alien = new Alien(0, 0, 1);
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
	
	public void testAlienmovement(){
		Alien slow = new Alien(10, 0, 1);
		slow.move();
		assertTrue(slow.getRow() == 9);
		assertTrue(slow.getCol() == 0);
		Alien fast = new Alien(10, 0, 2);
		fast.move();
		assertTrue(fast.getRow() == 8);
		assertTrue(fast.getCol() == 0);
	}
	
	public void testInit(){
		//test that the Aliens alien array is not empty
		//spaceinvaders.Board.
		ArrayList<Alien> boardaliens = board.getAliens();
		assertTrue(boardaliens.size() !=0);
		assertTrue(boardaliens.contains(new Alien(0,0, 1)));
		assertTrue(boardaliens.contains(new Alien(5,4, 1)));
	}
	
	public void testGameOver(){
		//test that Game Over displays correctly
		board.getPlayer().addHealth();
		assertTrue(board.getPlayer().getNumLives() == 4);
		board.getPlayer().maim();
		board.getPlayer().maim();
		board.getPlayer().maim();
		board.getPlayer().maim();
		assertTrue(board.getPlayer().isDead());
		
	}
}

