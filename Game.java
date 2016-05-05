package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Game {
	
	protected ActionController ac;
	protected View view;
	int toFile=0;

	public void run() throws FileNotFoundException{ //A játékot készíti elő. Létrehozza az ActionControllert.
	
		ac=new ActionController();
		view=new View();
		String filename;
		int file=0;
		int column=0;
		int row=0;
		
		view.menu();
		
	    Scanner scanFileName = new Scanner(System.in);
	    file=scanFileName.nextInt();
	    switch(file){
	    case 1:
	    	filename="test1.csv";
	    	break;
	    case 2:
	    	filename="test2.csv";
	    	break;
	    case 3:
	    	filename="test3.csv";
	    	break;
	    case 4:
	    	filename="test4.csv";
	    	break;
	    case 5:
	    	filename="test5.csv";
	    	break;
	    case 6:
	    	filename="test6.csv";
	    	break;
	    case 7:
	    	filename="test7.csv";
	    	break;
	    default:
	    	filename="level3.csv";
	    }
		Scanner scanner = new Scanner(new File(filename)); //Itt nyitjuk meg a beolvasandó pályát
	    scanner.useDelimiter(",");
	    System.out.print(" ");  
	    
	    ac.rows=scanner.nextInt();
	    ac.columns=scanner.nextInt();
	    scanner.next();

		ac.visitables​=new Tile[ac.rows][ac.columns]; //Létrehozunk egy megfelelő méretű pályát
		ac.starGates​=new StarGate[4]; //Létrehozzuk a csillagkapukat eltároló tömböt
		for(int i=0;i<4;i++)
			ac.starGates​[i]=null;
		ac.beams=new PortalBeam[4]; //Létrehozzuk a lövedékeket eltároló tömböt
		for(int i=0;i<4;i++)
			ac.beams[i]=null;
				
	    while(scanner.hasNext()){
	       String temp=scanner.next();
	       int tempArray[] ={row,column};
	       if(temp.contains(System.getProperty("line.separator"))){ 
	    	   /*sortörés esetén a pályán is új sort kezdünk*/
	    	   row++;
	    	   column=0;
	       }
	       else{
	    	   /*egyébként pedig a beolvasott betű alapján hozzuk létre a megfelelő típusú mezőt*/
		       switch(temp.charAt(0)){
		       		case 'P':
		       			ac.visitables​[row][column]=new StarGate();
		       		 switch(temp.charAt(1)){
		       		 case 'b':
		       			((StarGate) ac.visitables​[row][column]).setColor("blue");
		       			ac.starGates​[0]=ac.visitables​[row][column]; //a 0 helyen tároljuk a kék portált
		       			break;
		       		 case 'r':
		       			((StarGate) ac.visitables​[row][column]).setColor("red");
		       			ac.starGates​[1]=ac.visitables​[row][column]; //a 1 helyen tároljuk a piros portált
		       			break;
		       		 case 'g':
		       			((StarGate) ac.visitables​[row][column]).setColor("green");
		       			ac.starGates​[2]=ac.visitables​[row][column]; //a 2 helyen tároljuk a zöld portált
		       			break;
		       		 case 'y':
		       			((StarGate) ac.visitables​[row][column]).setColor("yellow");
		       			ac.starGates​[3]=ac.visitables​[row][column]; //a 3 helyen tároljuk a sárga portált
		       			break;
		       			}
	       			break;
		       		case 'W':
		       			if(temp.length()==1){
			       			ac.visitables​[row][column]=new Wall();
			       			ac.visitables​[row][column].coordinates=tempArray;
			       			}
		       			else{
		       				ac.visitables​[row][column]=new SpecialWall();
			       			ac.visitables​[row][column].coordinates=tempArray;
			       			}
		       			break;
		       		case 'C':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'H':
		       			ac.visitables​[row][column]=new Hole();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'R':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.replicator​=new Replicator();
		       			ac.replicator​.coordinates=tempArray;
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.replicatorIsAlive=true;
		       			break;
		       		case 'Z':
		       			ac.visitables​[row][column]=new CleanTile();
		       			((CleanTile) ac.visitables​[row][column]).changeZPM();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'B':
		       			ac.visitables​[row][column]=new BoxedTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'O':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.players[0]=new Player();
		       			ac.players[0].coordinates=tempArray;
		       			break;
		       		case 'J':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.players[1]=new Player();
		       			ac.players[1].coordinates=tempArray;
		       			break;
		       		case 'D':
		       			Door tempDoor=new Door();
		       			tempDoor.doorID=Character.getNumericValue(temp.charAt(1));
		       			ac.visitables​[row][column]=tempDoor;
		       			ac.visitables​[row][column].coordinates=tempArray;
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
		       			break;
		       }
		       ac.visitables​[row][column].coordinates=new int[2];
		       ac.visitables​[row][column].coordinates[0]=row;
		       ac.visitables​[row][column].coordinates[1]=column;
      		   column++;

	       }
	    }
	    scanner.close();
	    
	}
	
	public void play() throws FileNotFoundException, UnsupportedEncodingException{	//​Meghívásakor elindul a játék. Innentől kezdve az ActionController feladata a bemenetek kezelése.
	    Scanner scanner = new Scanner(System.in);
	    String temp=null;
	    System.out.print("Add meg, hogy consol-ra vagy fájlba szeretnél írni (0:consol, 1:fájl): ");
	    Scanner scanWhere = new Scanner(System.in);
	    toFile=scanWhere.nextInt();
		do{
		    System.out.println("\n");
		    if(toFile==1)
		    	ac.writeMap();
		    else
		    	ac.getMap();
			System.out.print("\nAdj meg egy parancsot: ");
			temp=scanner.next();
			
			switch(temp){
			/*O'Neill ezredes mozgatása*/
			case "4": //balra
				if(ac.players[0]!=null)
					ac.move(ac.players[0],0);
				break;
			case "8": //fel
				if(ac.players[0]!=null)
					ac.move(ac.players[0],1);
				break;
			case "6": //jobbra
				if(ac.players[0]!=null)
					ac.move(ac.players[0],2);
				break;
			case "2": //le
				if(ac.players[0]!=null)
					ac.move(ac.players[0],3);
				break;
			/*Ezredes dobozfelvétele*/
			case "5":
				if(ac.players[0]!=null)
				ac.boxing(ac.players[0]);
				break;
			/*Ezredes lövés*/
			case "+r":
				if(ac.players[0]!=null)
				ac.shoot(ac.players[0], "red");
				break;
			case "+b":
				if(ac.players[0]!=null)
				ac.shoot(ac.players[0], "blue");
				break;
			/*Jaffa mozgatása*/
			case "a": //balra
				if(ac.players[1]!=null)
					ac.move(ac.players[1],0);
				break;
			case "w": //fel
				if(ac.players[1]!=null)
					ac.move(ac.players[1],1);
				break;
			case "d": //jobbra
				if(ac.players[1]!=null)
					ac.move(ac.players[1],2);
				break;
			case "s": //le
				if(ac.players[1]!=null)
					ac.move(ac.players[1],3);
				break;
			/*Jaffa dobozfelvétele*/
			case "e":
				if(ac.players[1]!=null)
				ac.boxing(ac.players[0]);
				break;
			/*Jaffa lövés*/
			case "+g":
				if(ac.players[1]!=null)
				ac.shoot(ac.players[1], "gree");
				break;
			case "+y":
				if(ac.players[1]!=null)
				ac.shoot(ac.players[1], "yellow");
			}
		}while(!(temp.equals("exit")));
	}
}
