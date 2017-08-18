package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;

public class SettingsUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public SettingsUI(UserManager um) {
		setBounds(100, 100, 240, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(139, 69, 19));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setForeground(new Color(253, 245, 230));
		lblSettings.setBackground(new Color(253, 245, 230));
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setBounds(15, 16, 192, 31);
		contentPanel.add(lblSettings);
		
		JCheckBox SPMBox = new JCheckBox("Show Possible Moves");
		SPMBox.setForeground(new Color(139, 69, 19));
		SPMBox.setBackground(new Color(253, 245, 230));
		SPMBox.setSelected(true);
		SPMBox.setBounds(11, 56, 183, 31);
		contentPanel.add(SPMBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(139, 69, 19));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(139, 69, 19));
				okButton.setBackground(new Color(253, 245, 230));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (SPMBox.isSelected())
							um.setShowMoves(true);
						else if(!SPMBox.isSelected())
							um.setShowMoves(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(139, 69, 19));
				cancelButton.setBackground(new Color(253, 245, 230));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
