package com.daniel.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.daniel.classes.WordsAll;
import com.daniel.database.Database;

public class PanelIntelligent extends JPanel {

	private double percentageMeter;
	private static double percentage;
	private static String langConfig;

	private static JLabel word;
	private JTextField answer;
	private static List<WordsAll> words;
	private JButton bNext;

	private static int amount;
	private static int whichWord;

	public PanelIntelligent() {
		super(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		JPanel free = new JPanel(new GridBagLayout());
		percentageMeter = 0;
		word = new JLabel("");
		gbc.gridx = 0;
		gbc.gridy = 0;
		free.add(word, gbc);

		answer = new JTextField(10);
		gbc.gridy = 1;
		free.add(answer, gbc);

		bNext = new JButton("Next");
		gbc.gridy = 2;
		bNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (whichWord < amount) {
					if (wordChecker() == true) {
						whichWord++;
						freeModeNext();
					}

				} else {
					if (wordChecker() == true) {
						score();
					}

				}

			}
		});
		free.add(bNext, gbc);

		add(free, BorderLayout.CENTER);
	}

	public static void freeModeNext() {
		if (langConfig.matches("ENG")) {
			PanelIntelligent.word.setText(words.get(whichWord).getWordPl().toString());

		} else {
			PanelIntelligent.word.setText(words.get(whichWord).getWordEng().toString());
		}

	}

	public static void setUpIntelligent(List<WordsAll> words, String langConfig, int amount) {
		PanelIntelligent.words = words;
		PanelIntelligent.langConfig = langConfig;
		PanelIntelligent.amount = amount;
		PanelIntelligent.percentage = (100.0 / (amount + 1));

	}

	public boolean wordChecker() {
		StringBuilder sb = new StringBuilder();
		boolean isCorrect = false;
		if (langConfig.matches("ENG")) {
			if (answer.getText().matches(words.get(whichWord).getWordEng())) {
				percentageMeter += percentage;
				sb.append(words.get(whichWord).getLevel().substring(1) + 1);
				words.get(whichWord).setLevel(sb.toString());
				return isCorrect = true;
			} else {
				sb.append(words.get(whichWord).getLevel().substring(1) + 0);
				words.get(whichWord).setLevel(sb.toString());
				return isCorrect;
			}
		} else {
			if (answer.getText().matches(words.get(whichWord).getWordPl())) {
				percentageMeter += percentage;
				sb.append(words.get(whichWord).getLevel().substring(1) + 1);
				words.get(whichWord).setLevel(sb.toString());
				return isCorrect = true;
			} else {
				sb.append(words.get(whichWord).getLevel().substring(1) + 0);
				words.get(whichWord).setLevel(sb.toString());
				return isCorrect;
			}
		}
	}

	public void score() {
		percentageMeter = Math.ceil(percentageMeter);
		if (percentageMeter <= 50) {
			JOptionPane.showMessageDialog(new JFrame(), "Below 50%. Try again.");
		} else if (percentageMeter > 50 && percentageMeter <= 80) {
			JOptionPane.showMessageDialog(new JFrame(), "You scored " + percentageMeter + ". Not bad!");
		} else if (percentageMeter > 80 && percentageMeter <= 95) {
			JOptionPane.showMessageDialog(new JFrame(), "You scored " + percentageMeter + ". Great!");
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "You scored " + percentageMeter + ". SUPER!");
		}
		Database.updateWordsLevel(words);
	}

	public void updateLabel(String text) {
		word.setText(text);
	}

}
