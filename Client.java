package com.Gan.Tetris;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Client extends Frame {
	private static final long serialVersionUID = 1L;

	public static final int W = 9;
	public static final int H = 16;
	public static final int BLOCK = 40;
	public static int col = BLOCK;
	public static int row = BLOCK;
	Image img = null;
	NewTetirs t ;
	int[] f = {1,1,1,1,1,1,1,1,1};
	int[] ff = {0,0,0,0,0,0,0,0,0};
	int [][] client = new int[16][9];
	
	public static void main(String[] args) {
		new Client().launch();
	}

	public void launch() {
		setLocation(200, 100);
		setSize((W + 7) * BLOCK, (H + 2) * BLOCK);
		setBackground(Color.blue);
		setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				t.keyReleased(e);
			}
		});
		setVisible(true);
		t = new NewTetirs(this);
		new Thread(new paintThread()).start();
	}

	public void paint(Graphics g) {
		if(t.fall)
			t = new NewTetirs(this);
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		removeLine();
		g.fillRect(0, 0,(W + 7) * BLOCK, (H + 2) * BLOCK);
		t.paint(g);
		drawFall(g);
		g.setColor(Color.black);
		for (int i = 0; i <= W + 1; i++) {
			g.drawLine(BLOCK * i, BLOCK, BLOCK * i, BLOCK * (H + 1));
		}
		for (int i = 0; i <= H + 1; i++) {
			g.drawLine(BLOCK, BLOCK * i, BLOCK * (1 + W), BLOCK * i);
		}
		for (int i = 11; i <= 15; i++) {
			g.drawLine(i * BLOCK, BLOCK * 3, i * BLOCK, BLOCK * 7);
		}
		for (int i = 3; i <= 7; i++) {
			g.drawLine(11 * BLOCK, BLOCK * i, 15 * BLOCK, BLOCK * i);
		}
		g.setColor(c);
	}
	
	public void drawFall(Graphics g){
		for (int row = 0; row < client.length; row++) {
			for (int col = 0; col < client[row].length; col++) {
				if (client[row][col] == 1) {
					Color c = g.getColor();
					g.setColor(Color.yellow);
					g.fillRect((col + 1) * Client.BLOCK, (row + 1) * Client.BLOCK, BLOCK, BLOCK);
					g.setColor(c);
				}
			}
		}
	}
	
	public void removeLine(){
		for (int row = client.length-1; row >= 0 ; row--) {
			if(Arrays.equals(client[row], f)){
				client[row] = ff;
				for(int a = row; a > 1 ; a--){
					client[a] = client[a-1];
				}
			}	
		}
	}
	
	public void update(Graphics g) {
		if (img == null) {
			img = this.createImage((W + 2) * BLOCK, (H + 2) * BLOCK);
		}
		Graphics gImg = img.getGraphics();
		gImg.setColor(Color.BLUE);
		paint(gImg);
		g.drawImage(img, 0, 0, null);
	}

	private class paintThread implements Runnable {

		public void run() {
			try {
				while (true) {
					repaint();
//System.out.println("Paint Thread");
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
