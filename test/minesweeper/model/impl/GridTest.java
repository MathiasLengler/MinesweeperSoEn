package minesweeper.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import minesweeper.model.ICell;
import minesweeper.model.ICell.State;
import minesweeper.model.IGrid;

public class GridTest {
	IGrid<ICell> grid;
	ICell[][] cells;

	@Before
	public void setUp() throws Exception {
		cells = new ICell[2][2];
		cells[0][0] = new Cell(0, 0, State.CLOSED, 0, false);
		cells[0][1] = new Cell(0, 1, State.OPENED, 0, true);
		cells[1][0] = new Cell(1, 0, State.FLAG, 0, false);
		cells[1][1] = new Cell(1, 1, State.OPENED, 1, false);
		grid = new Grid<>(cells, 1);
	}

	@Test
	public void testGetCell() {
		assertEquals(new Cell(0, 0, State.CLOSED, 0, false), grid.getCell(0, 0));
	}

	@Test
	public void testCheckBounds() {
		expectIllegalArg(-1, 0);
		expectIllegalArg(0, -1);
		expectIllegalArg(2, 0);
		expectIllegalArg(0, 2);
	}

	private void expectIllegalArg(int row, int col) {
		try {
			grid.getCell(row, col);
			// we dont excpect to go here
			fail();
		} catch (IllegalArgumentException e) {
			// what we excpected
		}
	}

	@Test
	public void testGetHeight() {
		assertEquals(2, grid.getHeight());
	}

	@Test
	public void testGetWidth() {
		assertEquals(2, grid.getWidth());
	}

	@Test
	public void testGetMines() {
		assertEquals(1, grid.getMines());
	}

	@Test
	public void testGetSecondsSinceCreated() {
		long seconds = grid.getSecondsSinceCreated();
		assertTrue(seconds >= 0 && seconds < 60);
	}

	@Test
	public void testGetCells() {
		List<ICell> cellList = grid.getCells();
		assertTrue(cellList.contains(cells[0][0]));
		assertTrue(cellList.contains(cells[0][1]));
		assertTrue(cellList.contains(cells[1][0]));
		assertTrue(cellList.contains(cells[1][1]));
		assertEquals(4, cellList.size());
	}

	@Test
	public void testGetAdjCells() {
		List<ICell> cellList = grid.getAdjCells(0, 0);
		assertTrue(cellList.contains(cells[0][1]));
		assertTrue(cellList.contains(cells[1][0]));
		assertTrue(cellList.contains(cells[1][1]));
		assertEquals(3, cellList.size());

		assertEquals(cellList, grid.getAdjCells(new Cell(0, 0)));
	}

	@Test
	public void testMkString() {
		assertEquals(" |M\nF|1", grid.toString());
	}

}
