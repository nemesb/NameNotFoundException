package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{	   
	    System.out.print("Add meg a bet�ltend� map sz�m�t: ");
	    
	    Game game = new Game();
	    game.run();
	    game.play();
	}
}
