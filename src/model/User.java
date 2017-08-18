package model;
import java.io.Serializable;
public class User implements Serializable{
	private String userName;
	private String password;
	private int xp;
	private int gamesWon;
	private int gamesLost;
	private int draws;
	
	public User(String userName,String password){
		setUserName(userName);
		setPassword(password);
		setXp(0);
		setGamesLost(0);
		setGamesWon(0);
	}
	public String toString(){
		return userName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	public int getGamesLost() {
		return gamesLost;
	}
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	
	
}
