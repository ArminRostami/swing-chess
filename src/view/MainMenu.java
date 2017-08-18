package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.UserManager;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("unused")
	private UserManager um;
	
	public MainMenu(UserManager um) {
		this.um=um;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 336);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setForeground(new Color(139, 69, 19));
		btnNewGame.setBackground(new Color(253, 245, 230));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewGameUI ngui=new NewGameUI(um);
				ngui.setVisible(true);
			}
		});
		btnNewGame.setBounds(26, 78, 258, 45);
		contentPane.add(btnNewGame);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setForeground(new Color(139, 69, 19));
		btnSettings.setBackground(new Color(253, 245, 230));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingsUI sui=new SettingsUI(um);
				sui.setVisible(true);
			}
		});
		btnSettings.setBounds(26, 200, 93, 45);
		contentPane.add(btnSettings);
		
		JButton btnLeaderboards = new JButton("Leaderboards");
		btnLeaderboards.setForeground(new Color(139, 69, 19));
		btnLeaderboards.setBackground(new Color(253, 245, 230));
		btnLeaderboards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				um.sortUsersByXp();
				LeaderBoardsUI lui=new LeaderBoardsUI(um);
				lui.setVisible(true);
			}
		});
		btnLeaderboards.setBounds(132, 139, 152, 45);
		contentPane.add(btnLeaderboards);
		
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setForeground(new Color(139, 69, 19));
		btnStatistics.setBackground(new Color(253, 245, 230));
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatsUI sui=new StatsUI(um);
				sui.setVisible(true);
			}
		});
		btnStatistics.setBounds(26, 139, 93, 45);
		contentPane.add(btnStatistics);
		
		JButton btnExit = new JButton("Save & Exit");
		btnExit.setForeground(new Color(139, 69, 19));
		btnExit.setBackground(new Color(253, 245, 230));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				um.saveData();
				dispose();
				um.getLogUI().setVisible(true);
			}
		});
		btnExit.setBounds(134, 200, 152, 45);
		contentPane.add(btnExit);
		
		JLabel lblChess = new JLabel("CHESS");
		lblChess.setHorizontalAlignment(SwingConstants.CENTER);
		lblChess.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblChess.setForeground(new Color(253, 245, 230));
		lblChess.setBounds(26, 27, 259, 35);
		contentPane.add(lblChess);
	}
}
