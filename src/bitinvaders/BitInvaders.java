package bitinvaders;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class BitInvaders extends JFrame implements Commons {
	protected static Board board;
	private static JTextField lifeDisplay;
	private static boolean hasShownDialogue = false;
	private static boolean gameOver = false;
	private JTextField input;
	private JTextField scoreOutput;
	private static boolean resetBoard = false;

	public BitInvaders(){
		board = new Board();
        add(board);
        setTitle("Bit Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(createBottomPanel(), BorderLayout.SOUTH);
        add(makeScore(), BorderLayout.NORTH);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        pack();
        setVisible(true);
        input.requestFocus();
        setResizable(false);
        
        
        JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		JMenu file = new JMenu("File");
		bar.add(file);
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		class ExitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		exit.addActionListener(new ExitListener());
		
    }

	private JPanel createBottomPanel() {
		JPanel panelBottom = new JPanel();
		JTextField userInput = new JTextField(3);
		input = userInput;
		JLabel lifeLabel = new JLabel("Life");
		JButton pause = new JButton("Pause");
		JButton tutorial = new JButton("?");
		lifeDisplay = new JTextField(2);
		lifeDisplay.setEditable(false);
		lifeDisplay.setText(Integer.toString(board.player.getNumLives()));
		panelBottom.setSize(BOARD_WIDTH, 10);
		panelBottom.add(lifeLabel);
		panelBottom.add(lifeDisplay);
		panelBottom.add(userInput);
		panelBottom.add(pause);
		panelBottom.add(tutorial);
		
		class PauseListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				if (pause.getText().equals("Pause")){
					board.pause();
					pause.setText("Continue");
				}
				else {
					pause.setText("Pause");
					board.unpause();
				}	
			}
		}
		pause.addActionListener(new PauseListener());
		class TutorialListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				board.pause();
				pause.setText("Continue");
				new Tutorial();
			}
		}
		tutorial.addActionListener(new TutorialListener());
		//userInput textfield only accept numeric keypress by user DocumentFilter to check!
		((AbstractDocument)userInput.getDocument()).setDocumentFilter(new DigitDocumentFilter());
		//the user input will accept numeric input from the user when he/she presses 'Enter'
		userInput.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Enter Pressed: "+userInput.getText());
				board.checkInput(Integer.parseInt(userInput.getText()));
				scoreOutput.setText(Integer.toString(board.player.getScore()));
				userInput.setText("");
			}
		});
		
		return panelBottom;
	}
	private JPanel makeScore(){
		JPanel scorePanel = new JPanel();
		JLabel scoreLabel = new JLabel("Score");
		scorePanel.add(scoreLabel);
		JTextField scoreOutput = new JTextField(5);
		this.scoreOutput=scoreOutput;
		scoreOutput.setText(Integer.toString(0));
		scoreOutput.setEditable(false);
		scorePanel.add(scoreOutput);
		return scorePanel;
	}
	public static void displayLife(int life){
		lifeDisplay.setText(Integer.toString(life));
	}
	
	
	
	public Board getBoard() {
		return board;
	}
	
	

	
	public void setBoard(Board b){
		board=b;
	}
	
	public static void setHasShownDialogueTrue() {
		hasShownDialogue = true;
	}
	
    public static boolean isGameOver() {
		return gameOver;
	}

	public static void main(String[] args) {
		BitInvaders game = new BitInvaders();
		JOptionPane.showMessageDialog(game, "Convert the binary BitAliens to decimal to attack them.", "BitInvaders", JOptionPane.INFORMATION_MESSAGE);
        
    }

	public static void setGameOverTrue() {
		gameOver = true;
		
	}


	public static void disposeJFrame(Frame[] frames) {
		for (Frame frames1: frames){
		    frames1.setVisible(false);
		    frames1.dispose();
			}
	}
	
	

	public static void setGameOver(boolean b) {
		gameOver = b;
		
	}

}
