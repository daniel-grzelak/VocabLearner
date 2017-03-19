package com.daniel;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

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
		

		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			 try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
