package main;
import java.util.*;
import javax.swing.*;

public class MyMenuBar extends JMenuBar {
	
	private Hashtable menus = new Hashtable();
	
	public MyMenuBar() {
		super();
	}
	
	public JMenu addMenu(String name) {
		menus.put(name, new Hashtable());
		((Hashtable)menus.get(name)).put("thisMenuIsHere", new JMenu(name));
		this.add((JMenu)((Hashtable)menus.get(name)).get("thisMenuIsHere"));
		return getMenu(name);
	}
	
	public JMenuItem addMenuItem(String menuName, String name) {
		((Hashtable)menus.get(menuName)).put(name, new JMenuItem(name));
		((JMenu) ((Hashtable)menus.get(menuName)).get("thisMenuIsHere")).add((JMenuItem) (((Hashtable) menus.get(menuName)).get(name)));
		return getMenuItem(menuName, name);
	}
	
	public void addMenuSeparator(String menuName) {
		((JMenu) ((Hashtable) menus.get(menuName)).get("thisMenuIsHere")).addSeparator();
	}
	
	public JMenu getMenu(String menuName) {
		return (JMenu) ((Hashtable) menus.get(menuName)).get("thisMenuIsHere");
	}
	
	public JMenuItem getMenuItem(String menuName, String itemName) {
		return (JMenuItem) ((Hashtable) menus.get(menuName)).get(itemName);
	}
}