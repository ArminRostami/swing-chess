package model.pieces;

import javax.swing.ImageIcon;

public class Queen extends Piece {
	public Queen (boolean isWhite){
		setIsWhite(isWhite);
		if(isWhite)
			setImg(new ImageIcon(King.class.getResource("/images/whiteQueen.png")));
		else
			setImg(new ImageIcon(King.class.getResource("/images/blackQueen.png")));	
		
	}
	@Override
	public Boolean canMoveTo(int r,int c){
		int x=super.posX;
		int y=super.posY;
		Boolean flag=false;
		if(r==x||y==c)
			flag=true;
		if((x-r==y-c)||x-r==c-y)
			flag=true;
		return flag;
	}
}
