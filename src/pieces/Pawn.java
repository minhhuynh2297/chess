package pieces;
import static chess.Chess.board;
/**
 * The Pawn class extends to the Piece class and creates and manages a Pawn Piece.
 * @author Eric Martz
 * @author Minh Huynh
 *
 */
public class Pawn extends Piece{

	/**
	 * True if Pawn has not yet moved
	 */
	private boolean start;
	/**
	 *  True if Pawn advances two squares, it can be captured En Passant
	 */
	public boolean canEnpassant = false;
	/**
	 * True if Pawn captured another pawn En Passant
	 */
	public boolean didEnpassant = false;

	/**
	 * Creates Pawn Piece with position and color
	 * @param x X position of Pawn
	 * @param y Y position of Pawn
	 * @param color Color of Pawn (Black or White)
	 * @param moved Boolean which indicates if the piece has ever moved
	 */
	public Pawn(int x, int y, String color, boolean moved) {
		super(x, y, color, moved);
		start = true;
	}

	/** moveCheck takes a coordinate and returns true if the move is valid
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if the move is a valid for Pawn
	 */
	@Override
	public boolean moveCheck(int x, int y) {
		//prevents moving backwards
		if(this.color == "White"){
			if(this.y < y){ //White pawns cannot move back
				return false;
			}
		}
		else{
			if(this.y > y ){ //Black pawns cannot move back
				return false;
			}
		}

		//cannot take a piece while moving forwards
		if(this.x == x){
			if(board.getNodeXY(x,y).occupied){ //checks if the spot if occupied
				//System.out.println("cannot take with pawn");
				return false;
			}
		}

		//moves 2 pieces forward on start
		if(this.start == true && this.x == x) {
			if (Math.abs(this.y - y) == 2) { //Check obstruction on double jump.
				if(this.color == "Black"){
					if(board.getNodeXY(this.x,this.y+1).occupied == true){ //Cannot double jump over a piece.
						return false;
					}
					else {
						return true;
					}
				}
				else {
					if (board.getNodeXY(this.x, this.y - 1).occupied == true) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			else if( Math.abs(this.y - y) == 1) { //check occupied above
				return true;
			}
			else { //going up more than 2 on start
				//System.out.println("Invalid Move");
				return false;
			}
		}
		
		else if(this.start == false && this.x == x) { // moving one up
			if (Math.abs(this.y - y) == 1 ) {
				return true;
			}
			else {
				//System.out.println("Invalid Move");
				return false;
			}
		}
		
		else if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1 ) {

				if(board.getNodeXY(x,y).occupied == false && board.getNodeXY(x,this.y).occupied == true) //checks enpassant condition
					{
					if(board.getNodeXY(x,this.y).piece instanceof Pawn){

						if(((Pawn) board.getNodeXY(x,this.y).piece).canEnpassant == true){

							//System.out.println("here");
							didEnpassant = true;
							return true;
						}
					}
					else {
						return false;
					}
				}
				else{
					if(board.getNodeXY(x,y).occupied == true){
						if(board.getNodeXY(x,y).piece.color != this.color){
							return true;
						}

					}
					return false;
				}
			}
		//System.out.println("Invalid Move");
		return false;
		
	}
	/** move takes a coordinate and changes the x y values of Pawn
	 * @param x int X coordinate to move to
	 * @param y int Y coordinate to move to
	 * @return true if move is successful
	 */
	@Override
	public boolean move(int x, int y) {

		if(this.start == true && this.x == x) {
			if (Math.abs(this.y - y) == 2 || Math.abs(this.y - y) == 1 ) {


				if(Math.abs(this.y - y) == 2){ //if a double jump is made enpassant is true.

					canEnpassant = true;
				}

				this.y = y;
				start = false;
				return true;
			}
			else {
				//System.out.println("Invalid Move");
				return false;
			}
		}
		else if(this.start == false && this.x == x) {
			if (Math.abs(this.y - y) == 1 ) {
				this.y = y;
				return true;
			}
			else {
				//System.out.println("Invalid Move");
				return false;
			}
		}
		else {
			if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1 ) {

				if(didEnpassant == true){
					board.getNodeXY(x,this.y).piece = null;
					board.getNodeXY(x,this.y).occupied = false;
					board.getNodeXY(x,this.y).text = "   ";
					board.blackWhite(board.getNodeXY(x,this.y));
				}


				this.x = x;
				this.y = y;
				return true;
			}
			else {
				//System.out.println("Invalid Move");
				return false;
			}
		}
	}

}
