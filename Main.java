package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{	   
	    System.out.print("Ez a pályád:");
	    
	    Game game = new Game();
	    game.run();

	    Scanner scanner = new Scanner(System.in);
	    
		do{
		    System.out.println("\n");
			game.ac.getMap();
			System.out.print("\nAdj meg egy parancsot: ");
		}while(!scanner.next().equals("exit"));
	}
}
