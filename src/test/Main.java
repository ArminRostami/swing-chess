package test;
import controller.UserManager;
import view.LoginUI;
public class Main {

	public static void main(String[] args) {
		LoginUI logUI = new LoginUI();
		UserManager um= new UserManager(logUI);
		um.readData();
		System.out.println(um.getUserList().toString());
		logUI.setController(um);
		um.run();
	}
}
