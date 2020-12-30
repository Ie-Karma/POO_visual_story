package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import data.character;
import data.object;
import data.room;
import tools.TextRead;
import tools.dataTools;

public class intro extends JFrame implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private boolean start = false;
	@SuppressWarnings("unused")
	private screenFrame frame;
	private room[] rooms;
	private character[] characters;
	private object[] objects;
	private dataTools data = new dataTools();
	private int state = 0;
	private String url = ("imgs/tutorial/");
	private Font font40;
	private Font font20;

	
	public static void main(String[] args) throws IOException {
		
		//we start the game
		new intro();
		
	}

	
	public intro() throws IOException {
		
		//we create 2 fonts with different sizes
		try {
			
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     font40 = Font.createFont(Font.TRUETYPE_FONT, new File("font_1.ttf")).deriveFont(40f);
		     font20 = Font.createFont(Font.TRUETYPE_FONT, new File("font_1.ttf")).deriveFont(20f);
		     ge.registerFont(font40);
		     ge.registerFont(font20);

		} catch (IOException|FontFormatException e) {
		     System.out.println("error, font not found");
		}
		
		//we delete the window borders
		setUndecorated(true);
		
		TextRead text = new TextRead();
		
		//we save the txt data in a new objects arrays
		rooms = text.getRooms_ini();
		characters = text.getChars_ini();
		objects = text.getObjects_ini();
		characters = data.playerOnArray(characters);
				
		//some frame settings 
		setTitle("Visual Story - By Mario Gallego Cano");
		setVisible(true);
		setResizable(false);
		setSize(985,715);
		centerFrame();
		setDefaultCloseOperation(3);
		getContentPane().setBackground(new Color(64,50,51,255));
				
		//we add a keylistener to know when the player press any key
		addKeyListener(this);
				
	}
	
	private void centerFrame() {

		//we use this function only to center the game in relation to the screen used
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        //if the game exits the screen from the bottom or the top delete the following code and uncomment the next
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
        
        /*
        int dx = centerPoint.x - windowSize.width / 2;
        setLocation(dx, 0);
        */
        
   }
	
	public void paint (Graphics g) {
		
		//we implement Graphics to paint String and images to make the tutorial
		super.paintComponents(g);
								
		g.setColor(new Color(0, 210, 158,255));
		g.fillRect(0, 0, 1000, 800);
		
		//we generate a new image from the imgs/tutorial/ path depending on the value of state
		ImageIcon img = new ImageIcon(url + (state+1) + ".png");
		g.drawImage(img.getImage(), 0, 0, null);
		
		switch (state) {
		
		case 0: title(g);break;
		case 1: goals(g);break;
		
		}	
		
	}
	
	private void title(Graphics g) {
		
		//we print a simple String to wellcome the player
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(100, 100, 800, 100, 20, 20);
	
		g.setFont(font40);
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("Wellcome to U-TAD Visual Story", 150, 160);
	
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(355, 500, 270, 50, 20, 20);
	
		g.setFont(font20);
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("By Mario Gallego Cano", 370, 530);
		
	}
	
	private void goals(Graphics g) {
		
		//we inform the player of his goals
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(350, 50, 270, 100, 20, 20);
	
		g.setFont(font40);
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("YOUR GOALS", 365, 110);
		
		g.setColor(new Color(244, 211, 94,255));
		g.drawString("YOU HAVE TO GET TO " + characters[0].getGoal_location().getName(), 200, 350);
		
		if(characters[0].getGoal_object() != null) {
			
			g.drawString("YOU HAVE TO GET THE " + characters[0].getGoal_object().getName(), 200, 450);

		}
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
				
		//this function will tell us what key is the player pressing
		switch(e.getKeyCode()) {
				
		case 10: 
			
			//in case he is pressing ENTER we advance the state and repaint the images
			//when the state is 11 we create a screenframe with the data we have read from the txt
			if(state == 11) {
			try {
				frame = new screenFrame(rooms,characters,objects);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
			}else {state++;repaint();}
			break;
			
		}
	}

	
	/////////////////////////////////////////////////////gets, sets and unnused actions
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
	}

}
