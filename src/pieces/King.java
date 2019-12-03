package pieces;

/** The Bishop class extends to the Piece class and creates and manages a King Piece.
 * @author Eric Martz
 * @author Minh Huynh
 */
public class King extends Piece {
	/**
	 * Creates King Piece with position and color
	 * @param x X position of King
	 * @param y Y position of King
	 * @param color Color of King (Black or White)
	 * @param moved Boolean which indicates if the piece has ever moved
	 */
	public King(int x, int y, String color, Boolean moved) {
		super(x, y, color, moved);
	}

	/** moveCheck takes a coordinate and returns true if the move is valid
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if the move is a valid for King
	 */
	@Override 
	public boolean moveCheck(int x, int y) {
		if (Math.abs(this.x - x) == 1 || Math.abs(this.y - y) == 1 ) {
			return true;
		}
		else {
			//System.out.println("Invalid Move");
			return false;
		}
	}

	/** move takes a coordinate and changes the x y values of King
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if move is successful
	 */
	@Override
	public boolean move(int x, int y) {
		if (Math.abs(this.x - x) == 1 || Math.abs(this.y - y) == 1 ) {
			this.x = x;
			this.y = y;
			this.moved = true;
			return true;
		}
		else {
			//System.out.println("Invalid Move");
			return false;
		}
	}
	
}
