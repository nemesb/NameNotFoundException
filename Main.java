package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{	   
	    System.out.println("Ez a pályád:\n");
	    
	    Scanner scanner = new Scanner(new File("level1.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+" "); 
           
        }
        scanner.close();

		System.out.print("\n\nAdj meg egy parancsot: ");

	}
}
