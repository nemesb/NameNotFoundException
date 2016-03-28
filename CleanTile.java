package projlab;

public class CleanTile extends Tile{
	public void accept(Visitor visitor){
		Tabulator.increaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("-> nextTile.accept(Player)");
		Tabulator.tabMethod();
		System.out.println("<- void");
		new Player().visit(new CleanTile());
		Tabulator.decreaseTabNumber();

	}
	
}
