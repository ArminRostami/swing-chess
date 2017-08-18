package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class StatsUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel label;

	public StatsUI(UserManager um) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 245, 415);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblTotalGames = new JLabel("Total Games");
		lblTotalGames.setForeground(new Color(253, 245, 230));
		lblTotalGames.setBounds(15, 70, 97, 34);
		contentPane.add(lblTotalGames);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setForeground(new Color(253, 245, 230));
		lblWins.setBounds(15, 115, 97, 34);
		contentPane.add(lblWins);
		
		JLabel lblLoses = new JLabel("Losses");
		lblLoses.setForeground(new Color(253, 245, 230));
		lblLoses.setBounds(15, 162, 97, 28);
		contentPane.add(lblLoses);
		
		JLabel lblDraw = new JLabel("Draw");
		lblDraw.setForeground(new Color(253, 245, 230));
		lblDraw.setBounds(15, 206, 97, 28);
		contentPane.add(lblDraw);
		
		JLabel lblWinrate = new JLabel("Winrate");
		lblWinrate.setForeground(new Color(253, 245, 230));
		lblWinrate.setBounds(15, 250, 88, 28);
		contentPane.add(lblWinrate);
		
		JLabel lblXp = new JLabel("XP");
		lblXp.setForeground(new Color(253, 245, 230));
		lblXp.setBounds(15, 291, 56, 34);
		contentPane.add(lblXp);
		
		textField = new JTextField();
		textField.setBackground(new Color(253, 245, 230));
		textField.setForeground(new Color(139, 69, 19));
		textField.setEditable(false);
		textField.setBounds(123, 72, 69, 30);
		textField.setText(Integer.toString(um.totalGames()));
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(253, 245, 230));
		textField_1.setForeground(new Color(139, 69, 19));
		textField_1.setEditable(false);
		textField_1.setBounds(123, 118, 69, 28);
		textField_1.setText(Integer.toString(um.getSelectedUser().getGamesWon()));
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(253, 245, 230));
		textField_2.setForeground(new Color(139, 69, 19));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(123, 162, 69, 28);
		textField_2.setText(Integer.toString(um.getSelectedUser().getGamesLost()));
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(new Color(253, 245, 230));
		textField_3.setForeground(new Color(139, 69, 19));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(123, 206, 69, 28);
		textField_3.setText(Integer.toString(um.getSelectedUser().getDraws()));
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBackground(new Color(253, 245, 230));
		textField_4.setForeground(new Color(139, 69, 19));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(123, 250, 69, 28);
		textField_4.setText(Integer.toString(um.winRate()));
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBackground(new Color(253, 245, 230));
		textField_5.setForeground(new Color(139, 69, 19));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(123, 294, 69, 28);
		textField_5.setText(Integer.toString(um.getSelectedUser().getXp()));
		contentPane.add(textField_5);
		
		label = new JLabel("");
		label.setForeground(new Color(253, 245, 230));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 16, 177, 28);
		label.setText(um.getSelectedUser().toString()+"'s Stats");
		contentPane.add(label);
	}
}
