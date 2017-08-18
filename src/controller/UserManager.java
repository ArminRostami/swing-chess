package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controller.exceptions.UserAlreadyExistsException;
import controller.exceptions.WrongUsernameOrPasswordException;
import model.Game;
import model.User;
import view.LoginUI;
import view.MainMenu;

public class UserManager{
	private ArrayList<Game> ongoingGames;
	private ArrayList<User>userList;
	private User selectedUser;
	private LoginUI logUI;
	private Boolean showMoves;
	
	
	public UserManager(LoginUI logUI){
		this.setOngoingGames(new ArrayList<>());
		this.setShowMoves(new Boolean(true));
		this.setLogUI(logUI);
	}
	public void login(String userName,String password){
			if(userList.size()==0){
				try {
					throw new WrongUsernameOrPasswordException();
				} catch (WrongUsernameOrPasswordException e) {
					e.errorDialog();
				}
			}
			for(int i=0;i<userList.size();i++){
				User user=userList.get(i);
				if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
					setSelectedUser(user);
					MainMenu mm=new MainMenu(this);
					mm.setVisible(true);
					logUI.dispose();
				} else
					try {
						throw new WrongUsernameOrPasswordException();
					} catch (WrongUsernameOrPasswordException e) {
						e.errorDialog();
					}		
		}
	}
	
	public void run(){
		logUI.setVisible(true);
	}
	
	public Boolean addUser(String userName,String password){
		Boolean flag=false;
		try{
			if(userName.equals("")||password.equals(""))
				throw new WrongUsernameOrPasswordException();
			for(int i=0;i<userList.size();i++){
				if(userList.get(i).getUserName().equals(userName))
					throw new UserAlreadyExistsException();
			}
			userList.add(new User(userName, password));
			saveData();
			System.out.println("sign up successfull");
			flag=true;
		}catch(UserAlreadyExistsException e){
			e.errorDialog();
		}catch(WrongUsernameOrPasswordException e){
			e.errorDialog();
		}
		return flag;
	}
	
	public int totalGames(){
		return (selectedUser.getGamesWon()+selectedUser.getGamesLost()+selectedUser.getDraws());
	}
	public int winRate(){
		if(totalGames()>0)
			return (selectedUser.getGamesWon()/totalGames()*100);
		else return 0;
	}
	private static Comparator<User> xpCmp = new Comparator<User>() {

		public int compare(User u1, User u2) {
		   int xp1 = u1.getXp();
		   int xp2 = u2.getXp();
		   return xp2-xp1;

	    }};
	public void sortUsersByXp(){
		Collections.sort(userList, xpCmp);
	}
	
	public void saveData(){
		try {
			FileOutputStream fos=new FileOutputStream("users.txt");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(getUserList());
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		}
		
	}
	
	public void saveData2(){
		try {
			FileOutputStream fos=new FileOutputStream("games.txt");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(getOngoingGames());
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void readData(){
		try {
			FileInputStream fis=new FileInputStream("users.txt");
			ObjectInputStream ois=new ObjectInputStream(fis);
			this.setUserList((ArrayList<User>) ois.readObject());
			fis.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readData2(){
		try {
			FileInputStream fis=new FileInputStream("games.txt");
			ObjectInputStream ois=new ObjectInputStream(fis);
			this.setOngoingGames((ArrayList<Game>) ois.readObject());
			fis.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void logOut(){
		setSelectedUser(null);
	}
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	public LoginUI getLogUI() {
		return logUI;
	}
	public void setLogUI(LoginUI logUI) {
		this.logUI = logUI;
	}
	public Boolean getShowMoves() {
		return showMoves;
	}
	public void setShowMoves(Boolean showMoves) {
		this.showMoves = showMoves;
	}
	public ArrayList<Game> getOngoingGames() {
		return ongoingGames;
	}
	public void setOngoingGames(ArrayList<Game> ongoingGames) {
		this.ongoingGames = ongoingGames;
	}
}
