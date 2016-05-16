package projlab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import projlab.Game.MKeyListener;

public class View extends JFrame{

	private JPanel map;
	
	public String menu() throws IOException{
		this.setVisible(true);	
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//vár 400ms-ot különben a rajzolás nem működik
		// TODO gyorsabb gépen ellenőrizni, hogy ott is fennáll-e a helyzet
		try{Thread.sleep(400);}
		catch(InterruptedException e){}
		JPanel menu = new JPanel();
		this.setSize(400, 400);
		menu.setSize(400, 400);
		this.add(menu);
		
		//a menüt felépítő képek
		Image background = ImageIO.read(new File("src/projlab/menu/menu_bg.png"));
		Image onePlayerBtn = ImageIO.read(new File("src/projlab/menu/menu_1player.png"));
		Image twoPlayerBtn = ImageIO.read(new File("src/projlab/menu/menu_2players.png"));
		Image customBtn = ImageIO.read(new File("src/projlab/menu/menu_custom.png"));
		
		menu.getGraphics().drawImage(background, 0, 0, null);
		menu.getGraphics().drawImage(onePlayerBtn, 97, 54, null);
		menu.getGraphics().drawImage(twoPlayerBtn, 97, 154, null);
		menu.getGraphics().drawImage(customBtn, 97, 254, null);
		
		//a kattintást figyelő listener
		CustomMouseListener listener = new CustomMouseListener();
		menu.addMouseListener(listener);
		boolean waiting = true;
		String fileName = null;
		
		//addig fut a ciklus, amíg nem kaptunk érvényes választ a játékostól
		while(waiting){

			switch(listener.clickedButton){
			//ha az "1 player" gombot nyomtuk meg
			case 1:
				// TODO egyjátékos pálya
				fileName = "level1.csv";
				waiting = false;
				break;
			//ha a "2 players" gombot nyomtuk meg
			case 2:
				// TODO kétjátékos pálya
				fileName = "level2.csv";
				waiting = false;
				break;
			//ha a "custom" gombot nyomtuk meg
			case 3:
				//létrehozunk egy fájlválasztó ablakot
				JFileChooser fileChooser = new JFileChooser();
				//csak a .csv fájlokat mutatjuk
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					fileName = fileChooser.getSelectedFile().getPath();
					waiting = false;
				}
				//ha a játékos nem választott fájlt, tovább várunk
				else listener.clickedButton = 0;
			}
		}
		return fileName;
	}
	
	
	public void setMap(int columns, int rows){
		map = new JPanel();
		this.setSize(40*(columns+2), 40*(rows+2));
		map.setSize(40*(columns+1), 40*(rows+1));
		this.add(map);
	}
	
	public void addMyKeyListener(MKeyListener kl){
		this.addKeyListener(kl);
	}
	
	public void drawTile(int y, int x, Image img)
	{		
		map.getGraphics()
			.drawImage(img, x*40, y*40, null);  
	}
}

//egérlttuntást figyelő osztáy
class CustomMouseListener implements MouseListener{

	public int clickedButton;
	CustomMouseListener(){
		clickedButton = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		//1 player gomb
		if (e.getX() > 97 && e.getX() < 303 &&
			e.getY() > 54 && e.getY() < 126)
				clickedButton = 1;
		//2 players gomb
		else if (e.getX() >  97 && e.getX() < 303 &&
				 e.getY() > 154 && e.getY() < 226)
					clickedButton = 2;
		//custom gomb
		else if (e.getX() >  97 && e.getX() < 303 &&
				 e.getY() > 254 && e.getY() < 326)
					clickedButton = 3;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}

