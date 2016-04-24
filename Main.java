package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{	   
	    System.out.print("Ez a pályád:");
	    
	    Game game = new Game();
	    game.run();

	    Scanner scanner = new Scanner(System.in);
	    String temp=null;
	    
		do{
		    System.out.println("\n");
			game.ac.getMap();
			System.out.print("\nAdj meg egy parancsot: ");
			temp=scanner.next();
			switch(temp){
			case "left":
				game.ac.move(game.ac.players[0],0);
				break;
			case "up":
				game.ac.move(game.ac.players[0],1);
				break;
			case "right":
				game.ac.move(game.ac.players[0],2);
				break;
			case "down":
				game.ac.move(game.ac.players[0],3);
				break;
			}
		}while(!(temp.equals("exit")));
	}
}
