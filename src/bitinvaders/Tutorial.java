package bitinvaders;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tutorial extends JDialog {
	private JLabel imglabel;
	private JTextArea textarea;
	private JTextField in;
	String[] text= new String[10];{
	text[0] = "Binary numbers are like decimal numbers, but base 2 is used instead of base 10.";
	text[1] = "This means the instead of the ones digit going to 0 after 9, it returns to 0 after 1.";
	text[2] = "Thus, the decimal number 2 is expressed as 10. There is 1 in the twos place, and 0 in the ones place, making the total 2.";
	text[3] = "Therefore, 11 in binary is 3 in decimal, 100 in binary is 4 in decimal, and so on. ";
	text[4] = "As with decimal numbers, 0s at the beginning of a number do not change the value of the number. 011 is equal to 11.";
	text[5] = "What is 0101 in decimal?";
	text[6] = "What is 1010 in decimal?";
	text[7] = "What is 0011 in decimal?";
	text[8] = "What is 0011 + 0011 in decimal?";
	text[9] = "You are now ready to play! ";}
	private int i = 0;
	
	public Tutorial(){
		setVisible(true);
		setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		setTitle("Tutorial");
		setLayout(new GridLayout(2,0));
		imglabel = new JLabel("");
		imglabel.setIcon(new ImageIcon(BitInvaders.class.getResource("/spacepix/TutorialImageSmall.png"))); 
		add(imglabel);
		JPanel io = new JPanel();
		io.setLayout(new GridLayout(2,0));
		textarea = new JTextArea(text[i]);
		textarea.setWrapStyleWord(true);
		textarea.setLineWrap(true);
		textarea.setBorder(new EmptyBorder(10,10,10,10));
		textarea.setEditable(false);
		io.add(textarea);
		JPanel ioo = new JPanel();
		in = new JTextField(5);
		in.setVisible(false);
		ioo.add(in);
		JButton next = new JButton("OK");
		JButton close = new JButton("Close");
		ioo.add(next);
		ioo.add(close);
		io.add(ioo);
		add(io);

		class ButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				updateText();
			}
		}
		next.addActionListener(new ButtonListener());
		class CloseListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}
		close.addActionListener(new CloseListener());
	}
	private void updateText(){
		String[] answers = {"","","","","","5","10","3","6"};
		if (i>4 && i<9){
			if(!in.getText().equals(answers[i])){
				textarea.setText("That is incorrect. Remember that the first digit is the eights place, the second represents fours, the third twos, and the last ones. " + text[i]);
				in.setText("");
			}
			else{
				i++;
				in.setText("");
				textarea.setText(text[i]);
				if (i==9){
					in.setVisible(false);
					imglabel.setIcon(new ImageIcon(BitInvaders.class.getResource("/spacepix/alientut.png")));
					
				}
			}
		}
		else if (i==9){
			setVisible(false);
		}
		else if (i==4){
			in.setVisible(true);
			i++;
			textarea.setText(text[i]);
		}
		else{
			i++;
			textarea.setText(text[i]);
		}
		
	}
	//uncomment to run independently
	/*public static void main(String[] args) {
		JFrame d =new JFrame();
		d.add(new Tutorial());
		d.setSize(300,500);
		d.setVisible(true);
        
        
    }*/
}
