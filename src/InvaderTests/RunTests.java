package InvaderTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import bitinvaders.BitAlien;
import bitinvaders.BitInvaders;
import bitinvaders.Board;
import bitinvaders.Player;

public class RunTests {
	Board board;
	@Before
	public void setUp() {
	BitInvaders invaders = new BitInvaders();
	Board board = new Board();
	invaders.setBoard(new Board());
	this.board=invaders.getBoard();
	//invaders.setBoard(board);
	board.makeAliens();
	System.out.println(board.getAlienQueue().toString());
	}

@Test
	public void testAlienmovement(){
		BitAlien slow = new BitAlien(10);
		int y = slow.getY();
		slow.move();
		assertTrue(slow.getY() == y-10);
		assertTrue(slow.getCol() == 0);
		BitAlien fast = new BitAlien(2);
		fast.move();
		assertTrue(fast.getRow() == 8);
		assertTrue(fast.getCol() == 0);
	}
@Test	
	public void testInit(){
		//test that the Aliens alien array is not empty
		//spaceinvaders.Board.
		Queue<BitAlien> boardaliens = board.getAlienQueue();
		System.out.println(boardaliens);
		assertTrue(boardaliens.size() !=0);
		assertTrue(boardaliens.contains(new BitAlien(100)));
	}
	
	@Test
	public void testImage() {
		// board was "this", might have to change.
		ImageIcon ii = new ImageIcon(board.getClass().getResource("../spacepix/alien.png"));
		BitAlien alien = new BitAlien(0);
		alien.setImage(ii.getImage());
		if(alien.getImage() != null)
			assertTrue("This is true", true);
		Player p = new Player(0, 0);
		if(p.getImage() != null)
			assertTrue("This is true", true);
		//Shot shot = new Shot(0, 0);
		//if(shot.getImage() != null)
		//	assertTrue("This is true", true);
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
		//p.flushInput();
		p.updateInput();
		input = p.getInput();
		assertEquals(input, test2);

	}
	@Test
	public void testAlienDeath(){
		Player p = board.getPlayer();
		BitAlien a = new BitAlien(0);
		a.setMyBits("0101");
		board.addAlien(a);
		board.checkInput(5);
		assertTrue(a.isVisible());
		BitAlien b = new BitAlien(0);
		b.setMyBits("0101");
		board.addAlien(b);
		board.checkInput(7);
		assertFalse(a.isVisible());
	}
	
	@Test
	public void testInput(){
		Player p = board.getPlayer();
		int score = p.getScore();
		BitAlien a = new BitAlien(0);
		a.randomize();
		int i = a.getMyDecimalValue();
		board.addAlien(a);
		board.checkInput(i);
		assertEquals(p.getScore(), score+i);
		board.addAlien(a);
		board.checkInput(i+1);
		assertEquals(p.getScore(), score+i-5);
	}
	@Test	
	public void testGameOver(){
		//test that Game Over displays correctly
		board.getPlayer().updateLife(1);
		assertTrue(board.getPlayer().getNumLives() == 4);
		board.getPlayer().updateLife(-1);
		board.getPlayer().updateLife(-1);
		board.getPlayer().updateLife(-1);
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().isDead());
		
	}
}

