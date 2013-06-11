package main;
import java.awt.*;
import java.util.*;

public class Minesweeper {
	
	private int[][] minefield;
	
	public Minesweeper(int cols, int rows, int mines) {
		
		minefield = new int[rows][cols];
		
		for (int minesCreated = 0; minesCreated < mines; minesCreated++) {
			Point minePoint = generateRandomPoint(cols);
			int x = minePoint.x;
			int y = minePoint.y;
			
			if (minefield[x][y] != 9) {
				minefield[x][y] = 9;
			} else {
				minefield[x][y] = minefield[x][y];
				minesCreated--;
			}
		}
		
		for (int x = 0; x < minefield.length; x++) {
			for (int y = 0; y < minefield[0].length; y++) {
				if (minefield[x][y] != 9) {
					int set = 0;
					if (x == 0) {
						if (y == 0) {
							if (minefield[x][y+1] == 9) set++;
							if (minefield[x+1][y] == 9) set++;
							if (minefield[x+1][y+1] == 9) set++;
						} else if (y == minefield[x].length-1) {
							if (minefield[x][y-1] == 9) set++;
							if (minefield[x+1][y] == 9) set++;
							if (minefield[x+1][y-1] == 9) set++;
						} else {
							if (minefield[x][y-1] == 9) set++;
							if (minefield[x][y+1] == 9) set++;
							if (minefield[x+1][y+1] == 9) set++;
							if (minefield[x+1][y] == 9) set++;
							if (minefield[x+1][y-1] == 9) set++;
						}
					} else if (x == minefield.length-1) {
						if (y == 0) {
							if (minefield[x][y+1] == 9) set++;
							if (minefield[x-1][y] == 9) set++;
							if (minefield[x-1][y+1] == 9) set++;
						} else if (y == minefield[x].length-1) {
							if (minefield[x][y-1] == 9) set++;
							if (minefield[x-1][y] == 9) set++;
							if (minefield[x-1][y-1] == 9) set++;
						} else {
							if (minefield[x][y-1] == 9) set++;
							if (minefield[x][y+1] == 9) set++;
							if (minefield[x-1][y+1] == 9) set++;
							if (minefield[x-1][y] == 9) set++;
							if (minefield[x-1][y-1] == 9) set++;
						}
					} else {
						if (y == 0) {
							if (minefield[x-1][y] == 9) set++;
							if (minefield[x-1][y+1] == 9) set++;
							if (minefield[x][y+1] == 9) set++;
							if (minefield[x+1][y] == 9) set++;
							if (minefield[x+1][y+1] == 9) set++;
						} else if (y == minefield[x].length-1) {
							if (minefield[x-1][y] == 9) set++;
							if (minefield[x-1][y-1] == 9) set++;
							if (minefield[x][y-1] == 9) set++;
							if (minefield[x+1][y] == 9) set++;
							if (minefield[x+1][y-1] == 9) set++;
						} else {
							if (minefield[x-1][y-1] == 9) set++;
							if (minefield[x-1][y] == 9) set++;
							if (minefield[x-1][y+1] == 9) set++;
							if (minefield[x][y-1] == 9) set++;
							if (minefield[x][y+1] == 9) set++;
							if (minefield[x+1][y-1] == 9) set++;
							if (minefield[x+1][y] == 9) set++;
							if (minefield[x+1][y+1] == 9) set++;
						}
					}
					minefield[x][y] = (set != 0) ? set : minefield[x][y];
				}
			}
		}
		
	}
	
	private Point generateRandomPoint(int limit) {
		
		Random rand = new Random();
		
		int x = rand.nextInt(limit);
		int y = rand.nextInt(limit);
		
		return new Point(x, y);
	}
	
	private void internalPrintMinefield(int[][] minefield) {
		for (int x = 0; x < minefield.length; x++) {
			for (int y = 0; y < minefield[0].length; y++) {
				System.out.print(minefield[x][y] + ", ");
			}
			System.out.print("\n");
			System.out.flush();
		}
	}
	
	public void printMinefield() {
		internalPrintMinefield(minefield);
	}
	
	public int[][] getMinefield() {
		return minefield;
	}

}
