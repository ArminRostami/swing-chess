package model.pieces;

import javax.swing.ImageIcon;

public class Knight extends Piece {
	public Knight (boolean isWhite){
		setIsWhite(isWhite);
		if(isWhite)
			setImg(new ImageIcon(King.class.getResource("/images/whiteKnight.png")));
		else
			setImg(new ImageIcon(King.class.getResource("/images/blackKnight.png")));	
		
	}
	@Override
	public Boolean canMoveTo(int r,int c){
		int x=super.posX;
		int y=super.posY;
		Boolean flag=false;
		if(r==x-1 && (c==y-2||c==y+2))
			flag=true;
		else if(r==x+1 && (c==y-2||c==y+2))
			flag=true;
		else if(r==x-2 && (c==y-1||c==y+1))
			flag=true;
		else if(r==x+2 && (c==y-1||c==y+1))
			flag=true;
		
		return flag;
			
	}

}
