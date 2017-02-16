package com.daniel.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelIntelligent extends JPanel implements ActionListener{
	
	JButton button = new JButton("lol");
	
	public PanelIntelligent() {
		super(new BorderLayout());
		add(button, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
