package view;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.UserManager;
import model.User;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
public class LeaderBoardsUI extends JFrame {

	private JPanel contentPane;

	public LeaderBoardsUI(UserManager um) {
		getContentPane().setLayout(null);
		
		DefaultListModel<String>list=new DefaultListModel<>();
		{
			for(int i=0;i<um.getUserList().size();i++){
				User user=um.getUserList().get(i);
				String st=new String(i+1+"-"+user.toString()+" xp:"+user.getXp()+"\n");
				list.addElement(st);
			}
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 386);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList<String> list_1 = new JList<String>(list);
		list_1.setBackground(new Color(253, 245, 230));
		list_1.setBounds(15, 52, 278, 262);
		contentPane.add(list_1);
		
		JLabel lblLeaderboards = new JLabel("Leaderboards");
		lblLeaderboards.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLeaderboards.setForeground(new Color(250, 240, 230));
		lblLeaderboards.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaderboards.setBounds(15, 16, 278, 32);
		contentPane.add(lblLeaderboards);
		

	}
}
