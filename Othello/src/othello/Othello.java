package othello;

/*
 * Just initializes all needed components in the main method.
 * 
 * @author Jonas K
 */
class Othello  {
	
	public static void main(String[] args) {
		Board board = new Board(8, 8);
		GameController controller = new GameController(board);
		LoadedImages images = new LoadedImages();
		
		OthelloFrame frame = new OthelloFrame(controller, images);
	}
}
