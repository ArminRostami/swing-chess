package model.pieces;

import javax.swing.ImageIcon;

public class Pawn extends Piece{
	public boolean isWh;
	public Pawn (boolean isWhite){
		super.setIsWhite(isWhite);
		if(isWhite)
			setImg(new ImageIcon(King.class.getResource("/images/whitePawn.png")));
		else
			setImg(new ImageIcon(King.class.getResource("/images/blackPawn.png")));	
		super.id=0;
		
	}
	@Override
	public Boolean canMoveTo(int r,int c){
		int x=super.posX;
		int y=super.posY;
		Boolean wh=super.isWhite;
		Boolean flag=false;
		if(wh){
			//if(y==7&&c==0&&r==x)
			//	flag=true;
			if(x==6 && (r==x-1||r==x-2) && y==c)
				flag=true;
			else if(r==x-1 && y==c)
				flag=true;
		}else{
			if(x==1 && (r==x+1||r==x+2) && y==c)
				flag=true;
			else if(r==x+1 && y==c)
				flag=true;
		}
		return flag;
	}
}
