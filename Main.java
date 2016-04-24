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
			/*O'Neill ezredes mozgatása*/
			case "left": //balra
				if(game.ac.players[0]!=null)
					game.ac.move(game.ac.players[0],0);
				break;
			case "up": //fel
				if(game.ac.players[0]!=null)
					game.ac.move(game.ac.players[0],1);
				break;
			case "right": //jobbra
				if(game.ac.players[0]!=null)
					game.ac.move(game.ac.players[0],2);
				break;
			case "down": //le
				if(game.ac.players[0]!=null)
					game.ac.move(game.ac.players[0],3);
				break;
				
			/*Jaffa mozgatása*/
			case "a": //balra
				if(game.ac.players[1]!=null)
					game.ac.move(game.ac.players[1],0);
				break;
			case "w": //fel
				if(game.ac.players[1]!=null)
					game.ac.move(game.ac.players[1],1);
				break;
			case "d": //jobbra
				if(game.ac.players[1]!=null)
					game.ac.move(game.ac.players[1],2);
				break;
			case "s": //le
				if(game.ac.players[1]!=null)
					game.ac.move(game.ac.players[1],3);
				break;
			}
		}while(!(temp.equals("exit")));
	}
}
