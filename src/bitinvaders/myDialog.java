package bitinvaders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class myDialog extends JDialog{
	private JTextField text = new JTextField(10);
	private JButton yes = new JButton("Yes");
	private JButton no = new JButton("No");
	
	public myDialog(JFrame frame, String title){
		super(frame, title, false);
		JPanel panel = new JPanel();
		panel.add(text);
		panel.add(yes);
		panel.add(no);
		add(panel);
		pack();
		setLocationRelativeTo(frame);
		/*addYesListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!this.isVisible())
					this.setVisible(true);
				
			}
		}
		);*/
		
	}
	public void addYesListener(ActionListener listener){
		yes.addActionListener(listener);
	}
}

//package bitinvaders;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//public class myDialog extends JDialog{
//	private JTextField text = new JTextField(10);
//	private JButton yes = new JButton("Yes");
//	private JButton no = new JButton("No");
//	
//	public myDialog(JFrame frame, String title){
//		super(frame, title, false);
//		JPanel panel = new JPanel();
//		panel.add(text);
//		panel.add(yes);
//		panel.add(no);
//		add(panel);
//		pack();
//		setLocationRelativeTo(frame);
//		addYesListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if(!myDialog.isVisible())
//					myDialog.setVisible(true);
//				
//			}
//		}
//		);
//		
//	}
//	public void addYesListener(ActionListener listener){
//		yes.addActionListener(listener);
//	}
//}

