package minesweeper;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import minesweeper.aview.gui.MinesweeperFrame;
import minesweeper.aview.tui.TextUI;
import minesweeper.controller.IMinesweeperController;
import minesweeper.controller.impl.ControllerWrapper;
import minesweeper.model.IGridFactory;
import minesweeper.model.impl.GridFactory;

public class Minesweeper {
	private Minesweeper() {
	}

	public static void main(String[] args) {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");

		IGridFactory gFact = new GridFactory();
		IMinesweeperController controller = new ControllerWrapper(gFact);
		// Debug open for gui
		controller.openCell(5, 5);

		TextUI tui = new TextUI(controller);
		MinesweeperFrame gui = new MinesweeperFrame(controller);

		boolean cont = true;
		Scanner scanner = new Scanner(System.in);
		while (cont) {
			cont = tui.processLine(scanner.nextLine());
		}
		scanner.close();
	}
}
