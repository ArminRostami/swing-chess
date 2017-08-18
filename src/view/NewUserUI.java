package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import controller.UserManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class NewUserUI extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField passField;
	
	public NewUserUI(UserManager controller) {
		setTitle("Sign-up");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 348, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(145, 71, 163, 36);
		contentPane.add(nameField);
		
		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setBounds(145, 123, 163, 36);
		contentPane.add(passField);
		
		JLabel label = new JLabel("User Name:");
		label.setBounds(37, 79, 93, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setBounds(37, 131, 93, 20);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(15, 179, 168, 25);
		contentPane.add(label_2);
		
		JButton btnSignup = new JButton("Sign-up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean flag=controller.addUser(nameField.getText(), String.valueOf(passField.getPassword()));
				if(flag){
				nameField.setText(null);
				passField.setText(null);
				dispose();
				}
				
			}
		});
		btnSignup.setBounds(201, 175, 107, 29);
		contentPane.add(btnSignup);
		
		JLabel lblEnterUsername = new JLabel("Enter Username & Password");
		lblEnterUsername.setBounds(37, 16, 277, 29);
		contentPane.add(lblEnterUsername);
		
		
	}
}
