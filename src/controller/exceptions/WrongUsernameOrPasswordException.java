package controller.exceptions;

import view.ErrorDialog;

public class WrongUsernameOrPasswordException extends Exception{
	
	public void errorDialog(){
		ErrorDialog dialog=new ErrorDialog("wrong username or password,try again.");
		dialog.setVisible(true);
	}
}
