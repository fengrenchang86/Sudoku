package com.frc.sudoku.Panel.impl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
		final JTextArea txtArea = new JTextArea("abc");
		JLabel label = new JLabel("<html>1 2 3</br></html>");
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel label = (JLabel)e.getSource();
				String str = label.getText();
				System.out.println(label);
				txtArea.setText(str);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				char ch = e.getKeyChar();
				int code = e.getKeyCode();
				System.out.println(String.format("keyChar:%c, code:%d", ch, code));
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		mainPanel.add(label);
		mainPanel.add(txtArea);
		
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
