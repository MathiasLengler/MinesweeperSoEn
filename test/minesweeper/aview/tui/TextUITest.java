package minesweeper.aview.tui;

import static org.junit.Assert.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import minesweeper.controller.impl.MinesweeperController;
import minesweeper.model.impl.GridFactory;

public class TextUITest {
	private TextUI tui;
	private MinesweeperController controller;

	@Before
	public void setUp() throws Exception {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");

		int[][] mineLocations = { { 0, 0 }, { 0, 1 }, { 2, 2 } };
		controller = new MinesweeperController(new GridFactory(3, 3, mineLocations));
		tui = new TextUI(controller);
	}

	@Test
	public void testQuit() {
		assertFalse(tui.processLine("q"));
		assertFalse(tui.processLine("quit"));
	}

	@Test
	public void testNewGame() {
		assertTrue(tui.processLine("n"));
		assertEquals("New game started", controller.getStatusLine());
		assertTrue(tui.processLine("new"));
		assertEquals("New game started", controller.getStatusLine());
		assertTrue(tui.processLine("newGame"));
		assertEquals("New game started", controller.getStatusLine());
	}

	@Test
	public void testOpen() {
		assertTrue(tui.processLine("o 1 0"));
		assertEquals("Opened (1, 0) = 2", controller.getStatusLine());
	}

	@Test
	public void testAround() {
		tui.processLine("o 2 1");
		tui.processLine("f 2 2");
		assertTrue(tui.processLine("a 2 1"));
		assertEquals("Opened all cells around (2, 1) = 1", controller.getStatusLine());
	}

	@Test
	public void testFlag() {
		assertTrue(tui.processLine("f 0 0"));
		assertEquals("Flag set at (0, 0) = F", controller.getStatusLine());
	}
	
	@Test
	public void testIllegalComand() {
		assertTrue(tui.processLine("foobar"));
	}
}
