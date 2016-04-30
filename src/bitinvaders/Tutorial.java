package bitinvaders;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tutorial extends JPanel {
	
	private JTextArea textarea;
	private JTextField in;
	String[] text= new String[10];{
	text[0] = "Binary numbers are like decimal numbers, but base 2 is used instead of base 10.";
	text[1] = "This means the instead of the ones digit going to 0 after 9, it returns to 0 after 1.";
	text[2] = "Thus, the decimal number 2 is expressed as 10. There is 1 in the twos place, and 0 in the ones place, making the total 2.";
	text[3] = "Therefore, 11 in binary is 3 in decimal, 100 in binary is 4 in decimal, and so on. ";
	text[4] = "As with decimal numbers, 0s at the beginning of a number do not change the value of the number. 011 is equal to 11.";
	text[5] = "What is 0010 in decimal?";
	text[6] = "What is 0101 in decimal?";
	text[7] = "What is 1010 in decimal?";
	text[8] = "What is 1111 in decimal?";
	text[9] = "You are now ready to play!";}
	private int i = 0;
	
	public Tutorial(){
		setLayout(new GridLayout(2,0));
		JLabel imgLabel = new JLabel(new ImageIcon("images/TutorialImageSmall.png"));
		add(imgLabel);
		JPanel io = new JPanel();
		io.setLayout(new GridLayout(2,0));
		textarea = new JTextArea(text[i]);
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		io.add(textarea);
		JPanel ioo = new JPanel();
		in = new JTextField(5);
		in.setVisible(false);
		ioo.add(in);
		JButton next = new JButton("OK");
		ioo.add(next);
		io.add(ioo);
		add(io);

		class ButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				updateText();
			}
		}
		next.addActionListener(new ButtonListener());
	}
	private void updateText(){
		String[] answers = {"","","","","","2","5","10","15"};
		if (i>4 && i<9){
			if(!in.getText().equals(answers[i])){
				textarea.setText("That is incorrect. Remember that the first digit is the eights place, the second represents fours, the third twos, and the last ones. " + text[i]);
				in.setText("");
			}
			else{
				i++;
				in.setText("");
				textarea.setText(text[i]);
			}
		}
		else if (i==9)
			this.setVisible(false);
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
