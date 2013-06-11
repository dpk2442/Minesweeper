package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperGUI implements ActionListener, MouseListener {
	
	JFrame main = new JFrame("Minesweeper");
	
	int rows;
	int cols;
	int mineNumber;
	
	int[][] minefield;
	myButton[][] sweeperContents;
	
	mineCounter mineCounter;
	
	int blocksCleared = 0;
	
	boolean lost = false;
	
	JButton restartButton;

	
	public MinesweeperGUI(int rows, int cols, int mineNumber) {
		
		this.rows = rows;
		this.cols = cols;
		this.mineNumber = mineNumber;
		
		generateNewMinefield(rows, cols, mineNumber);
		
		JPanel sweeperPane = new JPanel(new GridLayout(rows, cols));
		sweeperContents = new myButton[rows][cols];
		
		mineCounter = new mineCounter(mineNumber);
		
		JDesktopPane restartPane = new JDesktopPane();
		
		restartButton = new JButton(createImageIcon("../images/normal.png", ""));
		restartButton.setSize(26, 26);
		restartButton.addActionListener(this);
		restartButton.setActionCommand("restartButton");
		
		restartPane.add(restartButton);
		restartPane.setBackground(main.getBackground());
		
		for (int x= 0; x < rows; x ++) {
			for (int y = 0; y < cols; y++) {
				sweeperContents[x][y] = new myButton(new Point(x, y));
				sweeperContents[x][y].addActionListener(this);
				sweeperContents[x][y].addMouseListener(this);
			}
		}
		for (int x= 0; x < rows; x ++) {
			for (int y = 0; y < cols; y++) {
				sweeperPane.add(sweeperContents[x][y]);
			}
		}
		
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(restartPane, BorderLayout.CENTER);
		topPanel.add(mineCounter, BorderLayout.EAST);
		
		main.setLayout(new BorderLayout());
		
		main.add(sweeperPane, BorderLayout.CENTER);
		main.add(topPanel, BorderLayout.NORTH);
		
		MyMenuBar menu = new MyMenuBar();
		menu.addMenu("File");
		menu.addMenuItem("File", "Restart").addActionListener(this);
		menu.addMenuItem("File", "Exit").addActionListener(this);
		
		main.setJMenuBar(menu);
		
		int squareSize = 30;
		main.setSize(cols * squareSize, (rows * squareSize) + 30);
		main.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - main.getWidth())/2, (Toolkit.getDefaultToolkit().getScreenSize().height - main.getHeight())/2);
		main.setVisible(true);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		restartButton.setLocation(restartPane.getWidth()/2 - restartButton.getWidth()/2, 0);
	}
	
	public void reset() {
		
		generateNewMinefield(rows, cols, mineNumber);
		
		for (int x= 0; x < rows; x ++) {
			for (int y = 0; y < cols; y++) {
				sweeperContents[x][y].reset(this);
			}
		}
		
		lost = false;
		
		mineCounter.reset(mineNumber);
		
		restartButton.setIcon(createImageIcon("images/normal.png", ""));
		
		blocksCleared = 0;
	}
	
	public void generateNewMinefield(int rows, int cols, int mineNumber) {
		Minesweeper minestuff = new Minesweeper(rows, cols, mineNumber);
		minestuff.printMinefield();
		
		this.minefield = minestuff.getMinefield();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof myButton) {
			((myButton)e.getSource()).buttonClicked(this);
			if (!lost) checkWin();
		} else if (e.getSource() instanceof JMenuItem) {
			JMenuItem src = (JMenuItem)e.getSource();
			if (src.getActionCommand().equals("Restart")) reset();
			if (src.getActionCommand().equals("Exit")) System.exit(0);
		} else if (e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals("restartButton")) reset();
		}
	}
	
	public void checkWin() {
		if (blocksCleared == ((rows * cols) - mineNumber)) {
			showAll();
			JOptionPane.showMessageDialog(null, "Congratulations!\n\nYou Won!");
		}
	}
	
	public void lost() {
		if (!lost) {
			lost = true;
			showAll();
			restartButton.setIcon(createImageIcon("images/frown.png", ""));
			JOptionPane.showMessageDialog(null, "Hello.\n\nYou just got your butt kicked. By a computer");
		}
	}
	
	public void showAll() {
		ImageIcon mineCheck = createImageIcon("images/mine_check.png", "");
		for (int x = 0; x < minefield.length; x++) {
			for (int y = 0; y < minefield[0].length; y++) {
				if (minefield[x][y] == 9) {
					sweeperContents[x][y].buttonClicked(true, mineCheck, this);
				}
				sweeperContents[x][y].removeActionListener(this);
				sweeperContents[x][y].removeMouseListener(this);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof myButton) {
			if (e.getButton() == 3) {
				myButton src = ((myButton)e.getSource());
				if (src.getStateEquals(src.Empty) && !src.clicked) {
					if (mineCounter.addFlag()) {
						src.setForeground(Color.red);
						src.setState(src.Flag);
						src.setIcon(createImageIcon("images/flag.png", ""));
					}
				} else if (src.getStateEquals(src.Flag)) {
					mineCounter.removeFlag();
					src.setForeground(Color.red);
					src.setState(src.Question);
					src.setIcon(null);
					src.setText("?");
				} else if (src.getStateEquals(src.Question)) {
					src.setForeground(Color.black);
					src.setState(src.Empty);
					src.setText("");
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

	public static void main(String[] args) {
		new MinesweeperGUI(9, 9, 10);
	}


	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}
