package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

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
	
	public intro() throws IOException {
		
		setUndecorated(true);
		
		TextRead text = new TextRead();
		
		rooms = text.getRooms_ini();
		characters = text.getChars_ini();
		objects = text.getObjects_ini();
		characters = data.playerOnArray(characters);
				
		setTitle("Visual Story - By Mario Gallego Cano");
		setVisible(true);
		setResizable(false);
		setSize(1000,750);
		centerFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(64,50,51,255));
				
		addKeyListener(this);
		
		Timer tm = new Timer(1,this);
		tm.start();
		
	}
	
	   private void centerFrame() {

           Dimension windowSize = getSize();
           GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
           Point centerPoint = ge.getCenterPoint();

           int dx = centerPoint.x - windowSize.width / 2;
           setLocation(dx, 0);
   }
	
	public void paint (Graphics g) {
		
		super.paintComponents(g);
								
		g.setColor(new Color(84, 92, 82,255));
		g.fillRect(0, 0, 1000, 800);
		
		switch (state) {
		
		case 0: title(g);break;
		case 1: goals(g);break;
		
		}	
		pressIntro(g);
		
	}
	
	private void title(Graphics g) {
		
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(100, 150, 800, 100, 20, 20);
	
		g.setFont(new Font("vcr osd mono", Font.PLAIN,40) );
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("Wellcome to U-TAD Visual Story", 150, 210);
	
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(355, 550, 270, 50, 20, 20);
	
		g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("By Mario Gallego Cano", 370, 580);
		
	}
	
	private void goals(Graphics g) {
		
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(350, 50, 270, 100, 20, 20);
	
		g.setFont(new Font("vcr osd mono", Font.PLAIN,40) );
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("YOUR GOALS", 365, 110);
		
		g.setColor(new Color(244, 211, 94,255));
		g.drawString("YOU HAVE TO GET TO " + characters[0].getGoal_location().getName(), 200, 350);
		
		if(characters[0].getGoal_object() != null) {
			
			g.drawString("YOU HAVE TO GET THE " + characters[0].getGoal_object().getName(), 200, 450);

		}
		

	}
	
	private void pressIntro(Graphics g) {
		
		g.setColor(new Color(255, 50, 50,255));
		g.fillRoundRect(330, 650, 320, 80, 20, 20);
		g.setColor(new Color(244, 211, 94,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,40) );
		g.drawString("PRESS INTRO", 360, 705);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
				
		switch(e.getKeyCode()) {
		
		case 82:repaint();break;
		
		case 10: 
			if(state == 1) {
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
		
		//repaint();
		
	}

}
