package controller.exceptions;

import view.ErrorDialog;

public class UserAlreadyExistsException extends Exception {
	public void errorDialog(){
		ErrorDialog dialog=new ErrorDialog("username already exists,try again.");
		dialog.setVisible(true);
	}
}
