package minesweeper;

import com.google.inject.Injector;
import minesweeper.aview.gui.MinesweeperFrame;
import minesweeper.aview.tui.TextUI;
import minesweeper.controller.IMinesweeperController;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import java.util.Scanner;

public class Minesweeper {
	private static final Logger LOGGER = Logger.getLogger(Minesweeper.class);

	private Minesweeper() {
	}

	public static void main(String[] args) {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");

		Injector injector = MinesweeperModule.getInjector(GridFactoryProviders.debugEasy);

		IMinesweeperController controller = injector.getInstance(IMinesweeperController.class);

		TextUI tui = injector.getInstance(TextUI.class);
        tui.setPrintGrid(true);
        tui.setPrintCommands(false);

		try {
			SwingUtilities.invokeAndWait(() -> {
				MinesweeperFrame gui = injector.getInstance(MinesweeperFrame.class);
				gui.setVisible(true);
			});
		} catch (Exception e) {
			LOGGER.error("GUI initialization error", e);
		}

		boolean cont = true;
		Scanner scanner = new Scanner(System.in);
		while (cont) {
			cont = tui.processLine(scanner.nextLine());
		}
		scanner.close();

		// same instance because gui is a singleton
		MinesweeperFrame gui = injector.getInstance(MinesweeperFrame.class);
		gui.dispose();
	}

	public static TextUI getTuiInstance() {
		Injector injector = MinesweeperModule.getInjector(GridFactoryProviders.debugEasy);
		return injector.getInstance(TextUI.class);
	}
}
