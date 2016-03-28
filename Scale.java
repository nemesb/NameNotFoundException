package projlab;

public class Scale extends Tile{
	Boolean hasWeight;
	public static void setWeight(){
		Tabulator.decreaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("-> Scale.setWeight()");
		Door.changePassable();
		Tabulator.tabMethod();
		System.out.println("<- void");
	}
}
