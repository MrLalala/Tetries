package com.Gan.Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class NewTetirs {
	int type;
	int col = 0, row = 0;
	int oldCol, oldRow;
	int flag = 0;
	int [][] shap = new int[4][4];
	
	boolean fall = false;

	Client c;

	public NewTetirs(Client c) {
		this.c = c;
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect((this.col + 1) * Client.BLOCK, (this.row + 1) * Client.BLOCK, Client.BLOCK, Client.BLOCK);
		g.setColor(c);
//		if (flag == 10) 
		if(flag == 2)
		{
			move();
			flag = 0;
		}
		flag++;
	}

	public void keyReleased(KeyEvent e) {
		int x = e.getKeyCode();
		oldCol = this.col;
		if (fall) {
			return;
		}
		switch (x) {
		case KeyEvent.VK_RIGHT:
			this.col += 1;
			if (this.col > Client.W - 1)
				this.col = oldCol;
			if (c.client[this.row][this.col] == 1)
				this.col = oldCol;
			break;
		case KeyEvent.VK_LEFT:
			this.col -= 1;
			if (this.col < 0)
				this.col = oldCol;
			if (c.client[this.row][this.col] == 1)
				this.col = oldCol;
			break;
		case KeyEvent.VK_DOWN:
			int temp = Client.H -1;
			for(int i = 0; i < Client.H; i++){
				if(c.client[i][this.col] == 1){
					 temp = i-1;
					break;
				}
			}
			fall = true;
			c.client[temp][col] = 1;
			break;
		}
	}

	void move() {
		oldRow = this.row;
		this.row += 1;
		if (row == Client.H){
			this.row = oldRow;
			this.fall = true;
			c.client[row][col] = 1;
		}
		if (c.client[row][col] == 1) {
			this.row = oldRow;
			this.fall = true;
			c.client[row][col] = 1;
		}
	}
}
