package othello;

/*
 * Controls game logic at a higher level than BoardPanel.
 * 
 * @author Jonas K
 */
public class GameController {
	Board board;
	Move[][] availableMoves; 
	PTeam turn;
	int[] playerScores;
	boolean gameFinished;
	
	/*
	 * Constructs controller and inserts pieces at the starting location. 
	 */
	GameController(Board board) {
		this.board = board;
		turn = PTeam.BLACK;
		playerScores = new int[PTeam.values().length];
		gameFinished = false;
		
		board.insertPiece(3, 3, PTeam.WHITE);
		board.insertPiece(4, 4, PTeam.WHITE);
		board.insertPiece(3, 4, PTeam.BLACK);
		board.insertPiece(4, 3, PTeam.BLACK);
		update();
	}
	
	/*
	 * Attempts to perform the move at given coordinates. @returns true
	 * if a move exists at the coordinates, and false otherwise. 
	 */
	boolean takeTurn(int x, int y) {
		if(availableMoves[x][y] != null) {
			availableMoves[x][y].perform();
			
			PTeam[] teams = PTeam.values();
			turn = teams[(turn.ordinal() + 1)%teams.length];
			update();
			
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Loops over the board and finds valid moves. @returns true if 
	 * moves are found, and false if there are no moves.
	 */
	private boolean findMoves() {
		availableMoves = new Move[board.width][board.height];
		boolean canMove = false;
		
		for(int i = 0; i < board.width; i++) {
			for(int j = 0; j < board.height; j++) {
				Move move = Move.validMove(i,j, turn, board);
				if(move != null) {
					canMove = true;
					availableMoves[i][j] = move;
				}
			}
		}
		
		if(canMove) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Just gets the scores from the board. (This should probably not 
	 * exists, but no point in changing it now) 
	 */
	private void getScores() {
		playerScores = board.playerScores;
	}
	
	/*
	 * Finds moves, updates scores, and sets gameFinished according to
	 * the return of findMoves.
	 */
	void update() {
		boolean finished = findMoves();
		if(!finished) {
			gameFinished = true;
		}
		getScores();
	}
}
