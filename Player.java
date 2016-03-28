package projlab;

public class Player implements Visitor{
	
	public static Visitable getVisitable(){
		Tabulator.tabMethod();
		System.out.println("-> Player.getVisitable()");
		Tabulator.tabMethod();
		System.out.println("<- Visitable nextTile");
		return null;
	}
	public void visit(Visitable visitable) {
		Tabulator.tabMethod();
		System.out.println("-> Player.visit(nextTile)");
		Tabulator.tabMethod();
		System.out.println("<- void");
	}
	
	public static Boolean getBox(){
		System.out.println("-> Player.getBox()");
		return null;
		
	}
}
