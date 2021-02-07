package othello;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

/*
 * Loads and stores all icons used in the game.
 * 
 * @author Jonas K
 */
public class LoadedImages {
	final ImageIcon BOARDSQUAREIMAGE;
	final ImageIcon BLACKPIECEIMAGE;
	final ImageIcon WHITEPIECEIMAGE;
	final ImageIcon MOVEIMAGE;
	final ImageIcon TITLEIMAGE;
	final ImageIcon GAME_FINISHED_IMAGE;
	final ImageIcon APP_ICON;
	
	/*
	 * Loads images into icons, and assigns their respective constants.
	 */
	LoadedImages() {
		BOARDSQUAREIMAGE = new ImageIcon("src/Images/BoardSquare.png");
		BLACKPIECEIMAGE = new ImageIcon("src/Images/BlackPiece.png");
		WHITEPIECEIMAGE = new ImageIcon("src/Images/WhitePiece.png");
		MOVEIMAGE = new ImageIcon("src/Images/Move.png");
		TITLEIMAGE = new ImageIcon("src/Images/OthelloTitle.png");
		GAME_FINISHED_IMAGE = new ImageIcon("src/Images/GameFinished.png");
		APP_ICON = new ImageIcon("src/Images/AppIcon.png");
	}
}
