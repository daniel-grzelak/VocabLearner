package com.daniel;

import javax.swing.JFrame;

import com.daniel.classes.User;
import com.daniel.classes.WordsAll;
import com.daniel.database.Database;
import com.daniel.panels.PanelLogin;

public class App 
{
	public static void createAndShowGui()
	{

		JFrame frame = new JFrame("Vocab learner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		PanelLogin panel = new PanelLogin();

		panel.setVisible(true);
		frame.setContentPane(panel); 
		frame.setVisible(true);
		frame.setResizable(false); 
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
    public static void main( String[] args )
    {
		Database.connect();
		Database.createTables();
		
		//Database.insertUser(new User(0, "admin", "admin", "admin"));
		//Database.insertUser(new User(0, "user", "user", "user"));
		

		javax.swing.SwingUtilities.invokeLater(
				new Runnable()
				{
					public void run()
					{
						createAndShowGui();
					}
				}
		);
    }
}
