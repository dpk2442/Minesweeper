package main;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

class myButton extends JButton {

	Point location;
	boolean clicked;
	
	private String state;
	
	public final String Flag = "F";
	public final String Question = "?";
	public final String Empty = "";

	public myButton(Point location) {
		super();
		this.location = location;
		clicked = false;
		setBackground(new Color(205, 201, 201));
//		setBackground(new Color(150, 255, 150));
		setOpaque(true);
		setBorder(new LineBorder(Color.black));
		setFocusable(false);
		
		this.state = this.Empty;
	}

//	public void doClick(boolean overrideMine, ImageIcon icon) {
//		if (!clicked) {
//			if (!getStateEquals(this.Flag) || (getStateEquals(this.Flag) && overrideMine)) {
//				clicked = true;
//				System.out.println(clicked);
//				super.doClick();
//				if (getStateEquals(this.Flag) && overrideMine) {
//					setIcon(icon);
//				}
//			}
//		}
//	}
//	
//	public void doClick() {
//		this.doClick(false, null);
//	}
	
	public void buttonClicked(MinesweeperGUI parent) {
		buttonClicked(false, null, parent);
	}
	
	public void buttonClicked(boolean overrideMine, ImageIcon icon, MinesweeperGUI parent) {
		if (!clicked) {
			if (!getStateEquals(this.Flag) || (getStateEquals(this.Flag) && overrideMine)) {
				
				parent.blocksCleared++;
				
				clicked = true;


				myButton src = this;

				int[][] minefield = parent.minefield;
				myButton[][] sweeperContents = parent.sweeperContents;

				src.setBackground(Color.white);

				int x = src.location.x;
				int y = src.location.y;

				String bText = "";
				int buttonValue = minefield[x][y];

				if (buttonValue == 0) {
					bText = "";
				} else if (buttonValue == 9) {
					bText = "";
					src.setIcon(createImageIcon("images/mine.png", ""));
				} else {
					bText = "" + buttonValue;
				}

				src.setText(bText);

				if (minefield[x][y] == 0) {
					if (x == 0) { // Top Row
						if (y == 0) { // Top Left Cell
							if (minefield[x][y+1] != 9) sweeperContents[x][y+1].buttonClicked(parent); // Check right
							if (minefield[x+1][y] != 9) sweeperContents[x+1][y].buttonClicked(parent); // Check below
							if (minefield[x+1][y+1] != 9) sweeperContents[x+1][y+1].buttonClicked(parent); // Check diagonal
						} else if (y == minefield[0].length-1) { // Top Right Cell
							if (minefield[x][y-1] != 9) sweeperContents[x][y-1].buttonClicked(parent); // Check left
							if (minefield[x+1][y] != 9) sweeperContents[x+1][y].buttonClicked(parent); // Check below
							if (minefield[x+1][y-1] != 9) sweeperContents[x+1][y-1].buttonClicked(parent); // Check diagonal
						} else { // Top Row
							if (minefield[x][y-1] != 9) sweeperContents[x][y-1].buttonClicked(parent); // Check left
							if (minefield[x][y+1] != 9) sweeperContents[x][y+1].buttonClicked(parent); // Check right
							if (minefield[x+1][y] != 9) sweeperContents[x+1][y].buttonClicked(parent); // Check below
							if (minefield[x+1][y-1] != 9) sweeperContents[x+1][y-1].buttonClicked(parent); // Check below left
							if (minefield[x+1][y+1] != 9) sweeperContents[x+1][y+1].buttonClicked(parent); // Check below left
						}
					} else if (x == minefield.length-1) { // Bottom Row
						if (y == 0) { // Bottom Right Corner
							if (minefield[x][y+1] != 9) sweeperContents[x][y+1].buttonClicked(parent); // Check right
							if (minefield[x-1][y] != 9) sweeperContents[x-1][y].buttonClicked(parent); // Check above
							if (minefield[x-1][y+1] != 9) sweeperContents[x-1][y+1].buttonClicked(parent); // Check diagonal
						} else if (y == minefield[0].length-1) { // Bottom Left Corner
							if (minefield[x][y-1] != 9) sweeperContents[x][y-1].buttonClicked(parent); // Check left
							if (minefield[x-1][y] != 9) sweeperContents[x-1][y].buttonClicked(parent); // Check above
							if (minefield[x-1][y-1] != 9) sweeperContents[x-1][y-1].buttonClicked(parent); // Check diagonal
						} else { // Bottom Row
							if (minefield[x][y-1] != 9) sweeperContents[x][y-1].buttonClicked(parent); // Check left
							if (minefield[x][y+1] != 9) sweeperContents[x][y+1].buttonClicked(parent); // Check right
							if (minefield[x-1][y] != 9) sweeperContents[x-1][y].buttonClicked(parent); // Check above
							if (minefield[x-1][y-1] != 9) sweeperContents[x-1][y-1].buttonClicked(parent); // Check above left
							if (minefield[x-1][y+1] != 9) sweeperContents[x-1][y+1].buttonClicked(parent); // Check above left
						}
					} else { // Middle Rows
						if (y == 0) { // Middle Left Side
							if (minefield[x][y+1] != 9) sweeperContents[x][y+1].buttonClicked(parent); // Check right
							if (minefield[x+1][y] != 9) sweeperContents[x+1][y].buttonClicked(parent); // Check above
							if (minefield[x-1][y] != 9) sweeperContents[x-1][y].buttonClicked(parent); // Check below
							if (minefield[x-1][y+1] != 9) sweeperContents[x-1][y+1].buttonClicked(parent); // Check above right
							if (minefield[x+1][y+1] != 9) sweeperContents[x+1][y+1].buttonClicked(parent); // Check below right
						} else if (y == minefield[0].length-1) { // Middle Right Side
							if (minefield[x][y-1] != 9) sweeperContents[x][y-1].buttonClicked(parent); // Check left
							if (minefield[x+1][y] != 9) sweeperContents[x+1][y].buttonClicked(parent); // Check below
							if (minefield[x-1][y] != 9) sweeperContents[x-1][y].buttonClicked(parent); // Check above
							if (minefield[x-1][y-1] != 9) sweeperContents[x-1][y-1].buttonClicked(parent); // Check above left
							if (minefield[x+1][y-1] != 9) sweeperContents[x+1][y-1].buttonClicked(parent); // Check below left
						} else { // Middle
							if (minefield[x][y-1] != 9) sweeperContents[x][y-1].buttonClicked(parent); // Check left
							if (minefield[x][y+1] != 9) sweeperContents[x][y+1].buttonClicked(parent); // Check right
							if (minefield[x-1][y] != 9) sweeperContents[x-1][y].buttonClicked(parent); // Check above
							if (minefield[x+1][y] != 9) sweeperContents[x+1][y].buttonClicked(parent); // Check below
							if (minefield[x-1][y-1] != 9) sweeperContents[x-1][y-1].buttonClicked(parent); // Check above left
							if (minefield[x-1][y+1] != 9) sweeperContents[x-1][y+1].buttonClicked(parent); // Check above right
							if (minefield[x+1][y-1] != 9) sweeperContents[x+1][y-1].buttonClicked(parent); // Check below left
							if (minefield[x+1][y+1] != 9) sweeperContents[x+1][y+1].buttonClicked(parent); // Check below right
						}
					}
				} else if (minefield[x][y] == 9 && !overrideMine) { // Hit Mine
					System.out.println("you hit a mine");
					parent.lost();
				}

				if (getStateEquals(this.Flag) && overrideMine) {
					setIcon(icon);
				}
			}
		}
	}
	
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public void reset(MinesweeperGUI listeners) {
		clicked = false;
		setForeground(Color.black);
		setBackground(new Color(205, 201, 201));
		setText("");
		setIcon(null);
		setState(this.Empty);
		setEnabled(true);
		
		addActionListener(listeners);
		addMouseListener(listeners);
	}
	
	
	public String getState() {
		return state;
	}
	
	public boolean getStateEquals(String state) {
		if (this.state.equals(state)) return true;
		else return false;
	}

	public void setState(String state) {
		this.state = state;
	}



}