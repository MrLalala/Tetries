package com.Gan.Tetris;

public interface Tetris {
	public static final int TRIANGLE = 1;
	public static final int RDIAMOND = 2;
	public static final int LDIAMOND = 3;
	public static final int LINE = 4;
	public static final int SQURE = 5;
	
	void move();
	void change();
}
