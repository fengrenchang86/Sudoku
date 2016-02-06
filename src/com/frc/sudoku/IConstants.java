package com.frc.sudoku;

import java.awt.Dimension;

public interface IConstants {
	public final static int WIN_WIDTH = 800;
	public final static int WIN_HEIGHT = 600;
	
	public final static int CELL_SIZE = 60;
	public final static int GONG_SIZE = CELL_SIZE * 3;
	public final static int GRID_SIZE = GONG_SIZE * 3;
	
	public final static int CTRL_PANEL_WIDTH = 220;
	public final static int CTRL_PANEL_HEIGHT = WIN_HEIGHT;
	
	public final static Dimension DIALG_DIMENSION = new Dimension(WIN_WIDTH, WIN_HEIGHT);
	public final static Dimension GRID_DIMENSION = new Dimension(GRID_SIZE, GRID_SIZE);
	public final static Dimension CTRL_DIMENSION = new Dimension(CTRL_PANEL_WIDTH, CTRL_PANEL_HEIGHT);
}
