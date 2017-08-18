package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.pieces.Piece;

public class Square extends JPanel {
	private Piece piece;
	private JLabel label;
	
	
	public Square() {
		piece=new Piece();
		label=new JLabel();
	}
	
	public Piece getPiece() {
		return piece;
	}
	public void setPieceInSquare(Piece piece) {
		this.piece = piece;
		label.setIcon(piece.getImg());
		this.add(label);
		label.setVisible(true);
	}
	


}
