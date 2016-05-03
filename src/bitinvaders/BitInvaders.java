package bitinvaders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;


public class BitInvaders extends JFrame implements Commons {
	protected static Board board;
	private JPanel gameIntro;
	private JPanel bottomPanel;
	private JPanel scorePanel;
	private static JTextField lifeDisplay;
	private static boolean hasShownDialogue = false;
	private static boolean gameOver = false;
	private JTextField input;
	private JTextField scoreOutput;
	private JButton pause;
	private static boolean resetBoard = false;

	public BitInvaders(){
		//start sound
		GameMusic backgroundMusic = new GameMusic();
		backgroundMusic.play();
		board = new Board();
   //     setContentPane(new JLabel(new ImageIcon(BitInvaders.class.getResource("/spacepix/boardBackground1.png"))));
		//only show when player click Play to switch from intro panel to board panel
		board.setVisible(false);
		board.pause();
        setTitle("Bit Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        bottomPanel = createBottomPanel();
        scorePanel = makeScore();
        scorePanel.setOpaque(false);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(scorePanel, BorderLayout.NORTH);
        
        gameIntro = new JPanel();
        getContentPane().add(gameIntro, BorderLayout.CENTER);
        gameIntro.setLayout(null);
        gameIntro.setVisible(true);
        bottomPanel.setVisible(false);
        scorePanel.setVisible(false);
        
        BufferedImage playBttnIcon = null;
		try {
			playBttnIcon = ImageIO.read(BitInvaders.class.getResource("/spacepix/buttonPlaySmall.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can't find button image");
		}
        JButton btnPlay = new JButton(new ImageIcon(playBttnIcon));
        JButton tutBut = new JButton("Tutorial");
        btnPlay.setBorder(BorderFactory.createEmptyBorder());
        btnPlay.setContentAreaFilled(false);
        btnPlay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		gameIntro.setVisible(false);
                bottomPanel.setVisible(true);
                scorePanel.setVisible(true);
                getContentPane().add(board);
        		board.setVisible(true);
                input.requestFocus();
        		board.unpause();
        	}
        });
        tutBut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			new Tutorial();
		}
        });
        btnPlay.setBounds(119, 333, 109, 41);
        gameIntro.add(btnPlay);
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(BitInvaders.class.getResource("/spacepix/bitInvaders.jpg")));
        lblNewLabel.setBounds(-3, -32, 346, 550);
        gameIntro.add(lblNewLabel);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        pack();
        setVisible(true);
        setResizable(false);
        
        
        JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		JMenu file = new JMenu("File");
		bar.add(file);
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem tutorial = new JMenuItem("Tutorial");
		file.add(tutorial);
		file.add(exit);
		class ExitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		class TutorialListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				board.pause();
				pause.setText("Continue");
				new Tutorial();
			}
		}
		exit.addActionListener(new ExitListener());
		tutorial.addActionListener(new TutorialListener());
		
    }

	private JPanel createBottomPanel() {
		JPanel panelBottom = new JPanel();
		JTextField userInput = new JTextField(3);
		input = userInput;
		JLabel lifeLabel = new JLabel("Life");
		JButton pause = new JButton("Pause");
		this.pause=pause;
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
				if (!userInput.getText().isEmpty()){
					board.checkInput(Integer.parseInt(userInput.getText()));
				}
				scoreOutput.setText(Integer.toString(board.player.getScore()));
				userInput.setText("");
			}
		});
		
		return panelBottom;
	}
	private JPanel makeScore(){
		JPanel scorePan = new JPanel();
		scorePan.setBackground(new Color(0,0,0,65));
		JLabel scoreLabel = new JLabel("Score");
		scorePan.add(scoreLabel);
		JTextField scoreOutput = new JTextField(5);
		this.scoreOutput=scoreOutput;
		scoreOutput.setText(Integer.toString(0));
		scoreOutput.setEditable(false);
		scorePan.add(scoreOutput);
		return scorePan;
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
    
    public void learnToPlayPopup() {
    	   int confirm = JOptionPane.showConfirmDialog (this, "Do you want to see the tutorials first?", "Learn to Play?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	   
    	   if (confirm == JOptionPane.YES_OPTION) {
    	      Tutorial firstTut = new Tutorial();
    	      firstTut.setVisible(true);
    	   }
    }

	public static void main(String[] args) {
		BitInvaders game = new BitInvaders();

		 game.learnToPlayPopup();
		//JOptionPane.showMessageDialog(game, "Convert the binary BitAliens to decimal to attack them.", "BitInvaders", JOptionPane.INFORMATION_MESSAGE);
        
    }

	public static void setGameOverTrue() {
		gameOver = true;
		
	}


	public static void setGameOver(boolean b) {
		gameOver = b;
		
	}
}
