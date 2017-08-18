package controller;
import java.awt.Color;
import java.util.ArrayList;
import model.Game;
import model.Player;
import model.pieces.King;
import model.pieces.Piece;
import view.BoardUI;
import view.Square;
public class GameController {
	private Game game;
	private BoardUI bui;
	private Piece pieceInHand;
	private Boolean showMoves;
	
	
	public GameController(Game game,Boolean showMoves) {
		setShowMoves(showMoves);
		setGame(game);
		setBui(new BoardUI(this));
		setPieceInHand(null);
	}
	
	public void updateBoardUI(){
		for(int i=0;i<64;i++){
			int r=i/8;
			int c=i%8;
			Piece[][] pieces=game.getGameBoard().getPieces();
			if(pieces[r][c]!=null){
				getSquare(r, c).setPieceInSquare(pieces[r][c]);
			}
		}
		bui.setVisible(true);
	}
	
	public void setPiece(int r,int c,Piece piece){
		Piece[][] pieces=game.getGameBoard().getPieces();
		pieces[r][c]=piece;
		pieces[r][c].setIsOccupied(true);
		pieces[r][c].setPosX(r);
		pieces[r][c].setPosY(c);
		updateBoardUI();
	}
	
	public void movePiece(int x,int y,Piece piece){
		if(isMovableTo(x, y, piece)){
			setPiece(getPieceInHand().getPosX(), getPieceInHand().getPosY(),new Piece());
			game.getGameBoard().getPieces()[piece.getPosX()][piece.getPosY()].setIsOccupied(false);
			setPiece(x, y, piece);
			setPieceInHand(null);	
			revertBackgrounds();
			changeTurn();
			
		}
	}
	
	public void showPossibleMoves(Piece piece){  //highlight possible moves with blue
		if(showMoves){
			for(int i=0;i<64;i++){
				int r=i/8;
				int c=i%8;
				if(isMovableTo(r, c, piece)){
					getBui().getSquares().get(i).setBackground(new Color(102, 163, 255));
				}
			}
		}
	}
	
	public int numOfPossibleMoves(Piece piece){
		int moves=0;
		for(int i=0;i<64;i++){
			int r=i/8;
			int c=i%8;
			if(isMovableTo(r, c, piece)){
				moves++;
			}
		}
		return moves;
	}
	
	public ArrayList<Integer> getMoveLocations(Piece piece){
		ArrayList<Integer> moves=new ArrayList<>();
		for(int i=0;i<64;i++){
			int r=i/8;
			int c=i%8;
			if(isMovableTo(r, c, piece)){
				moves.add(i);
			}
		}
		return moves;
	}
	
	public void revertBackgrounds(){   //reverts background color from blue to normal
		for(int i=0;i<64;i++){
			int r=i/8;
			int c=i%8;
			ArrayList<Square>squares=bui.getSquares();
			if ((r+c)%2==0){
				squares.get(i).setBackground(new Color(255, 204, 153));
			}		
			else{
				squares.get(i).setBackground(new Color(204, 102, 0));
			}
		}
	}
	
	public Boolean moveCheckNormalPieces(int x,int y,Piece piece){
		return (piece.canMoveTo(x,y) && !isOccupied(x, y, piece) && !isBlocked(x, y, piece));
	}
	
	public Boolean moveCheckPawn(int x,int y,Piece piece){
		Boolean movable=false;
		int r=piece.getPosX();
		int c=piece.getPosY();
		if(piece.getIsWhite()==true && x==r-1 && (y==c-1||y==c+1) && hasEnemyUnit(x, y, piece))
			movable=true;
		else if(piece.getIsWhite() && (x==r-1||x==r-2) && hasEnemyUnit(x, y, piece))
			movable=false;
		else if(piece.getIsWhite()==false && x==r+1 && (y==c-1||y==c+1) && hasEnemyUnit(x, y, piece))
			movable=true;
		else if(!piece.getIsWhite() && (x==r+1||x==r+2) && hasEnemyUnit(x, y, piece))
			movable=false;
		else 
			movable=moveCheckNormalPieces(x, y, piece);
		return movable;
	}
	
	public Boolean isMovableTo(int x,int y,Piece piece){  //considers all limitations and decides if a piece is movable to x,y
		Boolean movable=false;
		if(piece.getId()==0)
			movable=moveCheckPawn(x, y, piece);
		else if(piece.getId()==4)
			movable=moveCheckKing(x, y, piece);
		else if(piece.getId()==2)
			movable=rookMoveCheck(x, y, piece);
		else 
			movable=moveCheckNormalPieces(x, y, piece);
		return movable;	
	}
	
	public Boolean rookMoveCheck(int x,int y,Piece piece){
		Piece p=game.getGameBoard().getPieces()[x][y];
		if(hasEnemyUnit(x, y, piece)&&p.getId()==2)
			return false;
		else 
			return moveCheckNormalPieces(x, y, piece);
	}
	
	
	public Boolean moveCheckKing(int x,int y,Piece piece){
		Boolean canMove=true;
		Boolean color=piece.getIsWhite();
		if(color){
			for(int i=0;i<16;i++){
				if(i==4){
					canMove=false;
					break;
				}
				Piece p=game.getbPlayer().getPieces().get(i);
					if(isMovableTo(x, y, p)){
						canMove=false;
						break;
					}
			}	
		}else if(!color){
			for(int i=0;i<16;i++){
				if(i==4){
					canMove=false;
					break;
				}
				Piece p=game.getwPlayer().getPieces().get(i);
					if(isMovableTo(x, y, p)){
						canMove=false;
						break;
					}
			}
		}
		return (canMove && !isOccupied(x, y, piece) && piece.canMoveTo(x, y));
	}
	
	public String whoseTurn(){
		String s;
		if(game.getWinner()!=null)
			s="gg";
		else if(game.getIsWhitesTurn())
			s= "White's turn.";
		else
			s="Black's turn.";
		return s;
	}
	
	public String winner(){
		String s=null;
		if(game.getWinner().getIsWhite())
			s="White won the match!";
		else if(!game.getWinner().getIsWhite())
			s="Black won the match!";
		return s;
	}
	
	public void changeTurn(){
		if(game.getIsWhitesTurn())
			game.setIsWhitesTurn(false);
		else
			game.setIsWhitesTurn(true);
		bui.updateTurnText();
		bui.switchTimer();
	}
	
	public Boolean isBlocked(int x,int y,Piece piece){  //checks if a tile is blocked by friendly or enemy pieces
		Boolean blocked=false;							//for rook,bishop.
		int r=piece.getPosX();
		int c=piece.getPosY();
		
		if(x==r && c>y){
			for(int i=y+1;i<c;i++)
				if(isFull(x, i, piece))
					blocked=true;	
		}else if(x==r && y>c){
			for(int i=c+1;i<y;i++)
				if(isFull(x, i, piece))
					blocked=true;	
		}else if (y==c && x>r){
			for(int i=r+1;i<x;i++)
				if(isFull(i, y, piece))
					blocked=true;
		}else if (y==c && r>x){
			for(int i=x+1;i<r;i++)
				if(isFull(i, y, piece))
					blocked=true;			//block checks for Rook
		}else if((x-r==y-c)||(x-r==c-y)){
			if (r>x && c>y){
				for(int i=x+1,j=y+1;i<r&&j<c;i++,j++)
					if(isFull(i, j, piece))
						blocked=true;
			}else if(x>r && y>c){
				for(int i=r+1,j=c+1;i<x&&j<y;i++,j++)
					if(isFull(i, j, piece))
						blocked=true;
			}else if(x>r && c>y){
				for(int i=r+1,j=c-1;i<x&&j>y;i++,j--)
					if(isFull(i, j, piece))
						blocked=true;
			}else if(r>x && y>c){
				for(int i=r-1,j=c+1;i>x&&j<y;i--,j++)
					if(isFull(i, j, piece))
						blocked=true;					//block checks for bishop
			}
		}
		return blocked;
	}
	
	public void setNewBoard(){
		{
			ArrayList<Piece> wPieces=game.getwPlayer().getPieces();

			setPiece(7, 0, wPieces.get(0));
			setPiece(7, 1, wPieces.get(1));
			setPiece(7, 2, wPieces.get(2));
			setPiece(7, 3, wPieces.get(3));
			setPiece(7, 4, wPieces.get(4));
			setPiece(7, 5, wPieces.get(5));
			setPiece(7, 6, wPieces.get(6));
			setPiece(7, 7, wPieces.get(7));
			for(int i=0;i<8;i++)
				setPiece(6, i, wPieces.get(i+8));
		}{
			ArrayList<Piece> bPieces=game.getbPlayer().getPieces();
			
			setPiece(0, 0, bPieces.get(0));
			setPiece(0, 1, bPieces.get(1));
			setPiece(0, 2, bPieces.get(2));
			setPiece(0, 3, bPieces.get(3));
			setPiece(0, 4, bPieces.get(4));
			setPiece(0, 5, bPieces.get(5));
			setPiece(0, 6, bPieces.get(6));
			setPiece(0, 7, bPieces.get(7));
			for(int i=0;i<8;i++)
				setPiece(1, i, bPieces.get(i+8));
		}
	}
	public void loadBoard(Game game){
		Player w=game.getwPlayer();
		Player b=game.getbPlayer();
		for(int i=0;i<16;i++){
			int r=w.getPieces().get(i).getPosX();
			int c=w.getPieces().get(i).getPosY();
			setPiece(r, c, w.getPieces().get(i));
		}
		for(int i=0;i<16;i++){
			int r=b.getPieces().get(i).getPosX();
			int c=b.getPieces().get(i).getPosY();
			setPiece(r, c, b.getPieces().get(i));
		}
		
	}
	
	public Boolean isOccupied(int x,int y,Piece piece){  //checks if a tile is occupied by a friendly unit
		Boolean occupation=false;
		Piece p=game.getGameBoard().getPieces()[x][y];
		if(p!=null)
		{
			if(p.getIsOccupied()==true && piece.getIsWhite()==p.getIsWhite())
				occupation=true;
		}
		return occupation;
	}
	
	public void checkKing(King king){
		for(int i=0;i<16;i++){
			if(isMovableTo(king.getPosX(),king.getPosY(), getPlayer(!king.getIsWhite()).getPieces().get(i))){
				king.setChecked(true);
				System.out.println(king.getIsWhite()+" checked");
			}	
		}
		if(king.getChecked() && king.getMated()){
			System.out.println(king.getIsWhite()+" lost");
			setWinner(!king.getIsWhite());
		}if(king.getChecked()){
			king.setMated(true);
			king.setChecked(false);
		}else
			king.setMated(false);
	}
	
	public void check(){
		checkKing(game.getbPlayer().getKing());
		checkKing(game.getwPlayer().getKing());
	}
	
	public void setWinner(Boolean color){
		game.setWinner(getPlayer(color));
		
		Player winner=game.getWinner();
		Player loser=getPlayer(!color);
		
		if(winner.getUser()!=null){
			winner.getUser().setXp(winner.getUser().getXp()+10);
			winner.getUser().setGamesWon(winner.getUser().getGamesWon()+1);
		}
		if(loser.getUser()!=null){
			loser.getUser().setXp(loser.getUser().getXp()-5);
			loser.getUser().setGamesLost(loser.getUser().getGamesLost()+1);
		}
		bui.setWinnerText();
		bui.stopTimers();
	}
	
	
	
	public Boolean isFull(int x,int y,Piece piece){     //checks if a tile is occupied by a friendly or enemy piece
		Boolean occupation=false;
		Piece[][] pieces=game.getGameBoard().getPieces();
		if(pieces[x][y]!=null)
		{
			if(pieces[x][y].getIsOccupied()==true)
				occupation=true;
		}
		return occupation;
	}
	
	public Boolean hasEnemyUnit(int x,int y,Piece piece){     //checks if a tile is occupied by an enemy piece
		Boolean occupation=false;
		Piece[][] pieces=game.getGameBoard().getPieces();
		if(pieces[x][y]!=null)
		{
			if(pieces[x][y].getIsOccupied()==true && piece.getIsWhite()!=pieces[x][y].getIsWhite())
				occupation=true;
		}
		return occupation;
	}
	
	public void showTileInfo(Piece piece){
		System.out.println(piece.getClass().getName());
		for(int i=0;i<64;i++){
			int r=i/8;
			int c=i%8;
			System.out.println(i+"-"+"canmove:"+piece.canMoveTo(r,c)
					+" ||not occupied:" +!isOccupied(r, c, piece) +
					" ||not blocked:" + !isBlocked(r, c, piece)+" ||ismovable to:"+isMovableTo(r, c, piece));
		}
		System.out.println("============================================================================");
	}
	
	private Player getPlayer(Boolean color){
		Player playah=null;
		if(color)
			playah=game.getwPlayer();
		else if(!color)
			playah=game.getbPlayer();
		return playah;
	}
	
	public void perfAiMove(){
		ArrayList<Piece>pieces=new ArrayList<>();
		for(int i=0;i<16;i++){
			Piece piece=game.getbPlayer().getPieces().get(i);
			if(numOfPossibleMoves(piece)>0)
				pieces.add(piece);
		}
		Piece rPiece=pieces.get(getRand(pieces.size()-1));
		ArrayList<Integer>moves=getMoveLocations(rPiece);
		int loc=moves.get(getRand(moves.size()-1));
		int r=loc/8;
		int c=loc%8;
		setPieceInHand(rPiece);
		movePiece(r, c, rPiece);
	}
	
	private int getRand(int max){
		int rand=(int)(Math.random()*max);
		return rand;
	}

	public Square getSquare(int r,int c){
		return bui.getSquares().get(r*8+c);
	}

	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public BoardUI getBui() {
		return bui;
	}
	public void setBui(BoardUI bui) {
		this.bui = bui;
	}

	public Piece getPieceInHand() {
		return pieceInHand;
	}

	public void setPieceInHand(Piece pieceInHand) {
		this.pieceInHand = pieceInHand;
	}

	public Boolean getShowMoves() {
		return showMoves;
	}

	public void setShowMoves(Boolean showMoves) {
		this.showMoves = showMoves;
	}

}
