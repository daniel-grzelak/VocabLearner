package com.daniel.panels;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class PanelMain extends JPanel implements ActionListener{

	private String username;
	private String role;
	private JMenuItem edit = new JMenu("Edit");
	private JMenuItem intelligent = new JMenuItem("Intelligent mode");
	private JMenuItem revision = new JMenuItem("Revision mode");
	private JMenuItem free = new JMenuItem("Free mode");

	private PanelFree panelFree;
	private PanelIntelligent panelIntelligent;
	private PanelRevision panelRevision;
	
	public PanelMain(String role, String username) {
		super(new CardLayout());
		this.role = role;
		panelFree = new PanelFree();
		panelIntelligent = new PanelIntelligent();
		add(new PanelEdit(username, role), "EDIT PANEL");
		add(panelFree, "FREE PANEL");
		add(panelIntelligent, "INTELLIGENT PANEL");
		add(panelRevision, "REVISION PANEL");
		


		
		

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


	private void showSettingsWindowFree()
	{
		JFrame frame = new JFrame("Free mode settings");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		PanelSettings panelSettings = new PanelSettings(username, panelFree);

		panelSettings.setVisible(true);
		frame.setContentPane(panelSettings); 
		
		
		frame.setVisible(true);
		frame.setResizable(false); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
	}
	
	private void showSettingsWindowIntelligent()
	{
		JFrame frame = new JFrame("Intelligent mode settings");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		PanelSettings panelSettings = new PanelSettings(username, panelIntelligent);

		panelSettings.setVisible(true);
		frame.setContentPane(panelSettings); 
		
		
		frame.setVisible(true);
		frame.setResizable(false); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
	}
	
	private void showSettingsWindowRevision()
	{
		JFrame frame = new JFrame("Revision mode settings");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		PanelSettings panelSettings = new PanelSettings(username, panelRevision);

		panelSettings.setVisible(true);
		frame.setContentPane(panelSettings); 
		
		
		frame.setVisible(true);
		frame.setResizable(false); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == intelligent) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "INTELLIGENT PANEL");
			showSettingsWindowIntelligent();
		} else if (e.getSource() == free) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "FREE PANEL");
			showSettingsWindowFree();
		} else if (e.getSource() == revision) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "REVISION PANEL");
			showSettingsWindowRevision();
		} else if (e.getSource() == edit) {
			CardLayout layout = (CardLayout) getLayout();
			layout.show(this, "EDIT PANEL");

		}
		
	}
}
