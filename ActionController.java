package projlab;

public class ActionController {
	public static void move(Visitor player, int direction){
		if(direction==0){
		System.out.println("-> ActionController.move(Player, 0)");
		Tabulator.increaseTabNumber();
		Player.getVisitable();
		new CleanTile().accept(new Player());
		new Player().visit(new CleanTile());
		Tabulator.decreaseTabNumber();
		}
		
		if(direction==1){
			System.out.println("-> ActionController.move(Player, 1)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new CleanTile().accept(new Player());
			new Player().visit(new CleanTile());
			System.out.println("<- void");
			}
		
		System.out.println("<- void");

		
	}
	
	

}
