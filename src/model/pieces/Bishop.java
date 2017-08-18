package model.pieces;

import javax.swing.ImageIcon;

public class Bishop extends Piece {
	public Bishop (boolean isWhite){
		setIsWhite(isWhite);
		if(isWhite)
			setImg(new ImageIcon(King.class.getResource("/images/whiteBishop.png")));
		else
			setImg(new ImageIcon(King.class.getResource("/images/blackBishop.png")));
	}
	@Override
	public Boolean canMoveTo(int r,int c){
		int x=super.posX;
		int y=super.posY;
		if((x-r==y-c)||(x-r==c-y))
			return true;
		else
			return false;
	}
}
