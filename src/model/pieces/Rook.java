package model.pieces;

import javax.swing.ImageIcon;

public class Rook extends Piece {
	public Rook (boolean isWhite){
		setIsWhite(isWhite);
		if(isWhite)
			setImg(new ImageIcon(King.class.getResource("/images/whiteRook.png")));
		else
			setImg(new ImageIcon(King.class.getResource("/images/blackRook.png")));	
		super.id=2;
	}
	@Override
	public Boolean canMoveTo(int r,int c){
		int x=super.posX;
		int y=super.posY;
		if(r==x||y==c)
			return true;
		else 
			return false;
	}

}
