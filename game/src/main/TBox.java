package main;

public class TBox {
	
	public int x1;
	public final int y1;
	public final int x2;
	public final int y2;
	
	public TBox(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public static boolean collision(TBox box1, TBox box2) {
		return !(
		(box1.y2 < box2.y1) ||
		(box1.y1 > box2.y2) ||
		(box1.x1 > box2.x2) ||
		(box1.x2 < box2.x1) );
	}

}
