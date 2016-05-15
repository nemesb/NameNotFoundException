package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerView {
	View view;
	public PlayerView(View v){
		view = v;
	}
	public void drawPlayer(int x, int y, boolean o) throws IOException{
		Image img;
		
		if(o)
			img = ImageIO.read(new File("src/projlab/map/Oneill_left.png"));	
		else
			img = ImageIO.read(new File("src/projlab/map/jaffa_left.png"));
		
		view.drawTile(x, y, img);
	}
}
