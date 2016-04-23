package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
	
	private ActionController ac;

	public void run() throws FileNotFoundException{ //A játékot készíti elő. Létrehozza az ActionControllert.
	
		ac=new ActionController();

		int column=0;
		int row=0;
		
		Scanner scanner = new Scanner(new File("level1.csv"));
	    scanner.useDelimiter(",");
	    System.out.print(" ");  
	    
	    int rowCount=scanner.nextInt();
	    int columnCount=scanner.nextInt();
	    
		ac.visitables​=new Tile[rowCount+1][columnCount];

	    while(scanner.hasNext()){
	       String temp=scanner.next();
	       System.out.print(temp+" ");  
	       
	       if(temp.contains(System.getProperty("line.separator"))){
	    	   row++;
	    	   column=0;
	       }
	       else{
		       switch(temp.charAt(0)){
		       		case 'W':
		       			ac.visitables​[row][column]=new Wall();
		       			column++;
		       			break;
		       		case 'C':
		       			ac.visitables​[row][column]=new CleanTile();
		       			column++;
		       			break;
		       		case 'B':
		       			ac.visitables​[row][column]=new BoxedTile();
		       			column++;
		       			break;
		       		case 'O':
		       			ac.visitables​[row][column]=new CleanTile();
		       			int tempArray[] ={row,column};
		       			ac.players[0]=new Player();
		       			ac.players[0].coordinates=tempArray;
		       			column++;
		       			break;
		       		case 'J':
		       			ac.visitables​[row][column]=new CleanTile();
		       			int tempArray2[] ={row,column};
		       			ac.players[1]=new Player();
		       			ac.players[1].coordinates=tempArray2;
		       			column++;
		       			break;
		       		case 'D':
		       			Door tempDoor=new Door();
		       			tempDoor.doorID=Character.getNumericValue(temp.charAt(1));
		       			ac.visitables​[row][column]=tempDoor;
		       			column++;
		       			break;
		       		case 'S':
		       			Scale tempScale=new Scale();
		       			tempScale.scaleID=Character.getNumericValue(temp.charAt(1));
		       			tempScale.setWeightLimit(Character.getNumericValue(temp.charAt(3)));
		       			ac.visitables​[row][column]=tempScale;
		       			column++;
		       			break;
		       }
	       }
	    }
	    scanner.close();
	    
	    System.out.println("\n\n O'Neill helye: " + ac.players[0].coordinates[0]+" "+ ac.players[0].coordinates[1]);

	}
	
	public void play(){	//​Meghívásakor elindul a játék. Innentől kezdve az ActionController feladata a bemenetek kezelése.

	}
}
