package graphic;

import java.awt.*;

import javax.swing.*;

public class screen extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public screen() {
			
		this.setUndecorated(true);
		
		this.setTitle("Visual Story - By Mario Gallego Cano");
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(1000,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void paint (Graphics g) {
		
		super.paintComponents(g);
				
		g.setColor(new Color(0, 0, 0,180));
		g.fillRoundRect(25,25, 950, 700, 20, 20);
		
	}
	
	
}
