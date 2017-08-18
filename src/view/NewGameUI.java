package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.GameController;
import controller.UserManager;
import model.Game;
import model.Player;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class NewGameUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<Game> comboBox;
	private UserManager um;
	
	public NewGameUI(UserManager um) {
		this.um=um;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 329, 303);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setForeground(new Color(253, 245, 230));
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewGame.setBounds(32, 16, 134, 36);
		contentPane.add(lblNewGame);
		
		JCheckBox chckbxTimeLimited = new JCheckBox("Time Limited");
		chckbxTimeLimited.setBounds(32, 152, 155, 29);
		contentPane.add(chckbxTimeLimited);

		JRadioButton rdbtnPlayerVsCpu = new JRadioButton("Player vs CPU");
		rdbtnPlayerVsCpu.setBackground(new Color(253, 245, 230));
		rdbtnPlayerVsCpu.setBounds(32, 64, 155, 29);
		contentPane.add(rdbtnPlayerVsCpu);
		
		JRadioButton rdbtnPlayerVsPlayer = new JRadioButton("Player vs Player");
		rdbtnPlayerVsPlayer.setBackground(new Color(253, 245, 230));
		rdbtnPlayerVsPlayer.setBounds(32, 108, 155, 29);
		contentPane.add(rdbtnPlayerVsPlayer);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnPlayerVsCpu);
		bg.add(rdbtnPlayerVsPlayer);
		
		comboBox = new JComboBox<Game>();
		comboBox.setBounds(131, 193, 148, 31);
		contentPane.add(comboBox);
		populateBox();
		
		JButton btnStartGame = new JButton("GO!");
		btnStartGame.setForeground(new Color(139, 69, 19));
		btnStartGame.setBackground(new Color(253, 245, 230));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean limit=false;
				if(chckbxTimeLimited.isSelected())
					limit=true;
				if(rdbtnPlayerVsPlayer.isSelected()){
				
					Player player=new Player(true);
					player.setUser(um.getSelectedUser());
					Game game=new Game(player,new Player(false),false,limit);
					GameController gc= new GameController(game,um.getShowMoves());
					gc.getBui().setUm(um);
					gc.setNewBoard();
				}else if(rdbtnPlayerVsCpu.isSelected()){
					Player player=new Player(true);
					player.setUser(um.getSelectedUser());
					Game game=new Game(player,new Player(false),true,limit);
					GameController gc= new GameController(game,um.getShowMoves());
					gc.getBui().setUm(um);
					gc.setNewBoard();	
				}else{
					Player player=new Player(true);
					player.setUser(um.getSelectedUser());
					Game g=(Game)comboBox.getSelectedItem();
					GameController gc= new GameController(g,um.getShowMoves());
					gc.getBui().setUm(um);
					gc.loadBoard(g);
					
				}
			}
		});
		btnStartGame.setBounds(209, 64, 70, 73);
		contentPane.add(btnStartGame);
		
	
		
		JLabel lblOngoing = new JLabel("ongoing:");
		lblOngoing.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOngoing.setForeground(new Color(253, 245, 230));
		lblOngoing.setBackground(new Color(253, 245, 230));
		lblOngoing.setBounds(32, 192, 90, 31);
		contentPane.add(lblOngoing);
		
		
	}
	public void populateBox(){
		for(int i=0;i<um.getOngoingGames().size();i++)
			comboBox.addItem(um.getOngoingGames().get(i));
	}
}
