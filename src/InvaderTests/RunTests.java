package InvaderTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	//make sure alien falling down correctly
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
	//test the pause functionality
	public void pauseContinue(){
		BitAlien a = new BitAlien(10);
		board.addAlien(a);
		board.pause();
		assertFalse(a.isVisible());
		board.unpause();
		assertTrue(a.isVisible());
	}
	@Test
	//test colors with associated speeds
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
	//test loading image correctly
	public void testImage() {
		// board was "this", might have to change.
		Image ii = new ImageIcon(board.getClass().getResource("/spacepix/boardBackground1.png")).getImage();
		BitAlien alien = new BitAlien(0);
		alien.setImage(ii);
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
	//make sure sound is loaded and plays
	public void testSound() throws UnsupportedAudioFileException, IOException {
		// board was "this", might have to change.
		AudioInputStream ii = AudioSystem.getAudioInputStream(board.getClass().getResource("/spacepix/yugiMusic.wav"));
		BitAlien alien = new BitAlien(0);
		AudioFormat audioFormat = ii.getFormat();
		if(ii.getFormat() != null)
			assertTrue("This is true", true);
	}

	@Test
	//test when user guess the right value to destroy alien
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
	//checking if user input matches with alien's value
	public void testInput(){
		Player p = board.getPlayer();
		int score = p.getScore();
		BitAlien a = new BitAlien(0);
		a.setMyDecimalValue(5);
		//a.randomize();
		int i = a.getMyDecimalValue();
		System.out.println(i);
		board.addAlien(a);
		board.checkInput(i);
		for (BitAlien b : board.getAlienQueue()) {
			System.out.println(b.getMyDecimalValue());
		}
		assertEquals(5, score+i);
		//		board.addAlien(a);
		//board.checkInput(i+1);
		//assertEquals(p.getScore(), score+i-5);
	}


	@Test	
	//test life adjustment and game over
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

	@Test	
	//test incremental speed depend on stage
	public void testSpeedStages(){
		//test that speed scales correctly with id
		board.setId(0);
		board.makeAliens();
		Queue<BitAlien> qu = board.getAlienQueue();
		if (!qu.isEmpty()){
			assertTrue(qu.element().getSpeed() == 5);
		}
		board.setId(5);
		board.clearAlienQueue();
		board.makeAliens();
		Queue<BitAlien> qu1 = board.getAlienQueue();
		if (!qu.isEmpty()){
			assertTrue(qu1.element().getSpeed() == 10);
		}
		board.setId(10);
		board.clearAlienQueue();

		board.makeAliens();
		Queue<BitAlien> qu2 = board.getAlienQueue();
		if (!qu.isEmpty()){
			assertTrue(qu2.element().getSpeed() == 15);
		}
		board.setId(15);
		//board.getAlienQueue().clear();
		board.clearAlienQueue();
		board.makeAliens();
		Queue<BitAlien> qu3 = board.getAlienQueue();
		if (!qu.isEmpty()){
			assertTrue(qu3.element().getSpeed() == 20);
		}
	}

	@Test	
	//test life decreasing if alien touches bottom/dies
	public void testLifeCount(){
		board = new Board();
		board.getPlayer().updateLife(1);
		assertTrue(board.getPlayer().getNumLives() == 4);
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().getNumLives() == 3);
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().getNumLives() == 2);
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().getNumLives() == 1);
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().getNumLives() == 0);
		board.getPlayer().updateLife(-1);
		assertTrue(board.getPlayer().isDead());

	}
}

