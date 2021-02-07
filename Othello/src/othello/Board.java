package othello;

/*
 * Stores all of the pieces in a 2D array. Handles inserting and adjusting
 * individual pieces.
 * 
 * @author Jonas K
 */
public class Board {
	Piece[][] pieces;
	int[] playerScores;	
	int width;
	int height;
	
	/*
	 * Constructs an empty board.
	 */
	public Board(int w, int h) {
		pieces = new Piece[w][h];
		
		width = w;
		height = h;
		
		playerScores = new int[PTeam.values().length];
	}
	
	/*
	 * @returns the piece on the board in the given direction relative
	 * to the given board coordinates. 
	 */
	Piece getDir(Dir d, int x, int y) {
		Piece ret;
		try {
			switch(d) {
				case EAST:
					ret = pieces[x+1][y];
					break;
				case SOUTHEAST:
					ret = pieces[x+1][y+1];
					break;
				case SOUTH:
					ret = pieces[x][y+1];
					break;
				case SOUTHWEST:
					ret = pieces[x-1][y+1];
					break;
				case WEST:
					ret = pieces[x-1][y];
					break;
				case NORTHWEST:
					ret = pieces[x-1][y-1];
					break;
				case NORTH:
					ret = pieces[x][y-1];
					break;
				case NORTHEAST:
					ret = pieces[x+1][y-1];
					break;
				default:
					ret = null;
					break;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			ret = null;
		}
		
		return ret;
	}
	
	/*
	 * Changes a given piece to a given team and adjusts scores 
	 * accordingly.
	 */
	void flipPiece(Piece piece, PTeam team) {
		playerScores[piece.team.ordinal()]--;
		playerScores[team.ordinal()]++;
		piece.team = team;
	}
	
	/*
	 * Inserts a piece at a given location on the board, adjusts piece 
	 * adjacency and scores accordingly. 
	 */
	void insertPiece(int x, int y, PTeam team) {
		Piece p = new Piece(x, y, team);
		pieces[x][y] = p;
		playerScores[team.ordinal()] ++;
		
		for(Dir d : Dir.values()) {
			Piece adj = getDir(d, x, y);
			p.setDir(d, adj);
			if(adj != null) {
				adj.setOppisite(d, p);
			}
		}
	}
}
