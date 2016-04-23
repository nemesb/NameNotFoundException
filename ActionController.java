package projlab;

public class ActionController {
	
	protected int[][] players=new int[2][2]; 	//A két játékos tárolására szolgáló tömb.
	protected Tile[][] visitables​;	 //A pályán lévő elemeket tároló tömb.
	protected Visitor replicator​; 	//A replikátor helyét jegyzi.
	int countZPMs; 	//​A pályán lévő ZPM­ek számát jegyzi.
	Visitable additionalStoredVisitable​;	 //A funkciók végrehajtását megkönnyítő plusz attribútum.
	Visitable[] starGates​;	//Azon mezők jegyzésére szolgál, ahol portált nyitottak a játékosok.
	Boolean replicatorIsAlive​; 	//A replikátor létezéséről vagy nem létezéséről kapunk információt a segítségével.*/
	
	public static void move(Visitor player, int direction){ //mozgatja az adott visitor-t
		
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

}
