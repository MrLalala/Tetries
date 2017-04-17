package com.Gan.Tetris;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TetriesClient extends Frame{
	private static final long serialVersionUID = 1L;
	
	
	private static final int W = 9;
	private static final int H = 16;
	public  static final int BLOCK = 40;
	
	Image img = null;
	
	public static void main(String[] args) {
		new TetriesClient().launch();
	}
	
	public void launch(){
		setLocation(200,100);
		setSize((W+2)*BLOCK,(H+2)*BLOCK);
		setBackground(Color.blue);
		setResizable(false);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setVisible(true);
		
		new Thread(new paintThread()).start();
	}
	
	public void paint(Graphics g){
		for(int i = 0;i <= W+1;i++){
			g.drawLine(BLOCK*i,BLOCK,BLOCK*i, BLOCK*(H+1));
		}
		for(int i = 0;i <= H+1;i++){
			g.drawLine(BLOCK,BLOCK*i,BLOCK*(1+W),BLOCK*i);
		}
	}
	
	public void update(Graphics g){
		if(img == null){
			img = this.createImage((W+2)*BLOCK,(H+2)*BLOCK);
		}
		Graphics gImg = img.getGraphics();
		paint(gImg);
		g.drawImage(img, 0, 0, null);
	}
	
	private class paintThread implements Runnable{

		public void run() {
			try {
				repaint();
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
