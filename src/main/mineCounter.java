package main;

import java.awt.*;
import javax.swing.*;

public class mineCounter extends JLabel {
	
	private int mines;
	private int minesLeft;
	
	public mineCounter(int mines) {
		super();
		this.mines = mines;
		this.minesLeft = mines;
		updateMines();
		setFont(new Font(null, Font.BOLD, 20));
	}
	
	public void updateMines() {
		setText("" + minesLeft);
	}
	
	public boolean minesLeft() {
		if (minesLeft > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addFlag() {
		if (minesLeft > 0) {
			minesLeft--;
			updateMines();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeFlag() {
		if (minesLeft < mines) {
			minesLeft++;
			updateMines();
			return true;
		} else {
			return false;
		}
	}
	
	public void reset(int mines) {
		this.mines = mines;
		this.minesLeft = mines;
		updateMines();
	}

}
