package model;

import model.pieces.Piece;

public class Board {
	private Piece[][] pieces;
	public Board() {
		setPieces(new Piece[8][8]);
	}
	public Piece[][] getPieces(){
		return pieces;
	}
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
	public void setPiece(int r,int c,Piece piece){
		pieces[r][c]=piece;
	}
	
}
