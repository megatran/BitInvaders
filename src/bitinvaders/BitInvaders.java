package bitinvaders;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class BitInvaders extends JFrame implements Commons {
	Board board;
	
	public BitInvaders()
    {
		board = new Board();
		//setLayout(new GridLayout(0,1,0,3));
        add(board);
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(createBottomPanel(), BorderLayout.SOUTH);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGTH));
        pack();
        //setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

	private JPanel createBottomPanel() {
		JPanel panelBottom = new JPanel();
		JTextField userInput = new JTextField(5);
		JLabel scoreLabel = new JLabel("Score");
		JLabel lifeLabel = new JLabel("Life");
		JTextField scoreOutput = new JTextField(5);
		JTextField lifeOutput = new JTextField(5);
		lifeOutput.setEditable(false);
		scoreOutput.setEditable(false);
		panelBottom.setSize(BOARD_WIDTH, 10);
		panelBottom.add(scoreLabel);
		panelBottom.add(scoreOutput);
		panelBottom.add(userInput);
		panelBottom.add(lifeLabel);
		panelBottom.add(lifeOutput);
		
		//userInput textfield only accept numeric keypress by user DocumentFilter to check!
		((AbstractDocument)userInput.getDocument()).setDocumentFilter(new DigitDocumentFilter());
		//the user input will accept numeric input from the user when he/she presses 'Enter'
		userInput.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("Enter Pressed: "+userInput.getText());
				board.checkInput(Integer.parseInt(userInput.getText()));
				userInput.setText("");
			}
		});
		
		return panelBottom;
	}
	
    public static void main(String[] args) {
    	
        new BitInvaders();
    }

}
