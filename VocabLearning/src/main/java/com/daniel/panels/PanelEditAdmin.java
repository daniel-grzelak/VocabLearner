package com.daniel.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.daniel.classes.WordsAll;
import com.daniel.database.Database;

import daniel.grzelak.models.CustomTableModelAdmin;

public class PanelEditAdmin extends JPanel implements ActionListener {
	
	private int id;
	private String username;
	private CustomTableModelAdmin ctma;
	private JTable table;
	private JButton edit;
	private JButton insert;
	private JButton delete;
	private JLabel lWordPlInsert;
	private JLabel lWordEngInsert;
	private JTextField tfWordPlInsert;
	private JTextField tfWordEngInsert;
	private JLabel lWordPlEdit;
	private JLabel lWordEngEdit;
	private JTextField tfWordPlEdit;
	private JTextField tfWordEngEdit;
	private JLabel lIntervalEdit;
	private JLabel lIntervalInsert;
	private JTextField tfIntervalInsert;
	private JTextField tfIntervalEdit;
	
	
	public PanelEditAdmin(String username) {
		super(new BorderLayout());
		this.username = username;
		List<WordsAll> words = Database.selectWordsAdmin();
		List<String> headers = new ArrayList<>(Arrays.asList(new String[] { "ID", "POLISH", "ENGLISH", "USER", "INTERVAL" }));
		ctma = new CustomTableModelAdmin(words, headers);
		table = new JTable(ctma);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				tfWordPlEdit.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfWordEngEdit.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfIntervalEdit.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
			}
		});
		
		add(new JScrollPane(table), BorderLayout.PAGE_START);
		
		JPanel buttons = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		lWordPlInsert = new JLabel("PL: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttons.add(lWordPlInsert, gbc);
		tfWordPlInsert = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttons.add(tfWordPlInsert, gbc);
		lWordEngInsert = new JLabel("ENG: ");
		gbc.gridx = 2;
		gbc.gridy = 0;
		buttons.add(lWordEngInsert, gbc);
		tfWordEngInsert = new JTextField(10);
		gbc.gridx = 3;
		gbc.gridy = 0;
		buttons.add(tfWordEngInsert, gbc);
		lIntervalInsert = new JLabel("Interval: ");
		gbc.gridx = 4;
		gbc.gridy = 0;
		buttons.add(lIntervalInsert, gbc);
		tfIntervalInsert = new JTextField(10);
		gbc.gridx = 5;
		gbc.gridy = 0;
		buttons.add(tfIntervalInsert, gbc);
		insert = new JButton("Insert");
		insert.addActionListener(this);
		insert.setActionCommand("insert");
		gbc.gridx = 3;
		gbc.gridy = 1;
		buttons.add(insert, gbc);
		
		lWordPlEdit = new JLabel("PL: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		buttons.add(lWordPlEdit, gbc);
		tfWordPlEdit = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		buttons.add(tfWordPlEdit, gbc);
		lWordEngEdit = new JLabel("ENG: ");
		gbc.gridx = 2;
		gbc.gridy = 2;
		buttons.add(lWordEngEdit, gbc);
		tfWordEngEdit = new JTextField(10);
		gbc.gridx = 3;
		gbc.gridy = 2;
		buttons.add(tfWordEngEdit, gbc);
		lIntervalEdit = new JLabel("Interval: ");
		gbc.gridx = 4;
		gbc.gridy = 2;
		buttons.add(lIntervalEdit, gbc);
		tfIntervalEdit = new JTextField(10);
		gbc.gridx = 5;
		gbc.gridy = 2;
		buttons.add(tfIntervalEdit, gbc);
		edit = new JButton("Edit");
		edit.addActionListener(this);
		edit.setActionCommand("edit");
		gbc.gridx = 3;
		gbc.gridy = 3;
		buttons.add(edit, gbc);

		
		delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setActionCommand("delete");
		gbc.gridx = 3;
		gbc.gridy = 4;
		buttons.add(delete, gbc);
		
		add(buttons, BorderLayout.CENTER);


		
	}
	


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("insert")) {
			Database.insertWordsEdit(tfWordPlInsert.getText(), tfWordEngInsert.getText(), username, Integer.parseInt(tfIntervalInsert.getText()));
			ctma.update(Database.selectWordsAdmin());
			table.updateUI();
		}else if(e.getActionCommand().equals("edit")) {
			Database.editWords(tfWordPlEdit.getText(), tfWordEngEdit.getText(), this.id, Integer.parseInt(tfIntervalEdit.getText()));
		}else if(e.getActionCommand().equals("delete")) {
			Database.deleteWords(this.id);
		}

	}
}
