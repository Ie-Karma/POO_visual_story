import java.io.IOException;

import graphic.intro;
import tools.TextRead;

public class Main {

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("unused")
		intro intro = new intro();			
		
		dataTest();
		
	}

	@SuppressWarnings("unused")
	private static void dataTest() throws IOException {
		
		TextRead txt = new TextRead();
		
		System.out.println( "\n" + "-characers-" + "\n");
		
		for(int i = 0; txt.getChars_ini()[i] != null; i++) {
			
			System.out.println("name: " + txt.getChars_ini()[i].getName() + "\n" + "loc: " + txt.getChars_ini()[i].getLocation().getName());
			System.out.println( "goal loc: " + txt.getChars_ini()[i].getGoal_location().getName());

			if(txt.getChars_ini()[i].getObject() != null) {
						System.out.println("object: " + txt.getChars_ini()[i].getObject().getName());}
			else {		System.out.println("object: ******");}
			
			if(txt.getChars_ini()[i].getGoal_object() != null) {
						System.out.println("goal object: " + txt.getChars_ini()[i].getGoal_object().getName());}
			else {		System.out.println("goal object: ******");}
			
			System.out.println("");
			
		}
		
		System.out.println( "\n" + "-objects-" + "\n");
		
		for(int i = 0; txt.getObjects_ini()[i] != null; i++) {
			
			System.out.println("name: " + txt.getObjects_ini()[i].getName());
			
			if(txt.getObjects_ini()[i].getLocation() != null) {
				System.out.println("loc: " + txt.getObjects_ini()[i].getLocation().getName() + "\n");
			}
			
			if(txt.getObjects_ini()[i].getOwner() != null) {

				System.out.println("loc: " + txt.getObjects_ini()[i].getOwner().getName() + "\n");
			}
			
		}		
	}
	
}