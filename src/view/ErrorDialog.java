package view;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
public class ErrorDialog extends JDialog {

	public ErrorDialog(String labelText) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Error!");
		setBounds(100, 100, 342, 263);
		getContentPane().setLayout(null);
		
		JLabel lblExceptionOccured = new JLabel("An Error occured!!");
		lblExceptionOccured.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblExceptionOccured.setBounds(28, 30, 165, 34);
		getContentPane().add(lblExceptionOccured);
		
		JLabel exceptionLabel = new JLabel(labelText);
		exceptionLabel.setBounds(28, 90, 242, 91);
		getContentPane().add(exceptionLabel);
	}
}
