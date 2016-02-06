package com.frc.sudoku.Panel.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.frc.sudoku.IConstants;
import com.frc.sudoku.Panel.IWFPanel;
import com.frc.sudoku.component.CellLabel;
import com.frc.sudoku.core.LabelUtil;
import com.frc.sudoku.core.Sudoku;

@SuppressWarnings("all")
public class SamplePanel extends JPanel implements IWFPanel, ActionListener {
	private final String TITLE = "Sample v1";
	private static final String CMD_UNIQUE_CELL = "CMD_UNIQUE_CELL";
	private static final String CMD_UNIQUE_NUM = "CMD_UNIQUE_NUM";
	private int n = 3;
	private int N = n * n;
	
	private JPanel comp = null;
	private Sudoku sudoku = null;
	
	private CellLabel labelGrid[][] = null;

	public SamplePanel() {
		
		System.out.println("SamplePanel.SamplePanel");
		initSudoku();
		initialize();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (CMD_UNIQUE_CELL.equalsIgnoreCase(action)) {
			onSolve_UniqueCell();
			repaintAllLabel();
		} else if (CMD_UNIQUE_NUM.equalsIgnoreCase(action)) {
			onSolve_UniqueNum();
			repaintAllLabel();
		}
	}
	private void initSudoku() {
		boolean rs = false;
		int rt = 1, c = 0;
		if (sudoku == null) {
			sudoku = new Sudoku();
		}
		String strGrid = "009080000016002038008000076740000000000605000000000043520000300860400290000030800";
		sudoku.readGridFromString(strGrid, n);
		/*
		while (rt > 0 && c < 5) {
				rt = sudoku.solve();
				c++;
			}
		 */
	}
	private void onSolve_UniqueCell() {
		sudoku.solveWithUniqueCell();
	}
	private void onSolve_UniqueNum() {
		sudoku.solveWithUniqueNum();
	}
	private void repaintAllLabel() {
		int[][] grid = sudoku.getGrid();
		boolean[][][] choices = sudoku.getChoices();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				labelGrid[i][j].setValue(grid[i][j]);
				labelGrid[i][j].setArr(choices[i][j]);
				labelGrid[i][j].updateCell();
			}
		}
	}
	private void initialize() {
		System.out.println("SamplePanel.initialize");
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JPanel sudokuGrid = new JPanel();
		sudokuGrid.setLayout(new GridLayout(n, n));
		labelGrid = new CellLabel[N][N];
		for (int i = 0; i < N; i++) {
			labelGrid[i] = new CellLabel[N];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				JPanel panel = createGong(i*n+j);
				sudokuGrid.add(panel);
			}
		}
		sudokuGrid.setPreferredSize(IConstants.GRID_DIMENSION);
		sudokuGrid.setMinimumSize(IConstants.GRID_DIMENSION);
		sudokuGrid.setMaximumSize(IConstants.GRID_DIMENSION);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(IConstants.CTRL_DIMENSION);
		controlPanel.setMinimumSize(IConstants.CTRL_DIMENSION);
		controlPanel.setMaximumSize(IConstants.CTRL_DIMENSION);
		controlPanel.add(new JLabel("Danny"));
		
		JButton btn1 = new JButton("Unique cell");
		btn1.setActionCommand(CMD_UNIQUE_CELL);
		btn1.addActionListener(this);
		controlPanel.add(btn1);
		
		JButton btn2 = new JButton("Unique num");
		btn2.setActionCommand(CMD_UNIQUE_NUM);
		btn2.addActionListener(this);
		controlPanel.add(btn2);

		this.add(sudokuGrid);
		this.add(controlPanel);
		
		sudokuGrid.setBorder(BorderFactory.createLineBorder(Color.blue));
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
	}
	private JPanel createGong(int idx) {
		JPanel comp = new JPanel(new GridLayout(n, n));
		int [][]grid = sudoku.getGrid();
		boolean [][][]choices = sudoku.getChoices();
		int di = idx/n*n;
		int dj = idx%n*n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				CellLabel label = null;
				if (grid[i+di][j+dj] > 0) {
					label = generateLabelBig(grid[i+di][j+dj]);
				} else {
					label = generateLabel(choices[i+di][j+dj]);
				}
				labelGrid[i+di][j+dj] = label;
				comp.add(label);
			}
		}
		Dimension labelDim = new Dimension(IConstants.GONG_SIZE, IConstants.GONG_SIZE);
		comp.setPreferredSize(labelDim);
		comp.setMinimumSize(labelDim);
		comp.setMaximumSize(labelDim);
		comp.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
		return comp;
	}
	
	private String createHTMLString(int k) {
		String html = "<html>";
		String num = String.format("%d", k);
		if (n == 3) {
			html += "<font size='10'>" + num + "</font>";
		} else {
			html += "<font size='6'>" + num + "</font>";
		}
		html += "</html>";
		return html;
	}
	private String createHTMLString(boolean arr[]) {
		String html = "<html>";
		if (n == 3) {
			html += "<font size='3'>";
		} else {
			html += "<font size='1'>";
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int k = i * n + j + 1;
				if (arr[k]) {
					String num = "";
					if (k <= 9) {
						num = String.format("%d", k);
					} else {
						num = String.format("%c", (char)(k-10+'A'));
					}
					html += num;
				} else {
					String num = String.format("&#160;");
					html += num;
				}
			}
			html += "<br/>";
		}
		html += "</font></html>";
		return html;
	}
	private CellLabel generateLabel(boolean arr[]) {
		boolean highlight[] = new boolean[10];
		highlight[2] = true;
		
		CellLabel cellLabel = new CellLabel(0, arr);
		cellLabel.setHighlight(highlight);
		cellLabel.updateCell();
		return cellLabel;
		/*
		String html = createHTMLString(arr);
		html = LabelUtil.createLabel(0, arr, highlight);
		JLabel label = new JLabel(html, JLabel.CENTER);
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(CENTER_ALIGNMENT);
		label.setBorder(BorderFactory.createLineBorder(Color.green));
		return label;*/
	}
	private CellLabel generateLabelBig(int r) {
		boolean highlight[] = new boolean[10];
		highlight[2] = true;
		
		CellLabel cellLabel = new CellLabel(r, null);
		cellLabel.setHighlight(highlight);
		cellLabel.updateCell();
		return cellLabel;
		/*boolean highlight[] = new boolean[10];
		highlight[2] = true;
		String num = String.format("%d", r);
		String html = createHTMLString(r);
		html = LabelUtil.createLabel(r, null, highlight);
		JLabel label = new JLabel(html, JLabel.CENTER);
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(CENTER_ALIGNMENT);
		label.setBorder(BorderFactory.createLineBorder(Color.green));
		return label;*/
	}

	@Override
	public String getTitle() {
		return TITLE;
	}

}
