package pieces;

/**
 * The Rook class extends to the Piece class and creates and manages a Rook Piece.
 * @author Eric Martz
 * @author Minh Huynh
 *
 */
public class Rook extends Piece {

	/**
	 * Creates Rook Piece with position and color
	 * @param x X position of Rook
	 * @param y Y position of Rook
	 * @param color Color of Rook (Black or White)
	 * @param moved Boolean which indicates if the piece has ever moved
	 */
	public Rook(int x, int y, String color, boolean moved) {
		super(x, y, color, moved);
		this.moved = moved;
	}

	/** moveCheck takes a coordinate and returns true if the move is valid
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if the move is a valid for Rook
	 */
	@Override
	public boolean moveCheck(int x, int y) {
		return checkAcross(x, y);
	}

	/** move takes a coordinate and changes the x y values of Rook
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if move is successful
	 */
	@Override
	public boolean move(int x, int y) {
		if(across(x,y)) {
			this.moved = true;
			return true;
		}
		else {
			return false;
		}
	}

	/** promote allows a Pawn to be promoted into a Rook
	 * @param pawn Pawn Piece to be promoted into a Rook
	 * @return New Rook Piece to replace Pawn
	 */
	@Override
	public Rook promote(Piece pawn) {
		Rook promoted = new Rook (pawn.x, pawn.y, pawn.color, true);
		return promoted;
	}
	
}
