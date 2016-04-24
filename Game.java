package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
	
	protected ActionController ac;

	public void run() throws FileNotFoundException{ //A játékot készíti elő. Létrehozza az ActionControllert.
	
		ac=new ActionController();

		int column=0;
		int row=0;
		
		Scanner scanner = new Scanner(new File("level2.csv"));
	    scanner.useDelimiter(",");
	    System.out.print(" ");  
	    
	    ac.rows=scanner.nextInt();
	    ac.columns=scanner.nextInt();
	    scanner.next();

		ac.visitables​=new Tile[ac.rows][ac.columns];

	    while(scanner.hasNext()){
	       String temp=scanner.next();
	       
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
		       			for(int i = 0; i<=row; i++)
		       			{
		       				if(i!=row){
			       			    for(int j = 0; j<ac.columns; j++)
			       			    {
			       			    	if(ac.visitables​[i][j].getClass().getSimpleName().equals("Scale")&&((Scale) ac.visitables​[i][j]).getID()==tempDoor.doorID){
			       			    		((Scale) ac.visitables​[i][j]).setDoor(row,column);
			       			    	}
			       			    		
			       			    }
		       			    }
		       				else{
		       				 for(int j = 0; j<column; j++)
			       			    {
			       			    	if(ac.visitables​[i][j].getClass().getSimpleName().equals("Scale")&&((Scale) ac.visitables​[i][j]).getID()==tempDoor.doorID){
			       			    		((Scale) ac.visitables​[i][j]).setDoor(row,column);
			       			    	}
			       			    		
			       			    }
		       				}
		       					
		       			}
		       			
		       			column++;
		       			break;
		       		case 'S':
		       			Scale tempScale=new Scale();
		       			tempScale.scaleID=Character.getNumericValue(temp.charAt(1));
		       			tempScale.setWeightLimit(Character.getNumericValue(temp.charAt(3)));
		       			ac.visitables​[row][column]=tempScale;
		       			for(int i = 0; i<=row; i++)
		       			{
		       				if(i!=row){
			       			    for(int j = 0; j<ac.columns; j++)
			       			    {
			       			    	if(ac.visitables​[i][j].getClass().getSimpleName().equals("Door")&&((Door) ac.visitables​[i][j]).getID()==tempScale.scaleID){
			       			    		((Scale) ac.visitables​[row][column]).setDoor(i,j);
			       			    	}
			       			    		
			       			    }
		       			    }
		       				else{
		       				 for(int j = 0; j<column; j++)
			       			    {
			       			    	if(ac.visitables​[i][j].getClass().getSimpleName().equals("Door")&&((Door) ac.visitables​[i][j]).getID()==tempScale.scaleID){
			       			    		((Scale) ac.visitables​[row][column]).setDoor(i,j);
			       			    	}
			       			    		
			       			    }
		       				}
		       					
		       			}
		       			column++;
		       			break;
		       }
	       }
	    }
	    scanner.close();
	    
	}
	
	public void play(){	//​Meghívásakor elindul a játék. Innentől kezdve az ActionController feladata a bemenetek kezelése.

	}
}
