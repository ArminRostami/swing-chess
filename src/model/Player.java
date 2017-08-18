package model;

import java.util.ArrayList;

import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;

public class Player {
	private ArrayList<Piece>pieces;
	private Boolean isWhite;
	private User user;
	
	public Player(Boolean isWhite) {
		this.setIsWhite(isWhite);
		pieces=new ArrayList<>();
		
		Rook lRook=new Rook(isWhite);
		Knight lKnight=new Knight(isWhite);
		Bishop lBishop=new Bishop(isWhite);
		King king =new King(isWhite);
		Queen queen=new Queen(isWhite);
		Bishop rBishop=new Bishop(isWhite);
		Knight rKnight =new Knight(isWhite);
		Rook rRook=new Rook(isWhite);
		Pawn[] pawns=new Pawn[8];
		for(int i=0;i<pawns.length;i++)
			pawns[i]=new Pawn(isWhite);

		pieces.add(lRook);
		pieces.add(lKnight);
		pieces.add(lBishop);
		pieces.add(queen);
		pieces.add(king);
		pieces.add(rBishop);
		pieces.add(rKnight);
		pieces.add(rRook);
		for(int i=0;i<8;i++)
			pieces.add(pawns[i]);
	}
	
	public King getKing(){
		return (King) pieces.get(4);
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
