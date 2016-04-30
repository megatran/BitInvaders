package InvaderTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Queue;

import javax.swing.ImageIcon;

import org.junit.BeforeClass;
import org.junit.Test;

import bitinvaders.BitAlien;
import bitinvaders.BitInvaders;
import bitinvaders.Board;
import bitinvaders.Player;
import bitinvaders.SpecialBitAlien;

public class RunTests {
	static Board board;
	@BeforeClass
	public static void setUp() {
	BitInvaders invaders = new BitInvaders();
	board=invaders.getBoard();
	}

@Test
	public void testAlienmovement(){
		BitAlien slow = new BitAlien(10);
		int x = slow.getX();
		int y = slow.getY();
		slow.move();
		assertTrue(slow.getY() == y+10);
		assertTrue(slow.getX() == x);
		BitAlien fast = new BitAlien(20);
		x= fast.getX();
		y=fast.getY();
		fast.move();
		assertTrue(fast.getY() == y+20);
		assertTrue(fast.getX() == x);
	}
@Test
public void testAliencolor(){
	BitAlien a = new BitAlien(5);
	assertTrue(a.getColor().equals(Color.cyan));
	BitAlien b = new BitAlien(10);
	assertTrue(b.getColor().equals(Color.green));
	BitAlien c = new BitAlien(15);
	assertTrue(c.getColor().equals(Color.yellow));
	BitAlien d = new BitAlien(20);
	assertTrue(d.getColor().equals(Color.magenta));
	SpecialBitAlien e = new SpecialBitAlien(5);
	assertTrue(e.getColor().equals(Color.RED));
	
}
@Test	
	public void testInit(){
		//test that the Aliens alien array is not empty
		//spaceinvaders.Board.
		board.makeAliens();
		Queue<BitAlien> boardaliens = board.getAlienQueue();
		assertTrue(boardaliens.size() !=0);
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
	public void testAlienDeath(){
		BitAlien a = new BitAlien(0);
		a.randomize();
		int i = a.getMyDecimalValue();
		board.addAlien(a);
		board.checkInput(i+1);
		Queue<BitAlien> qu = board.getAlienQueue();
		assertTrue(qu.contains(a));
		for(int k=0; k<5; k++){
		board.checkInput(i);}
		qu = board.getAlienQueue();
		assertFalse(qu.contains(a));
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
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().isDead());
		
	}
}

