package model.pieces;

import javax.swing.ImageIcon;

public class Piece {
	
	protected Boolean isWhite;
	protected Boolean isOccupied;
	protected int id;
	protected int posX;
	protected int posY;
	protected transient ImageIcon img;
	
	public Piece() {
		setIsOccupied(false);
	}
	
	
	public Boolean getIsWhite() {
		return isWhite;
	}
	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int currPosX) {
		this.posX = currPosX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int currPosY) {
		this.posY = currPosY;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public Boolean getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}	
	public Boolean canMoveTo(int r,int c){
		return false;
	}
	
	public void setPos(int pos){
		posX=pos/8;
		posY=pos%8;
	}
	public int getPos(int x,int y){
		return (x*8+y);
	}
}
