package model.pieces;

import javax.swing.ImageIcon;

public class King extends Piece {
	private Boolean checked;
	private Boolean mated;
	public King (boolean isWhite){
		setIsWhite(isWhite);
		setChecked(false);
		setMated(false);
		if(isWhite)
			setImg(new ImageIcon(King.class.getResource("/images/whiteking.png")));
		else
			setImg(new ImageIcon(King.class.getResource("/images/blackking.png")));	
		super.id=4;
	}
	@Override
	public Boolean canMoveTo(int r,int c){
		int x=super.posX;
		int y=super.posY;
		double distance=Math.sqrt(Math.pow(x-r, 2)+Math.pow(y-c, 2));
		if((int)distance==1)
			return true;
		else
			return false;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getMated() {
		return mated;
	}
	public void setMated(Boolean mated) {
		this.mated = mated;
	}
}
