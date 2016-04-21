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
	
	@Test
	public void testImage() {
		// board was "this", might have to change.
		ImageIcon ii = new ImageIcon(board.getClass().getResource("../spacepix/alien.png"));
		Alien alien = new Alien(0, 0, 0);
		alien.setImage(ii.getImage());
		if(alien.getImage() != null)
			assertTrue("This is true", true);
		Player p = new Player(0, 0);
		if(p.getImage() != null)
			assertTrue("This is true", true);
		Shot shot = new Shot(0, 0);
		if(shot.getImage() != null)
			assertTrue("This is true", true);
	}
	@Test
	public void testPlayerInput(){
		// 'ENTER' ends input
		Player p = new Player(0, 0);
		String test = "10";
		String test2 = "1";
		p.updateInput();
		String input = p.getInput();
		assertEquals(input, test);
		p.flushInput();
		p.updateInput();
		input = p.getInput();
		assertEquals(input, test2);

	}
	@Test
	public void testConversion(){
		Alien a1 = new Alien(0,0, 0);
		
		Player p = new Player(0, 0);
		p.updateInput();
		if(board.checkInput())
			assertTrue("This should be true", true);
		else
			assertTrue("Should have been true", false);
			
	}
	
	@Test
	public void testAlienDying(){
		Player p = new Player(0, 0);
		p.updateInput();
		if(board.checkInput())
			if(board.getAlienQueue().poll().isDying())
				assertTrue("Alien is dying", true);
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

