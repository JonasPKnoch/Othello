package othello;

import java.util.ArrayList;

/*
 * Controls and individual, potential move. Determines if a given location on the board is valid via a static method.
 */
public class Move {
	private int startX;
	private int startY;
	private PTeam team;
	private Board board;
	private ArrayList<Piece> flipPieces;
	
	/*
	 * Just assigns all variables.
	 */
	private Move(int startX, int startY, PTeam team, Board board, ArrayList<Piece> flipPieces) {
		this.startX = startX;
		this.startY = startY;
		this.team = team;
		this.board = board;
		this.flipPieces = flipPieces;
	}
	
	/*
	 * Inserts a new piece and flips all affected pieces. 
	 */
	void perform() {
		board.insertPiece(startX, startY, team);
		for(Piece p: flipPieces) {
			board.flipPiece(p, team);
		}
	}
	
	/*
	 * Called by valid moves, and recursively. Checks in a given 
	 * direction on the board through pieces, accumulates valid pieces 
	 * in list. @returns all affected pieces or null if the given 
	 * direction is not valid. 
	 */
	private static ArrayList<Piece> checkDir(Piece piece, Dir dir, PTeam team) {
		ArrayList<Piece> ret = new ArrayList<Piece>();
		ret.add(piece);
		
		Piece adj = piece.getDir(dir);
		if(adj != null) {
			if(adj.team != team) {
				ArrayList<Piece> check = checkDir(adj, dir, team);
				if(check != null) {
					ret.addAll(check);
				} else {
					return null;
				}
			}
			
			return ret;
		} else {
			return null;
		}
	}
	
	/*
	 * @returns a valid move if one exists, and null otherwise. The 
	 * only public way to get a Move.
	 */
	static Move validMove(int x, int y, PTeam team, Board board) {
		boolean valid = false;
		ArrayList<Piece> flippedPieces = new ArrayList<Piece>();
		
		if(board.pieces[x][y] == null) {
			for(Dir d: Dir.values()) {
				Piece p = board.getDir(d, x, y);
				if(p != null && p.team != team) {
					ArrayList<Piece> check = checkDir(p, d, team);
					
					if(check != null) {
						valid = true;
						flippedPieces.addAll(check);
					}
				}
			}
		} else {
			return null;
		}
		
		if(!valid) {
			return null;
		} else {
			return new Move(x, y, team, board, flippedPieces);
		}
	}
}
