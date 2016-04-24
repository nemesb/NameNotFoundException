package projlab;

public class StarGate extends Tile{
	
	private Tile[] tiles; 	//​Eltárolja a mező szomszédjait.
	Boolean passable;	//Eltárolja, hogy átjárható-e az adott csillagkapu.
	private String color;
	
	public Boolean isPassable(){
		return passable;
	}
	
	public void changePassable(){
		passable=!passable;
	}
	
	public void setColor(String color){
		this.color=color;
	}
	
	public String getColor(){
		return color;
	}
}
