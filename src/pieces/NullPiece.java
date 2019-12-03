package pieces;
/**
 * The NullPiece class extends to the Piece class and creates and manages a NullPiece Piece.
 * NullPiece is for retuning null values safely.
 * @author Eric Martz
 * @author Minh Huynh
 *
 */
public class NullPiece extends Piece {

    /**
     * Creates NullPiece Piece with position and color
     * @param x X position of NullPiece
     * @param y Y position of NullPiece
     * @param color Color of NullPiece
     * @param moved Boolean which indicates if the piece has ever moved
     */
    public NullPiece(int x, int y, String color, boolean moved) {
        super(x, y, color, moved);
    }


}
