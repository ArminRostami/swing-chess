package model;

import java.io.Serializable;

public class Game implements Serializable{
	private Player wPlayer;
	private Player bPlayer;
	private Board gameBoard;
	private Boolean isWhitesTurn;
	private Boolean timeLimited;
	private Player winner;
	private Boolean hasAi;
	
	public Game(Player wPlayer,Player bPlayer,boolean hasAi,boolean timeLimit) {
		this.setTimeLimited(timeLimit);
		this.setIsWhitesTurn(true);
		this.setGameBoard(new Board());
		this.setbPlayer(bPlayer);
		this.setwPlayer(wPlayer);
		this.setHasAi(hasAi);
	}
	@Override
	public String toString(){
		String s=wPlayer.getUser().getUserName()+"'s game";
		return s;
	}
	
	public Player getwPlayer() {
		return wPlayer;
	}
	public void setwPlayer(Player wPlayer) {
		this.wPlayer = wPlayer;
	}
	public Player getbPlayer() {
		return bPlayer;
	}
	public void setbPlayer(Player bPlayer) {
		this.bPlayer = bPlayer;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Boolean getIsWhitesTurn() {
		return isWhitesTurn;
	}

	public void setIsWhitesTurn(Boolean isWhitesTurn) {
		this.isWhitesTurn = isWhitesTurn;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Boolean getHasAi() {
		return hasAi;
	}

	public void setHasAi(Boolean hasAi) {
		this.hasAi = hasAi;
	}
	public Boolean getTimeLimited() {
		return timeLimited;
	}
	public void setTimeLimited(Boolean timeLimited) {
		this.timeLimited = timeLimited;
	}
}
