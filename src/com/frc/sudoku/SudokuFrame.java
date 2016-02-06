package com.frc.sudoku;

import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.frc.sudoku.Panel.IWFPanel;
import com.frc.sudoku.Panel.impl.SamplePanel;
import com.frc.sudoku.Panel.impl.TestPanel;

@SuppressWarnings("all")
public class SudokuFrame extends JFrame {
	private int panelCount = 0;
	private int currentTabIdx = 0;
	private JTabbedPane tabbedPane = null;
	private JPanel jContentPane = null;

	private IWFPanel samplePanel = null;
	private IWFPanel testPanel = null;

	public SudokuFrame() {
		super();
		System.out.println("SudokuFrame.SudokuFrame");
		initialize();
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				new SudokuFrame().setVisible(true);
			}
		});
	}

	private void registerTab(IWFPanel component) {
		tabbedPane.addTab(component.getTitle(), (Component) component);
		tabbedPane.setMnemonicAt(panelCount, KeyEvent.VK_1 + panelCount);
		panelCount++;
	}

	private void registerTab(String title, JComponent component) {
		tabbedPane.addTab(title, component);
		tabbedPane.setMnemonicAt(panelCount, KeyEvent.VK_1 + panelCount);
		panelCount++;
	}

	private void initialize() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(IConstants.DIALG_DIMENSION);
		add(tabbedPane);
		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		samplePanel = new SamplePanel();
		testPanel = new TestPanel();

		registerTab(testPanel);
		registerTab(samplePanel);
		

		jContentPane = new JPanel();
		jContentPane.add(tabbedPane);

		setTitle("Sudoku");
		setContentPane(jContentPane);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
