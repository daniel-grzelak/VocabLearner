package com.daniel.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.daniel.classes.WordsAll;
import com.daniel.database.Database;


public class PanelSettings extends JPanel {
	
	private String username;
	private JLabel lLanguages;
	private JComboBox cbLanguages;
	private JLabel lWordAmount;
	private JTextField tfWordAmount;
	private static String[] languages = {"PL", "ENG"};
	private JButton bReady;
	private PanelFree panelFree;
	private PanelIntelligent panelIntelligent;
	private PanelRevision panelRevision;
	private List<WordsAll> list;
	public PanelSettings(String username, PanelFree panelFree) {
		super(new BorderLayout());
		this.panelFree = panelFree;
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.username = username;
		JPanel settings = new JPanel(new GridBagLayout());
		lLanguages = new JLabel("Pick language to learn:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		settings.add(lLanguages, gbc);
		
		cbLanguages = new JComboBox<>(languages);
		gbc.gridx = 1;

		settings.add(cbLanguages, gbc);
		
		lWordAmount = new JLabel("How many words do you want to review?");
		gbc.gridx = 3;
		
		settings.add(lWordAmount, gbc);
		
		tfWordAmount = new JTextField(3);
		gbc.gridx = 4;
		settings.add(tfWordAmount, gbc);
		
		bReady = new JButton("Ready!");
		gbc.gridx = 2;
		gbc.gridy = 2;
		settings.add(bReady, gbc);
		bReady.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list = Database.selectWordsFree(username, Integer.parseInt(tfWordAmount.getText()));
				setUpFree();
				JFrame f = (JFrame)(getRootPane().getParent());
				f.dispose();
				
			}
		});
		add(settings, BorderLayout.CENTER);
		

		
	}
	
	public PanelSettings(String username, PanelIntelligent panelIntelligent) {
		super(new BorderLayout());
		this.panelIntelligent = panelIntelligent;
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.username = username;
		JPanel settings = new JPanel(new GridBagLayout());
		lLanguages = new JLabel("Pick language to learn:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		settings.add(lLanguages, gbc);
		
		cbLanguages = new JComboBox<>(languages);
		gbc.gridx = 1;

		settings.add(cbLanguages, gbc);
		
		lWordAmount = new JLabel("How many words do you want to review?");
		gbc.gridx = 3;
		
		settings.add(lWordAmount, gbc);
		
		tfWordAmount = new JTextField(3);
		gbc.gridx = 4;
		settings.add(tfWordAmount, gbc);
		
		bReady = new JButton("Ready!");
		gbc.gridx = 2;
		gbc.gridy = 2;
		settings.add(bReady, gbc);
		bReady.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list = Database.selectWordsIntelligent(username, Integer.parseInt(tfWordAmount.getText()));
				setUpIntelligent();
				JFrame f = (JFrame)(getRootPane().getParent());
				f.dispose();
				
			}
		});
		add(settings, BorderLayout.CENTER);
		

		
	}
	
	public PanelSettings(String username, PanelRevision panelRevision) {
		super(new BorderLayout());
		this.panelRevision = panelRevision;
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.username = username;
		JPanel settings = new JPanel(new GridBagLayout());
		lLanguages = new JLabel("Pick language to learn:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		settings.add(lLanguages, gbc);
		
		cbLanguages = new JComboBox<>(languages);
		gbc.gridx = 1;

		settings.add(cbLanguages, gbc);
		
		lWordAmount = new JLabel("How many words do you want to review?");
		gbc.gridx = 3;
		
		settings.add(lWordAmount, gbc);
		
		tfWordAmount = new JTextField(3);
		gbc.gridx = 4;
		settings.add(tfWordAmount, gbc);
		
		bReady = new JButton("Ready!");
		gbc.gridx = 2;
		gbc.gridy = 2;
		settings.add(bReady, gbc);
		bReady.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list = Database.selectWordsFree(username, Integer.parseInt(tfWordAmount.getText()));
				setUpFree();
				JFrame f = (JFrame)(getRootPane().getParent());
				f.dispose();
				
			}
		});
		add(settings, BorderLayout.CENTER);
		

		
	}
	
	private void setUpFree() {
	
		PanelFree.setUpFree(list, cbLanguages.getSelectedItem().toString(), Integer.parseInt(tfWordAmount.getText())-1);
		System.out.println(list.toString());
		if(cbLanguages.getSelectedItem().toString().matches("ENG")) {
			panelFree.updateLabel(list.get(0).getWordPl());
		}else {
			panelFree.updateLabel(list.get(0).getWordEng());
		}
		

	}
	
	private void setUpIntelligent() {
		PanelIntelligent.setUpIntelligent(list, cbLanguages.getSelectedItem().toString(), Integer.parseInt(tfWordAmount.getText())-1);
		if(cbLanguages.getSelectedItem().toString().matches("ENG")) {
			panelIntelligent.updateLabel(list.get(0).getWordPl());
		}else {
			panelIntelligent.updateLabel(list.get(0).getWordEng());
		}
	}
	

	
	
}
