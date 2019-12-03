package pieces;

//import static chess.Chess.board;
/** The Bishop class extends to the Piece class and creates and manages a Bishop Piece.
 * @author Eric Martz
 * @author Minh Huynh
 */
public class Bishop extends Piece {

	/**
	 * Creates Bishop Piece with position and color
	 * @param x X position of Bishop
	 * @param y Y position of Bishop
	 * @param color Color of Bishop (Black or White)
	 * @param moved Boolean which indicates if the piece has ever moved
	 */
	public Bishop(int x, int y, String color, Boolean moved) {
		super(x, y, color, moved);
	}

	/** moveCheck takes a coordinate and returns true if the move is valid
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if the move is a valid for Bishop
	 */
	@Override
 	public boolean moveCheck(int x, int y) {
		return(checkDiag(x,y));
 	}

	/** move takes a coordinate and changes the x y values of Bishop
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if move is successful
	 */
 	@Override
 	public boolean move(int x, int y) {
		return(diag(x,y));
 	}

	/** promote allows a Pawn to be promoted into a Bishop
	 * @param pawn Pawn Piece to be promoted into a Bishop
	 * @return New Bishop Piece to replace Pawn
	 */
	@Override
	public Bishop promote(Piece pawn) {
		Bishop promoted = new Bishop (pawn.x, pawn.y, pawn.color, true);
		return promoted;
	}
}
