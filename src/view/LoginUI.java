package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import controller.UserManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField passField;
	private UserManager controller;
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Log-in");
		setBounds(100, 100, 334, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(123, 16, 163, 36);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBounds(123, 68, 163, 36);
		contentPane.add(passField);
		passField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(15, 24, 93, 20);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(15, 76, 93, 20);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().login(nameField.getText(), String.valueOf(passField.getPassword()));
				
			}
		});
		btnLogin.setBounds(165, 120, 121, 29);
		contentPane.add(btnLogin);
		
		JLabel lblNotAUserclick = new JLabel("not a user? click here.");
		lblNotAUserclick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NewUserUI NUI=new NewUserUI(getController());
				NUI.setVisible(true);
			}
		});
		lblNotAUserclick.setBounds(15, 165, 282, 31);
		contentPane.add(lblNotAUserclick);
	}
	public UserManager getController() {
		return controller;
	}
	public void setController(UserManager controller) {
		this.controller = controller;
	}
}
