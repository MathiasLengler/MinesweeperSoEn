package minesweeper.aview.gui;

import minesweeper.controller.IMinesweeperController;
import minesweeper.solverplugin.SolverPlugin;
import minesweeper.solverplugin.workers.CompleteSolverWorker;
import minesweeper.solverplugin.workers.SingleStepSolverWorker;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Set;

public class MinesweeperMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MinesweeperMenuBar(final IMinesweeperController controller, final Set<SolverPlugin> plugins) {
		JMenu menu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;

		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		add(menu);

		menuItem = new JMenuItem("New");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(e -> controller.newGame());
		menu.add(menuItem);

		menu.addSeparator();

		ButtonGroup group = new ButtonGroup();

		rbMenuItem = new JRadioButtonMenuItem("Easy");
		rbMenuItem.setMnemonic(KeyEvent.VK_1);
		rbMenuItem.addActionListener(e -> controller.changeSettings(8, 8, 10));
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Intermediate");
		rbMenuItem.setMnemonic(KeyEvent.VK_2);
		rbMenuItem.addActionListener(e -> controller.changeSettings(16, 16, 40));
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Hard");
		rbMenuItem.setMnemonic(KeyEvent.VK_3);
		rbMenuItem.addActionListener(e -> controller.changeSettings(16, 30, 99));
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		// TODO: Add selection window for custom board
		rbMenuItem = new JRadioButtonMenuItem("Custom...");
		rbMenuItem.setMnemonic(KeyEvent.VK_4);
		rbMenuItem.addActionListener(e1 -> {
			JOptionPane optionPane = new JOptionPane(new CustomBoardDialogPanel(), JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			JDialog dialog = optionPane.createDialog(this, "Custom Board");
			dialog.setVisible(true);
		});
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		menu = new JMenu("Debug");
		menu.setMnemonic(KeyEvent.VK_D);
		add(menu);

		menuItem = new JMenuItem("Repack/Resize");
		menuItem.setMnemonic(KeyEvent.VK_R);
		menuItem.addActionListener(e -> ((MinesweeperFrame) SwingUtilities.getWindowAncestor(MinesweeperMenuBar.this)).repack());
		menu.add(menuItem);

		menu = new JMenu("Solve");
		menu.setMnemonic(KeyEvent.VK_D);
		add(menu);

		if (plugins.isEmpty()) {
			menu.setEnabled(false);
		} else {
			for (SolverPlugin plugin : plugins) {
				JMenu subMenu = new JMenu(plugin.getSolverName());
				menu.add(subMenu);

				JCheckBoxMenuItem subMenuCheckBox = new JCheckBoxMenuItem("Guessing");
				boolean defaultGuessing = false;
				subMenuCheckBox.setState(defaultGuessing);
				plugin.setGuessing(defaultGuessing);
				subMenuCheckBox.addItemListener(e -> plugin.setGuessing(e.getStateChange() == ItemEvent.SELECTED));
				subMenu.add(subMenuCheckBox);

				JMenuItem subMenuItem;

				subMenuItem = new JMenuItem("Complete solve");
				subMenuItem.addActionListener(e ->
						new CompleteSolverWorker(plugin).execute());
				subMenu.add(subMenuItem);

				subMenuItem = new JMenuItem("Single step solve");
				subMenuItem.addActionListener(e ->
						new SingleStepSolverWorker(plugin).execute());
				subMenu.add(subMenuItem);
			}
		}
	}
}
