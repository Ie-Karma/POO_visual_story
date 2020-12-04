package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextRead_malo {
	
	public String[] tipos = new String[3];
	public String[][] localizaciones = new String [100][100];
	public String[][] nombres = new String[100][100];
	public String[][] objetos = new String[100][100];
	public String[][] eCharacter = new String [100][2];
	public boolean[] playerObj = new boolean[100];
	
	public int localizacion = 0;
	public int nombre = 0;
	public int objeto = 0;
	
	public int tipo = 0;
	public int nombreLoc = 0;
	public int localizacionAd = 0;
	public int objetoLoc = 0;
	
	@SuppressWarnings("resource")
	public TextRead_malo() throws IOException {
		
		
        try {
            BufferedReader br = new BufferedReader(new FileReader("inicio.txt"));
            StringBuilder sb = new StringBuilder();
			 
            String line = br.readLine(); 
            while (line != null) { 
            	sb.append(line).append("\n"); 
            	line = br.readLine(); 
            	} 
            String string = sb.toString();
            //System.out.println(character);
            string = string.toUpperCase();
            Separador(string);
 			
			
		} catch (FileNotFoundException e) {
			
			System.out.println("No se Pudo Encontrar El Archivo .txt");
			e.printStackTrace();
			System.exit(0);
			
		}
		
	}

	public void Separador(String string) {
		
		boolean totNombres = false;
		boolean totLocalizaciones = false;
		boolean totObjetos = false;
		
		int cont = 0;
		int cont2 = 0;
		int cont3 = 0;
		int cont4 = 0;
		int cont5 = 0;
		
		while(cont < string.length()) {
			
			////////////////////////////////////////////////////////////////////////////////////////////////
			
			if(string.charAt(cont) == '<') {
				
				cont2 = cont;
				tipos[tipo] ="";
				
				while(string.charAt(cont2) != '>') {
					tipos[tipo] = tipos[tipo] + string.charAt(cont2);
					cont2++;
				}
				tipos[tipo] = tipos[tipo] + string.charAt(cont2);
				tipo++;
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////
			
			if(string.charAt(cont) == '>' && totLocalizaciones == false) {
				
				cont4 = cont;
				cont4 += 2;
				localizaciones[localizacion][localizacionAd] ="";
				
				while(string.charAt(cont4) != '<') {

					if(string.charAt(cont4) == '\n') {
						
						localizacionAd = 0;
						localizacion++;
						cont4++;
						localizaciones[localizacion][localizacionAd] ="";
						
					}else {
						if(string.charAt(cont4) == '(') {localizacionAd ++;localizaciones[localizacion][localizacionAd] ="";cont4++;}
						if(string.charAt(cont4) == ',') {localizacionAd ++;localizaciones[localizacion][localizacionAd] ="";cont4+=2;}
						if(string.charAt(cont4) == ')') {cont4++;}
						else {
							localizaciones[localizacion][localizacionAd] = localizaciones[localizacion][localizacionAd] + string.charAt(cont4);
							cont4++;
							}
						}
				}
				cont++;
				totLocalizaciones = true;
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////
			
			if(string.charAt(cont) == '>' && totNombres == false && totLocalizaciones == true) {
				
				cont3 = cont;
				cont3 += 2;
				nombres[nombre][0] ="";
				
				while(string.charAt(cont3) != '<') {

					if(string.charAt(cont3) == '\n') {
						
						nombre++;
						nombreLoc = 0;
						cont3++;
						nombres[nombre][0] ="";
						
					}else {
						if(string.charAt(cont3) == '(') {nombreLoc ++;nombres[nombre][nombreLoc] ="";cont3++;}
						if(string.charAt(cont3) == ')') {cont3++;}
						else {						
							nombres[nombre][nombreLoc] = nombres[nombre][nombreLoc] + string.charAt(cont3);
							cont3++;}
						}

				}
				cont++;
				totNombres = true;
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////
			
			if(string.charAt(cont) == '>' && totNombres == true && totLocalizaciones == true && totObjetos == false) {
				
				cont5 = cont;
				cont5 += 2;
				objetos[objeto][objetoLoc] ="";
				int finalString = string.length();
				finalString -=2;
				
				while(cont5<finalString) {

					if(string.charAt(cont5) == '\n') {
						
						objetoLoc=0;
						objeto++;
						cont5++;
						objetos[objeto][objetoLoc] ="";
						
					}else {
						if(string.charAt(cont5) == '(') {objetoLoc ++;objetos[objeto][objetoLoc] ="";cont5++;}
						if(string.charAt(cont5) == ')') {cont5++;}
						else {
						objetos[objeto][objetoLoc] = objetos[objeto][objetoLoc] + string.charAt(cont5);
						cont5++;}
					}

				}
				objetos[objeto][objetoLoc] = objetos[objeto][objetoLoc] + string.charAt(cont5);
				cont++;
				objeto++;
				totObjetos = true;
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////

			cont++;
			
		}
		
	}
}
