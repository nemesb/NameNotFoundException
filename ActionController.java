package projlab;

import java.util.Arrays;
import java.util.Random;

public class ActionController {

	protected Player players[] = new Player[2]; // A két játékos tárolására
												// szolgáló tömb.
	protected Tile[][] visitables​; // A pályán lévő elemeket tároló tömb.
	protected Visitor replicator​; // A replikátor helyét jegyzi.
	PortalBeam[] beams;
	int countZPMs; // ​A pályán lévő ZPM­ek számát jegyzi.
	Visitable additionalStoredVisitable​; // A funkciók végrehajtását
											// megkönnyítő plusz attribútum.
	Tile[] starGates​; // Azon mezők jegyzésére szolgál, ahol portált nyitottak
						// a játékosok.
	Boolean replicatorIsAlive​; // A replikátor létezéséről vagy nem létezéséről
								// kapunk információt a segítségével.*/
	int rows = 0;
	int columns = 0;

	public void move(Player player, int direction) { // mozgatja az adott
														// visitor-t

		/* 0:balra 1:fel 2:jobbra 3:le */

		int[] t = new int[2];
		if (player.getDirection() != direction)
			player.setDirection(direction);
		else {
			t=getNextVisitable(player.coordinates, direction).coordinates;
		}
			
		if (!(visitables​[t[0]][t[1]].getClass().getSimpleName().equals("Wall"))
				&&!(visitables​[t[0]][t[1]].getClass().getSimpleName().equals("SpecialWall"))
				&& (!visitables​[t[0]][t[1]].getClass().getSimpleName().equals("Door")
						|| ((Door) visitables​[t[0]][t[1]]).isPassable())) {

				if (visitables​[player.coordinates[0]][player.coordinates[1]].getClass().getSimpleName().equals("Scale")) {
					((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(-4);
					if (((Scale) visitables​[player.coordinates[0]][player.coordinates[1]])
							.getWeight() < ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]])
									.getWeightLimit()) {
						int[] tempDoor = ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor();
						((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
					}
				}
				if (visitables​[t[0]][t[1]].getClass().getSimpleName().equals("CleanTile")
						&&((CleanTile)visitables​[t[0]][t[1]]).getZPM()){
					
					((CleanTile)visitables​[t[0]][t[1]]).changeZPM();
					player.addZPM();
					if(player==players[0])
						ZPMcreator(player);
				}
				 if (visitables​[t[0]][t[1]].getClass().getSimpleName().equals("StarGate")){
					switch( ((StarGate)visitables​[t[0]][t[1]]).getColor() ){
					case "blue":
						if(starGates​[1]!=null)
							t = starGates​[1].coordinates;
						break;
					case "red":
						if(starGates​[0]!=null)
							t = starGates​[0].coordinates;
						break;	
					case "green":
						if(starGates​[3]!=null)
							t = starGates​[3].coordinates;
						break;
					case "yellow":
						if(starGates​[2]!=null)
							t = starGates​[2].coordinates;
						break;
					
				}
			}
		
				if (visitables​[t[0]][t[1]].getClass().getSimpleName().equals("Scale")) {
					((Scale) visitables​[t[0]][t[1]]).setWeight(4);
					if (((Scale) visitables​[t[0]][t[1]]).getWeight() >= ((Scale) visitables​[t[0]][t[1]]).getWeightLimit()) {
						int[] tempDoor = ((Scale) visitables​[t[0]][t[1]]).getDoor();
						((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
					}
				}
					
					player.coordinates = t;
			}
		
	}

	public void boxing(Player player) {
		if (!player.getBox()) {
			if (getNextVisitable(player.coordinates, player.getDirection()).getClass().getSimpleName()
					.equals("BoxedTile")) {
				changeVisitable(getNextVisitable(player.coordinates, player.getDirection()), new CleanTile());
			}
			if (getNextVisitable(player.coordinates, player.getDirection()).getClass().getSimpleName()
					.equals("Scale")) {
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).hasBox = false;
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).setWeight(-2);
				if (((Scale) getNextVisitable(player.coordinates, player.getDirection()))
						.getWeight() < (((Scale) getNextVisitable(player.coordinates, player.getDirection())))
								.getWeightLimit()) {
					int[] tempDoor = ((Scale) getNextVisitable(player.coordinates, player.getDirection())).getDoor();
					((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
				}
			}
			player.changeBox();
		} else {
			if (getNextVisitable(player.coordinates, player.getDirection()).getClass().getSimpleName()
					.equals("CleanTile")) {
				changeVisitable(getNextVisitable(player.coordinates, player.getDirection()), new BoxedTile());
			}
			if (getNextVisitable(player.coordinates, player.getDirection()).getClass().getSimpleName()
					.equals("Scale")) {
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).hasBox = true;
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).setWeight(2);
				if (((Scale) getNextVisitable(player.coordinates, player.getDirection())).getWeight() >= ((Scale) getNextVisitable(player.coordinates, player.getDirection())).getWeightLimit()) {
					int[] tempDoor = (((Scale) getNextVisitable(player.coordinates, player.getDirection())).getDoor());
					((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
				}
			}
			player.changeBox();
		}
	}

	public Tile getNextVisitable(int[] coordinates, int direction) { 
		/* megadja, hogy a meghatározott irányban mi akövetkező mező */
		int[] temp = coordinates;
		try {
			switch (direction) {
			case 0: // balra
				return visitables​[temp[0]][temp[1] - 1];
			case 1: // fel
				return visitables​[temp[0] - 1][temp[1]];
			case 2: // jobbra
				return visitables​[temp[0]][temp[1] + 1];
			case 3: // le
				return visitables​[temp[0] + 1][temp[1]];
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public void changeVisitable(Tile changingVisitable, Tile newVisitable) { // megváltoztatja
																				// az
																				// adott
																				// visitable-t
		int[] t = changingVisitable.coordinates;

		if (newVisitable.getClass().getSimpleName().equals("BoxedTile")) {
			visitables​[t[0]][t[1]] = new BoxedTile();
		} else if (newVisitable.getClass().getSimpleName().equals("CleanTile")) {
			visitables​[t[0]][t[1]] = new CleanTile();
		}

		visitables​[t[0]][t[1]].coordinates = t;

	}

	public static void changeGates(Visitable oldGates[], Visitable newGates[]) { // megváltoztatja
																					// az
																					// aktuális
																					// kapukat

	}

	public void shoot(Player visitor,String color){ //az adott visitor adott színű lövedéket lő ki
		 		boolean alive=true;
		  		PortalBeam beam= new PortalBeam();
		  		beam.changeColor(color);
		  		beam.coordinates=new int[2];
		  		beam.coordinates=visitor.coordinates;
		  		beam.setDirection(visitor.getDirection());
		  		switch(color){
		  		case "blue":
		  			beams[0]=beam;
		  			break;
		  		case "red":
		  			beams[1]=beam;
		  			break;	
		  		case "green":
		  			beams[2]=beam;
		  			break;
		  		case "yellow":
		  			beams[3]=beam;
		  			break;
		  		}		 		
		  	}

	private void move(PortalBeam beam) {
		beam.coordinates=getNextVisitable(beam.coordinates,beam.getDirection()).coordinates;
	}

	public void getMap() {
		
		for(int i=0;i<4;i++){ 
		if(beams[i]!=null&&beams[i].coordinates[0]<rows-1&&beams[i].coordinates[1]<columns-1)
			move(beams[i]); //mozgatja a pályán lévő lövedékeket
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				switch (visitables​[i][j].getClass().getSimpleName()) {
				case "SpecialWall":
					System.out.print("Ws");
					if (j != columns - 1)
						System.out.print(",");
					break;
				case "StarGate":
					if (players[0] != null && i == players[0].getRow() && j == players[0].getColumn())
						System.out.print("O");
					else if (players[1] != null && i == players[1].getRow() && j == players[1].getColumn())
						System.out.print("J");
					else{
					System.out.print("P");
					switch( ((StarGate)visitables​[i][j]).getColor() ){
					case "blue":
						System.out.print("b");
						break;
					case "red":
						System.out.print("r");
						break;	
					case "green":
						System.out.print("g");
						break;
					case "yellow":
						System.out.print("y");
						break;
						}
					}
					if (j != columns - 1)
						System.out.print(",");
					break;
				case "Wall":
					System.out.print("W");
					if (j != columns - 1)
						System.out.print(",");
					break;
				case "BoxedTile":
					if (players[0] != null && i == players[0].getRow() && j == players[0].getColumn())
						System.out.print("O,");
					else if (players[1] != null && i == players[1].getRow() && j == players[1].getColumn())
						System.out.print("J,");
					else
						System.out.print("B,");
					break;
				case "CleanTile":
					int b=0;
					Boolean hasBeam=false;

					while(b<4){
					if (beams[b]!= null&&i == beams[b].coordinates[0] && j == beams[b].coordinates[1] ){
							hasBeam=true;
						}
						b++;
					}
					if(hasBeam)
						System.out.print("*,");
					else if (players[0] != null && i == players[0].getRow() && j == players[0].getColumn())
						System.out.print("O,");
					else if (players[1] != null && i == players[1].getRow() && j == players[1].getColumn())
						System.out.print("J,");
					else if(((CleanTile) visitables​[i][j]).getZPM())
						System.out.print("Z,");
					else
						System.out.print("C,");
					break;
				case "Scale":
					if (players[0] != null && i == players[0].getRow() && j == players[0].getColumn())
						System.out.print("O,");
					else if (players[1] != null && i == players[1].getRow() && j == players[1].getColumn())
						System.out.print("J,");
					else if (((Scale) visitables​[i][j]).hasBox)
						System.out.print("B,");
					else
						System.out.print("S" + ((Scale) visitables​[i][j]).getID() + "."
								+ ((Scale) visitables​[i][j]).getWeightLimit() + ",");
					break;
				case "Door":
					if (players[0] != null && i == players[0].getRow() && j == players[0].getColumn())
						System.out.print("O,");
					else if (players[1] != null && i == players[1].getRow() && j == players[1].getColumn())
						System.out.print("J,");
					else
						System.out.print("D" + ((Door) visitables​[i][j]).getID() + ",");
					break;
				default:
					System.out.print(visitables​[i][j].getClass().getSimpleName() + ",");
				}
			}
			System.out.println();
		}
	}

	private void ZPMcreator(Player player){
		if(player.getZPMs()%2==0){
			int tries=0;
			Random rand = new Random();
			int i=rand.nextInt(rows-1) + 1;
			int j=rand.nextInt(columns-1) + 1;
			
			while(tries<(rows-1)*(columns-1)&&
					(!visitables​[i][j].getClass().getSimpleName().equals("CleanTile"))
					||(visitables​[i][j].getClass().getSimpleName().equals("CleanTile")
							&&((CleanTile) visitables​[i][j]).getZPM())){
				System.out.println(visitables​[i][j].getClass().getSimpleName());
				tries++;
				i=rand.nextInt(rows-1) + 1;
				j=rand.nextInt(columns-1) + 1;
			}
			
			((CleanTile) visitables​[i][j]).changeZPM();
		}
	}
}
