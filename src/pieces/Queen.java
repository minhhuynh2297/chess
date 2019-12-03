package pieces;

/**
 * The Queen class extends to the Piece class and creates and manages a Queen Piece.
 * @author Eric Martz
 * @author Minh Huynh
 *
 */
public class Queen extends Piece {

	/**
	 * Creates Queen Piece with position and color
	 * @param x X position of Queen
	 * @param y Y position of Queen
	 * @param color Color of Queen (Black or White)
	 * @param moved Boolean which indicates if the piece has ever moved
	 */
	public Queen(int x, int y, String color, Boolean moved) {
		super(x, y, color, moved);
	}

	/** moveCheck takes a coordinate and returns true if the move is valid
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if the move is a valid for Queen
	 */
	@Override
	public boolean moveCheck(int x, int y) {
		if(checkAcross(x, y) || checkDiag(x, y))
			return true;
		else
			return false;
	}

	/** move takes a coordinate and changes the x y values of Queen
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if move is successful
	 */
	@Override
	public boolean move(int x, int y) {
		//Queen has aspects of both a Rook and a Queen
		if(across(x,y) || diag(x,y))
			return true;
		else
			return false;
	}

	/** promote allows a Pawn to be promoted into a Queen
	 * @param pawn Pawn Piece to be promoted into a Queen
	 * @return New Queen Piece to replace Pawn
	 */
	@Override
	public Queen promote(Piece pawn) {
		Queen promoted = new Queen (pawn.x, pawn.y, pawn.color, true);
		return promoted;
	}
}
