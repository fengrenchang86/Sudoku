package com.frc.sudoku.core;

import org.junit.Test;

public class Sudoku {
	private int n = 3;
	private int N = 3;
	private int[][] grid = null;
	/*[i][j][k]
	 * 第i行，第j列，能否填k
	 */
	private boolean[][][] choices = null;
	
	/*[i][j]
	 * 第i行，能否填j
	 */
	private boolean[][] choices_row = null;
	
	/*[i][j]
	 * 第i列，能否填j
	 */
	private boolean[][] choices_col = null;
	
	/*[i][j]
	 * 第i个box，能否填j
	 */
	private boolean[][] box = null;
	
	/*[i][j]
	 * 第i行，第j列，有多少个选择
	 */
	private int[][] statics = null;
	
	/*[i][j]
	 * 第i个box，有多少个选择
	 */
	private int[] statics_box = null;
	
	private static final String REGEX_NUM = "^[\\d]*$";
	
	public Sudoku() {
		init(3);
	}
	/*
	public Sudoku(int n) {
		init(n);
	}*/
	
	public boolean readGridFromString(String str, int n) {
		if (str == null || str.length() != n * n * n * n) {
			return false;
		}
		if (!str.matches(REGEX_NUM)) {
			return false;
		}
		
		init(n);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int k = i * N + j;
				grid[i][j] = Integer.parseInt(str.substring(k, k+1));
			}
		}
		initCalculate();
		
		show();
		return true;
	}
	public int solve() {
		int rt = fillUnique();
		System.out.println("fillUnique:" + rt);
		rt += fillUnique2();
		System.out.println("fillUnique2:" + rt);
		show();
		return rt;
	}
	protected int fillUnique2() {
		int rt = 0;
		int c = 0, v = 0, row = 0, col = 0, bid = 0;
		//In row[i], k can only be fill in grid[i][j]
		for (int i = 0; i < N; i++) {
			for (int k = 1; k <= N; k++) {
				c = 0;
				for (int j = 0; j < N && c <= 1; j++) {
					if (choices[i][j][k]) {
						row = i;
						col = j;
						v = k;
						c++;
					}
				}
				if (c == 1) {
					fill(row, col, v);
					System.out.println(String.format("row[%d] has unique number, grid[%d][%d]=%d", 
							row+1, row+1, col+1, v));
					rt++;
				}
			}
		}
		
		//In col[j], k can only be fill in grid[i][j]
		for (int j = 0; j < N; j++) {
			for (int k = 1; k <= N; k++) {
				c = 0;
				for (int i = 0; i < N && c <= 1; i++) {
					if (choices[i][j][k]) {
						row = i;
						col = j;
						v = k;
						c++;
					}
				}
				if (c == 1) {
					fill(row, col, v);
					System.out.println(String.format("col[%d] has unique number, grid[%d][%d]=%d", 
							row+1, row+1, col+1, v));
					rt++;
				}
			}
		}
		
		//In box[idx], k can only be fill in grid[i][j]
		for (int idx = 0; idx < N; idx++) {
			for (int k = 1; k <= 9; k++) {
				c = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (choices[i+idx/n*n][j+idx%n*n][k]) {
							row = i+idx/n*n;
							col = j+idx%n*n;
							v = k;
							bid = idx;
							c++;
						}
					}
				}
				if (c == 1) {
					fill(row, col, v);
					System.out.println(String.format("box[%d] has unique number, grid[%d][%d]=%d", 
							bid, row+1, col+1, v));
					rt++;
				}
			}
			
		}
		return rt;
	}
	protected int fillUnique() {
		int rt = 0;
		int c = 0, v = 0, row = 0, col = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] != 0) {
					continue;
				}
				c = 0;
				for (int k = 1; k <= N; k++) {
					if (choices[i][j][k]) {
						c++;
						row = i;
						col = j;
						v = k;
					}
				}
				if (c == 1) {
					fill(row, col, v);
					System.out.println(String.format("grid[%d][%d]=%d", 
							row+1, col+1, v));
					rt++;
				}
			}
		}
		return rt;
	}
	public void show() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===============================");
	}
	private void fill(int row, int col, int val) {
		if (!inRange(0, N-1, row) || !inRange(0, N-1, col)
			||!inRange(0, N, val)) {
			System.out.println("fill:Invalid data!");
			return;
		}
		for (int i = 0; i < N; i++) {
			if (choices[row][i][val]) {
				choices[row][i][val] = false;
				statics[row][i]--;
			}
			if (choices[i][col][val]) {
				choices[i][col][val] = false;
				statics[i][col]--;
			}
		}
		int boxId = getBoxId(row, col);
		if (box[boxId][val]) {
			box[boxId][val] = false;
			statics_box[boxId]--;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				choices[i+boxId/n*n][j+boxId%n*n][val] = false;
			}
		}
		grid[row][col] = val;
		for (int i = 1; i <= N; i++) {
			choices[row][col][i] = false;
		}
	}
	
	private boolean inRange(int from, int to, int val) {
		return val >= from && val <= to;
	}
	private int getBoxId(int row, int col) {
		if (!inRange(0, N-1, row) || !inRange(0, N-1, col)) {
			System.out.println("getBoxId:Invalid data!");
			return -1;
		}
		int k = row/n*n + col/n;
		return k;
	}
	private void initCalculate() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] != 0) {
					fill(i, j, grid[i][j]);
				}
			}
		}
	}
	private void init(int n) {
		this.n = n;
		this.N = n * n;
		this.grid = new int[N][N];
		this.choices = new boolean[N][N][N+1];
		this.box = new boolean[N][N+1];
		this.statics = new int[N][N];
		this.statics_box = new int[N];
		for (int i = 0; i < N; i++) {
			statics_box[i] = N;
			for (int j = 0; j < N; j++) {
				this.statics[i][j] = N;
				for (int k = 0; k <= N; k++) {
					box[j][k] = true;
					this.choices[i][j][k] = true;
					
				}
			}
		}
	}
	public int[][] getGrid() {
		return grid;
	}
	public boolean[][][] getChoices() {
		return choices;
	}

	@Test
	public void test() {
		String grid = "009080000016002038008000076740000000000605000000000043520000300860400290000030800";
		grid = "009080000016002038008000076740000000000605000000000043520000300860400290000030800";
		boolean rs = readGridFromString(grid, 3);
		System.out.println("result:" + rs);
		int rt = 1;
		int c = 0;
		while (rt > 0 && c < 5) {
			rt = solve();
			c++;
		}
		System.out.println("c=" + c);
	}
}
