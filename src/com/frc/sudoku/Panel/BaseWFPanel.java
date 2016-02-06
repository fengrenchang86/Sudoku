package com.frc.sudoku.Panel;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class BaseWFPanel extends JPanel implements IWFPanel, ActionListener {
	protected JComponent component = null;
	protected String title;
	
	public BaseWFPanel() {
		this.title = "No title";
		initialize();
	}
	
	public BaseWFPanel(String title) {
		this.title = title;
		initialize();
	}
	
	public abstract void initialize();


	@Override
	public String getTitle() {
		return title;
	}
	
}
