package com.daniel.panels;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class PanelMain extends JPanel implements ActionListener{

	private String username;
	private String role;
	private JMenuItem edit = new JMenu("Edit");
	private JMenuItem intelligent = new JMenuItem("Intelligent mode");
	private JMenuItem revision = new JMenuItem("Revision mode");
	private JMenuItem free = new JMenuItem("Free mode");
	private PanelIntelligent panelIntelligent;
	private PanelEditAdmin panelEditAdmin;
	private PanelEditUser panelEditUser;
	
	public PanelMain(String role, String username) {
		super(new CardLayout());
		this.role = role;
		if(this.getRole().equals("admin")) {
			panelEditAdmin = new PanelEditAdmin(username);
			add(panelEditAdmin, "EDIT PANEL");
		}else if (this.getRole().equals("user")) {
			panelEditUser = new PanelEditUser(username);
			add(panelEditUser, "EDIT PANEL");
		}
		panelIntelligent = new PanelIntelligent();
		
		add(panelIntelligent, "INTELLIGENT PANEL");
		
		

	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public JMenuBar createMenu () {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu modes = new JMenu("Modes");
		
		intelligent = new JMenuItem("Intelligent");
		intelligent.addActionListener(this);
		intelligent.setActionCommand("intelligent");
		
		free = new JMenuItem("Free");
		free.addActionListener(this);
		free.setActionCommand("free");
		
		revision = new JMenuItem("Revision");
		revision.addActionListener(this);
		revision.setActionCommand("revision");
		
		edit = new JMenuItem("Edit");
		edit.addActionListener(this);
		edit.setActionCommand("edit");
		
		modes.add(intelligent);
		modes.add(free);
		modes.add(revision);
		modes.add(edit);
		menuBar.add(modes);
		return menuBar;
	}



	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == intelligent) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "INTELLIGENT PANEL");
		} else if (e.getSource() == free) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "FREE PANEL");
		} else if (e.getSource() == revision) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "REVISION PANEL");
		} else if (e.getSource() == edit) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "EDIT PANEL");

		}
		
	}
}
