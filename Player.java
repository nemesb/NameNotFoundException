package projlab;

public class Player implements Visitor{
	
	private String name; //​Játékos neve
	private int direction=0; //​A játékos irányát tárolja.
	private Boolean hasBox=false; //​Azt jegyzi, hogy van a felhasználónál doboz, vagy nincs.
	private Tile tile; //Azt a Tile­t tárolja, amelyiken a felhasználó karaktere éppen áll.
	private int collectedZPMs=0; //​Azért felel, hogy az összegyűjtött elemeket ZPMeket számontartsa.
	int coordinates[]=new int[2];
			
	public static Visitable getVisitable(){ //megadja, hogy mi a következő mező
		
		return null;
	}
	public void visit(Visitable visitable) { //meglátogatja az adott mezőt
		
		
	}
	
	public Boolean getBox(){ //megmondja van-e doboz van a játékosnál
		
		return hasBox;
		
	}
	
	public void setVisitable(Visitable visitable){ //beállítja a következő mezőt
		
	}
	
	public void changeBox(){ //megváltoztatja, hogy van-e épp doboz a játékosnál
		hasBox=!hasBox;
	}
		  	
	
	public void addZPM(){ //hozzáad egyet a játékosnál lévő ZPM-ekhez 
		collectedZPMs++;
		/* Itt még új ZPM-et is kell csináltatni minden második ZPM felvételnél*/
	}
	
	public int getZPMs(){
		return collectedZPMs;
	}
	public int getRow(){
		return coordinates[0];
	}
	
	public int getColumn(){
		return coordinates[1];
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int dir){
		direction=dir;
	}

}
