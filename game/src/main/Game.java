package main;
import java.awt.Graphics;


public class Game extends gameLoop {
	
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setSize(854, 480);
		Thread main = new Thread(this);
		main.start();
		offscreen = createImage(854, 480);
		d = offscreen.getGraphics();
		addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		d.clearRect(0,  0,  854,  480);
		d.drawImage(background,  0,  0, this);
		d.drawImage(foreground,  0, 356,  this);
		for (int i = 0; i < objects.size();i++) {
			d.drawImage(objects.get(i).Img, objects.get(i).x, objects.get(i).y, this);
			d.drawString("Object: " + objects.get(i).fileName + " " + objects.get(i).x + "," + objects.get(i).y, 2, 75 + (i *15));
		}
		d.drawImage(player, x, y, this);
		d.drawString("Score: " + score, 2,  15);
		d.drawString("Level: " + level, 2,  30);
		d.drawString("Health: " + health, 2,  45);
		d.drawString("Position:" + x + "," + y, 2, 60);
		g.drawImage(offscreen,  0,  0,  this);
	}
	public void update(Graphics g) {
		paint(g);
	}

}
