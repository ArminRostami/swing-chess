package controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.Square;

public class MousePressListener extends MouseAdapter {
	private GameController gc;
	private Square sq;
	public MousePressListener(GameController gc,Square sq) {
		this.gc=gc;
		this.sq=sq;	
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(gc.getPieceInHand()==null 
				&& sq.getPiece().getIsOccupied() 
				&& gc.numOfPossibleMoves(sq.getPiece())!=0  
				&& sq.getPiece().getIsWhite()==gc.getGame().getIsWhitesTurn()
				&& gc.getGame().getWinner()==null)
		{
			gc.setPieceInHand(sq.getPiece());
		//	gc.showTileInfo(sq.getPiece());
			gc.showPossibleMoves(gc.getPieceInHand());
		}
		else if(gc.getPieceInHand()!=null){
			gc.movePiece(sq.getPiece().getPosX(), sq.getPiece().getPosY(),gc.getPieceInHand());	
			gc.check();
			if(gc.getGame().getHasAi())
				gc.perfAiMove();
		}
	}

}
