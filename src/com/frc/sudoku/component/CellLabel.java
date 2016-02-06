package com.frc.sudoku.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.frc.sudoku.core.LabelUtil;

public class CellLabel extends JLabel {
	private int value;
	private boolean arr[];
	private boolean highlight[];
	private String html;
	public CellLabel(int value, boolean arr[]) {
		this.value = value;
		this.arr = arr;
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);
		this.setBorder(BorderFactory.createLineBorder(Color.green));
	}
	public void updateCell() {
		this.html = LabelUtil.createLabel(value, arr, highlight);
		this.setText(html);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean[] getArr() {
		return arr;
	}
	public void setArr(boolean[] arr) {
		this.arr = arr;
	}
	public boolean[] getHighlight() {
		return highlight;
	}
	public void setHighlight(boolean[] highlight) {
		this.highlight = highlight;
	}
}
