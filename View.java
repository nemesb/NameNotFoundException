package projlab;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{

	public void menu(){
		this.setVisible(true);

		JFrame menuFrame = new JFrame("Medzsik Snake - MENU");
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.validate();
		
		JPanel contentPaneMenu = new JPanel();
		contentPaneMenu.setBackground(Color.pink);
		
		JButton random=new JButton();
		random.setText("RANDOM");
		random.setBackground(Color.PINK);
		random.setPreferredSize(new Dimension(300, 50));

		
		contentPaneMenu.add(random);
		menuFrame.getContentPane().add(contentPaneMenu);

		menuFrame.setSize(400, 400);

	}
}
