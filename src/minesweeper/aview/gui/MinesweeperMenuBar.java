package minesweeper.aview.gui;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.sun.glass.events.KeyEvent;

import minesweeper.controller.IMinesweeperController;

public class MinesweeperMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MinesweeperMenuBar(final IMinesweeperController controller) {
		JMenu menu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;

		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		add(menu);

		menuItem = new JMenuItem("New");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menu.add(menuItem);

		menu.addSeparator();

		ButtonGroup group = new ButtonGroup();

		rbMenuItem = new JRadioButtonMenuItem("Easy");
		rbMenuItem.setMnemonic(KeyEvent.VK_1);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Intermediate");
		rbMenuItem.setMnemonic(KeyEvent.VK_2);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Hard");
		rbMenuItem.setMnemonic(KeyEvent.VK_3);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Custom...");
		rbMenuItem.setMnemonic(KeyEvent.VK_4);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

	}

}