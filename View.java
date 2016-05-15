package projlab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{

	private JPanel map;
	
	public void menu(){
		this.setVisible(true);		
	}
	
	public void setMap(int columns, int rows){
		map = new JPanel();
		this.setSize(40*(columns+2), 40*(rows+2));
		map.setSize(40*(columns+1), 40*(rows+1));
		this.add(map);
	}
	
	public void drawTile(int y, int x, Image img)
	{		
		map.getGraphics()
			.drawImage(img, x*40, y*40, null);  
	}
}
