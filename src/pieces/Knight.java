package pieces;

/**
 * The Knight class extends to the Piece class and creates and manages a Knight Piece.
 * @author Eric Martz
 * @author Minh Huynh
 *
 */
public class Knight extends Piece {

	/**
	 * Creates Knight Piece with position and color
	 * @param x X position of Knight
	 * @param y Y position of Knight
	 * @param color Color of Knight (Black or White)
	 * @param moved Boolean which indicates if the piece has ever moved
	 */

	public Knight(int x, int y, String color, boolean moved) {
		super(x, y, color, moved);
	}

	/** moveCheck takes a coordinate and returns true if the move is valid
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if the move is a valid for Knight
	 */
	@Override
	public boolean moveCheck(int x, int y) {
		if (Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) {
			return true;
		}
		else if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 2) {
			return true;
		}
		else {
			//System.out.println("Invalid Input");
			return false;
		}
	}

	/** move takes a coordinate and changes the x y values of Knight
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if move is successful
	 */
	
	@Override
	public boolean move(int x, int y) {
		if (Math.abs(this.x - x) == 2 && Math.abs(this.y - y) == 1) {
			this.x = x;
			this.y = y;
			return true;
		}
		else if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 2) {
			this.x = x;
			this.y = y;
			return true;
		}
		else {
			//System.out.println("Invalid Input");
			return false;
		}
	}

	/** promote allows a Pawn to be promoted into a Knight
	 * @param pawn Pawn Piece to be promoted into a Knight
	 * @return New Knight Piece to replace Pawn
	 */

	@Override
	public Knight promote(Piece pawn) {
		Knight promoted = new Knight (pawn.x, pawn.y, pawn.color, true);
		return promoted;
	}
}
