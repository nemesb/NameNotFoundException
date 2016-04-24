package projlab;

import java.util.Arrays;

public class ActionController {
	
	protected Player players[]=new Player[2]; 	//A két játékos tárolására szolgáló tömb.
	protected Tile[][] visitables​;	 //A pályán lévő elemeket tároló tömb.
	protected Visitor replicator​; 	//A replikátor helyét jegyzi.
	int countZPMs; 	//​A pályán lévő ZPM­ek számát jegyzi.
	Visitable additionalStoredVisitable​;	 //A funkciók végrehajtását megkönnyítő plusz attribútum.
	Tile[] starGates​;	//Azon mezők jegyzésére szolgál, ahol portált nyitottak a játékosok.
	Boolean replicatorIsAlive​; 	//A replikátor létezéséről vagy nem létezéséről kapunk információt a segítségével.*/
	int rows=0;
	int columns=0;
	
	public void move(Player player, int direction){ //mozgatja az adott visitor-t
		
		/* 0:balra 1:fel 2:jobbra 3:le */
		
		  int[] t= new int[2];
		  
		  switch(direction){
		  case 0:
		   t[0]=player.coordinates[0];
		   t[1]=player.coordinates[1]-1;
		   break;
		  case 1:
		   t[0]=player.coordinates[0]-1;
		   t[1]=player.coordinates[1];
		   break;
		  case 2:
		   t[0]=player.coordinates[0];
		   t[1]=player.coordinates[1]+1;
		   break;
		  case 3:
		   t[0]=player.coordinates[0]+1;
		   t[1]=player.coordinates[1];
		   break;
		  }
		  
		  if(!(visitables​[t[0]][t[1]].getClass().getSimpleName().equals("Wall"))
				  &&(!visitables​[t[0]][t[1]].getClass().getSimpleName().equals("Door")
						  || ((Door) visitables​[t[0]][t[1]]).isPassable())){
			  
			 if(visitables​[player.coordinates[0]][player.coordinates[1]].getClass().getSimpleName().equals("Scale")){
				 ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(-4);
				 if (((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeight()<((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeightLimit()){
					  int[] tempDoor=((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor();
					  ((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
				  }
			 }
				 player.coordinates=t;
		  }
		  
		  if(visitables​[t[0]][t[1]].getClass().getSimpleName().equals("Scale")){
			  ((Scale) visitables​[t[0]][t[1]]).setWeight(4);
			  if (((Scale) visitables​[t[0]][t[1]]).getWeight()>=((Scale) visitables​[t[0]][t[1]]).getWeightLimit()){
				  int[] tempDoor=((Scale) visitables​[t[0]][t[1]]).getDoor();
				  ((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
			  }
		  }
	}
	
	public static Visitable getNextVisitable(Visitable currentTile,int direction){ //megadja, hogy a meghatározott irányban mi a következő mező

		return null;
	}

	public static void changeVisitable(Visitable changingVisitable, Visitable newVisitable){ //megváltoztatja az adott visitable-t
		
	}
	
	public static void changeGates(Visitable oldGates[], Visitable newGates[]){ //megváltoztatja az aktuális kapukat

	}
	
	public static void shoot(Visitor visitor,String color){ //az adott visitor adott színű lövedéket lő ki


	}
	
	public void getMap(){

		for(int i = 0; i<rows; i++)
		{
		    for(int j = 0; j<columns; j++)
		    {
		    	switch(visitables​[i][j].getClass().getSimpleName()){
		    	case "Wall":
		    		System.out.print("W");
		    		if(j!=columns-1)
			    		System.out.print(",");
		    		break;
		    	case "BoxedTile":
		    		if(players[0]!=null&&i==players[0].getRow()&&j==players[0].getColumn())
			    		System.out.print("O,");
		    		else if(players[1]!=null&&i==players[1].getRow()&&j==players[1].getColumn())
			    		System.out.print("J,");
		    		else
		    		System.out.print("B,");
		    		break;
		    	case "CleanTile":
		    		if(players[0]!=null&&i==players[0].getRow()&&j==players[0].getColumn())
			    		System.out.print("O,");
		    		else if(players[1]!=null&&i==players[1].getRow()&&j==players[1].getColumn())
			    		System.out.print("J,");
		    		else
		    		System.out.print("C,");
		    		break;
		    	case "Scale":
		    		if(players[0]!=null&&i==players[0].getRow()&&j==players[0].getColumn())
			    		System.out.print("O,");
		    		else if(players[1]!=null&&i==players[1].getRow()&&j==players[1].getColumn())
			    		System.out.print("J,");
		    		else
		    		System.out.print("S"+((Scale) visitables​[i][j]).getID()+"."+((Scale) visitables​[i][j]).getWeightLimit()+",");
		    		break;
		    	case "Door":
		    		if(players[0]!=null&&i==players[0].getRow()&&j==players[0].getColumn())
			    		System.out.print("O,");
		    		else if(players[1]!=null&&i==players[1].getRow()&&j==players[1].getColumn())
			    		System.out.print("J,");
		    		else
		    		System.out.print("D"+((Door) visitables​[i][j]).getID()+",");
		    		break;
		    	default:
		    		System.out.print(visitables​[i][j].getClass().getSimpleName()+",");
		    		}
		    }
		    System.out.println();
		}	
	}

}
