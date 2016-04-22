package spaceinvaders;

import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements Commons {
	Board board;
	
	public SpaceInvaders()
    {
		board = new Board();
        add(board);
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
    	
        new SpaceInvaders();
    }

}
