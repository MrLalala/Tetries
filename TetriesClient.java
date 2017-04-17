package com.Gan.Tetris;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TetriesClient extends Frame{
	private static final long serialVersionUID = 1L;
	
	
	private static final int W = 9;
	private static final int H = 16;
	public  static final int BLOCK = 40;
	public static void main(String[] args) {
		new TetriesClient().launch();
	}
	
	public void launch(){
		setLocation(200,100);
		setSize(W*BLOCK,H*BLOCK);
		setBackground(Color.blue);
		setResizable(false);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		setVisible(true);
	}

}
