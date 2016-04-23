package projlab;

public class Tile implements Visitable{
	
	private Tile[] tiles; 	//​Eltárolja a mező szomszédjait.
	
	public Visitable getVisitable(int direction){	//Megadja a következő mezőt
		return tiles[direction];
	}

	public void accept(Visitor visitor) {
			visitor.visit(this);
	}

}
