package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

 class GameObject {
	
	public int x, y, w, h;
	private static TBox currentBounds;
	public final int damage, value;
	public final String fileName;
	public BufferedImage Img;
	
	public GameObject(int x, int y, int w, int h, int damage, int value, String fileName) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.damage = damage;
		this.value = value;
		this.fileName = fileName;
		try {
			this.Img = ImageIO.read(new File("main/" + fileName));
		} catch (IOException e) {
			this.Img = null;
			System.out.println("Error loading image");
		}
	}

	public static TBox getCurrentBounds() {
		return currentBounds;
	}

	public static void setCurrentBounds(TBox currentBounds) {
		GameObject.currentBounds = currentBounds;
	}	
	
}
