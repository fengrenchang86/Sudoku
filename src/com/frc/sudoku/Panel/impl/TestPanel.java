package com.frc.sudoku.Panel.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.frc.sudoku.Panel.IWFPanel;

@SuppressWarnings("all")
public class TestPanel extends JPanel implements IWFPanel, ActionListener {
	private static final String TITLE = "Test!!!";
	
	public TestPanel() {
		initialize();
	}
	
	private void initialize() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		
		JTextArea txtArea = new JTextArea("111222\n33123123213123k21ll3jlk______3", 3, 30);
		txtArea.setLineWrap(true);
		
		JScrollPane areaScrollPane = new JScrollPane(txtArea);
		areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		
		mainPanel.add(areaScrollPane);
		
		this.add(mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
	}

	@Override
	public String getTitle() {
		return TITLE;
	}

}
