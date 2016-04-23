package projlab;

public class Player implements Visitor{
	
	private String name; //​Játékos neve
	private int direction; //​A játékos irányát tárolja.
	private Boolean hasBox; //​Azt jegyzi, hogy van a felhasználónál doboz, vagy nincs.
	private Tile tile; //Azt a Tile­t tárolja, amelyiken a felhasználó karaktere éppen áll.
	private int collectedZPMs; //​Azért felel, hogy az összegyűjtött elemeket ZPMeket számontartsa.
	int coordinates[]=new int[2];
			
	public static Visitable getVisitable(){ //megadja, hogy mi a következő mező
		
		return null;
	}
	public void visit(Visitable visitable) { //meglátogatja az adott mezőt
		
		
	}
	
	public static Boolean getBox(){ //megmondja hány doboz van a játékosnál
		
		return null;
		
	}
	
	public void setVisitable(Visitable visitable){ //beállítja a következő mezőt
		
	}
	
	public static void changeBox(){ //megváltoztatja, hogy van-e épp doboz a játékosnál
		

	}
	
	public static void addZPM(){ //hozzáad egyet a játékosnál lévő ZPM-ekhez 
		/* Itt még új ZPM-et is kell csináltatni minden második ZPM felvételnél*/
	}
	
	public int getRow(){
		return coordinates[0];
	}
	
	public int getColumn(){
		return coordinates[1];
	}
	
}
