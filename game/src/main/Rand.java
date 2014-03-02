package main;

import java.util.Random;

public class Rand {
	
	static Random r = new Random();

	public static int integer(int num) {
		return r.nextInt(num);
	}
	
	public static int range(int min, int max) {
		return r.nextInt(max - min) + min;
	}

}
