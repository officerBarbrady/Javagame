package main;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import main.GameObject;
import main.TBox;

import javax.imageio.ImageIO;

public class gameLoop extends Applet implements Runnable, KeyListener {

	static final long serialVersionUID = 1L;
	public Image offscreen;
	public Graphics d;
	public boolean up, down, left, right;
	public BufferedImage background, foreground, player;
	public int counter, score, x, y, width, height, health, speed, level;
	public Long lastTime;
	public TBox bounds;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	GameObject spike = new GameObject(200, 400, 10, 20, 20,
			5000, "fire.png");
	GameObject mj = new GameObject(Rand.range(200, 400), Rand.range(400, 450), 20, 20, 10,
			5000, "plant.png");

	public void addObjectList(GameObject... object) {
		for (int i = 0; i < object.length; i++)
			objects.add(object[i]);
	}

	public void deleteObject(int index) {
		objects.remove(index);
	}

	public void updateObjectPositions() {
		for (int i = 0; i < objects.size(); i++) {
			switch (objects.get(i).fileName) {
			case "fire.png": {
				objects.get(i).x--;
				break;
			}
			case "plant.png": {
				objects.get(i).x--;
				if (Rand.range(0, 8) == 1) {
					if ((objects.get(i).y < 473) && (objects.get(i).y > 356))
						objects.get(i).y = objects.get(i).y
								+ (int) ((Math.sin(Rand.integer(5)) + Math
										.cos(Rand.integer(5))) * 3);
				}
				break;
			}
			}
			if (objects.get(i).x < -2) {
				objects.remove(i);
				return;
			}
		}
	}
	
	public void updateObjectBoxes() {
		for (int i = 0; i < objects.size(); i++) {
			int x1 = objects.get(i).x;
			int y1 = objects.get(i).y;
			int x2 = objects.get(i).x + objects.get(i).w;
			int y2 = objects.get(i).y + objects.get(i).h;
			objects.get(i);
			GameObject.setCurrentBounds(new TBox(x1, y1, x2, y2));
		}
	}
	
	public void updatePlayerBounds() {
		int x1 = x;
		int y1 = y;
		int x2 = x + width;
		int y2 = y + height;
		bounds = new TBox(x1, y1, x2, y2);	
	}
	
	public void handleScore(String objectName) {
		switch(objectName) {
		case "plant.png": {
			health += 5;
			score += 10;
			speed += 10;
			lastTime = System.currentTimeMillis();
			break;
		}
		}
		
	}
	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i);
			if (GameObject.getCurrentBounds() == null)
				return;
			if (TBox.collision(GameObject.getCurrentBounds(), bounds)) {
				handleScore(objects.get(i).fileName);
				objects.remove(i);
				return;
			}
		}
	}

	public void addRandomObjects() {

	}

	public void run() {
		level = 1;
		x = 100;
		y = 400;
		width = 20;
		height = 26;
		health = 10000;
		addObjectList(spike, mj);
		try {
			background = ImageIO.read(new File("main/background.png"));
			foreground = ImageIO.read(new File("main/foreground.png"));
			player = ImageIO.read(new File("main/player.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			updatePlayerBounds();
			level = ((int) score / 1000) + 1;
			counter++;
			if (counter > 10) {
				health -= 1;
				counter = 0;
				updateObjectPositions();
				updateObjectBoxes();
			}
			if (bounds != null)
				checkCollision();
			//if ((System.currentTimeMillis() - lastTime) > (5000) {
				
			//}
			if (y <= 356)
				up = false;
			if (x > 845)
				right = false;
			if (x < 1)
				left = false;
			// if ( y <= 348 && jump != true) {
			// y+=4;
			// }
			if (left == true) {
				x--;
			}
			if (right == true) {
				x++;
			}
			if (up == true) {
				y--;
				// counter2 += 0.04;
				// y = y + (int) ((Math.sin(counter2) + Math.cos(counter2)) *
				// 8);
				// if (counter2 >= 6.8) {
				// counter2 = 4;
				// }

			}
			if (down == true) {
				y++;
			}
			if (y > 473) {
				y = 473;
			}
			repaint();
			try {
				Thread.sleep(6 + speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = true;
		}
		if (e.getKeyCode() == 38) {
			up = true;
		}
		if (e.getKeyCode() == 39) {
			right = true;
		}
		if (e.getKeyCode() == 40) {
			down = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = false;
		}
		if (e.getKeyCode() == 38) {
			up = false;
			counter = 4;
		}
		if (e.getKeyCode() == 39) {
			right = false;
		}
		if (e.getKeyCode() == 40) {
			down = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}
