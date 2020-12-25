package tools;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

public class imgGenerator { 
	 
	//We take advantage of the image generator through the url of an internet page to generate our own images
	//For this we create several random numbers that determine values such as pants, shoes ...
	 private int Shoes; 	//1-4
	 private int Pants; 	//1-20
	 private int Top; 		//1-2
	 private int Mouth;
	 private int HairTop;	//1-9
	 
	 private String hairTone;	//String colores
	 private String pantsTone;	//
	 private String topTone;	//
	 private String topTone2;	//
	 
	 private String[] colores = {"001053","455300","ffe1e1","483211"};
	 
	 private String url;
	 private String url_gen;
	 
	 public Image img;
	 	
	 public imgGenerator() {
		 
		 setImg(imagenes());
		 
	 }
	 
	public Image imagenes() {
			
	    Image image = null;
	    try {
	          URL url = new URL(Generator());
	          image = ImageIO.read(url);
	        } catch (IOException e) {
	        	System.out.println("ERROR TRYING TO GENERATE IMAGE");
	        	System.out.println(url);
	        }
			
	        return image;
			
		}

	 
	public String Generator() {
		
		//we randomize the numbers
		Shoes = (int)(Math.random() * 4) + 1;
		Pants = (int)(Math.random() * 19) + 2;
		Top = (int)(Math.random() * 2) + 1;
		Mouth = (int)(Math.random() * 2) + 1;
		HairTop = (int)(Math.random() * 9) + 1;
			
		hairTone = colores[(int)(Math.random() * 3)];
		pantsTone = colores[(int)(Math.random() * 3)];
		topTone = colores[(int)(Math.random() * 3)];
		topTone2 = colores[(int)(Math.random() * 3)];

		//when we have all those numbers we create the url
		url = "{\"Shoes\":\"" + Shoes + "\",\"Pants\":\"" + Pants + "\",\"Top\":\"" + Top + "\",\"Mouth\":\"" + Mouth +"\",\"HairTop\":\"" + HairTop + "\",\"hairTone\":\"" + hairTone + "\",\"pantsTone\":\"" + pantsTone + "\",\"topTone\":\"" + topTone + "\",\"topTone2\":\"" + topTone2 + "\"}";			
						
		//now we transform the url to base64
		String String64 = Base64.getEncoder().encodeToString(url.getBytes());
			 
		//and we add the website to create the whole url
		url_gen = "http://www.avatarsinpixels.com/chibi/" + String64 + "/10/show.png";
			 		
		return url_gen;
			
	}
	

	/////////////////////////////////////////////////////gets and sets

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}
