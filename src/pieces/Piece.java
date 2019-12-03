package pieces;
import static chess.Chess.board;

/**
 * Piece class is the parent class of all Pieces
 * @author Eric Martz
 * @author Minh Huynh
 */
public class Piece {

	/**
	 * X Value position coordinate
	 */
	public int x;
	/**
	 * Y Value position coordinate
	 */
	public int y;
	/**
	 * Tells if Piece has moved.
	 */
	public boolean moved;
	/**
	 * Color of Piece (Black or White)
	 */
	public String color;

	/**
	 * Piece constructor for all Pieces
	 * @param x X Position
	 * @param y Y Position
	 * @param color Color of Piece (Black or White)
	 * @param moved Tells if Piece has moved.
	 */
	public Piece(int x, int y, String color, boolean moved) {
		this.x = x;
		this.y = y;
		this.color = color; 
		this.moved = moved;
	}	
	//no change in x y

    /**
     * Checks for obstructions diagonally from piece to desired spot
     * @param x X Position
     * @param y Y Position
     * @return True if there are no obstructions (valid move)
     */
	public boolean checkDiag(int x, int y){
		if (Math.abs(this.x - x) < 8 && Math.abs(this.y - y) < 8 && (Math.abs(this.x - x) == Math.abs(this.y - y))) {

			int dirX;
			int dirY;
			if(this.x < x)
				dirX = 1;
			else
				dirX = -1;

			if(this.y < y)
				dirY = 1;
			else
				dirY = -1;

			int tempX = this.x + dirX;
			int tempY = this.y + dirY;

			for(int eachSpot = 0; eachSpot < Math.abs(x-this.x)-1; eachSpot++){
				if(board.getNodeXY(tempX,tempY).occupied == true){
					////System.out.println("Invalid Move OBS");
					return false;
				}
				tempX = this.x+dirX;
				tempY = this.y + dirY;
				eachSpot++;
			}
			return true;
		}
		else {
			////System.out.println("Invalid Move");
			return false;
		}
	}

	/** Moves Piece diagonally
	 * @param x X Position
	 * @param y Y Position
	 * @return True if valid move
	 */
	//change in x y
	public boolean diag(int x, int y){


		if (Math.abs(this.x - x) < 8 && Math.abs(this.y - y) < 8 && (Math.abs(this.x - x) == Math.abs(this.y - y))) {

			int dirX;
			int dirY;
			if(this.x < x)
				dirX = 1;
			else
				dirX = -1;

			if(this.y < y)
				dirY = 1;
			else
				dirY = -1;

			int tempX = this.x + dirX;
			int tempY = this.y + dirY;

			for(int eachSpot = 0; eachSpot < Math.abs(x-this.x)-1; eachSpot++){
				if(board.getNodeXY(tempX,tempY).occupied == true){
					//System.out.println("Invalid Move OBS");
					return false;
				}
				tempX = this.x+dirX;
				tempY = this.y + dirY;
				eachSpot++;
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

	/**
	 * Checks for obstructions horizontally and vertically from piece to desired spot
	 * @param x X Position
	 * @param y Y Position
	 * @return True if there are no obstructions (valid move)
	 */
	//no change in x y
	public boolean checkAcross(int x, int y){

		if (Math.abs(this.x - x) < 8 && this.y == y) { //moving on X axis

			int xDir = 0;

			if(this.x > x)
				xDir = -1; //moving down x axis
			else
				xDir = 1; //moving up x axis


			for(int xCheck = 1; xCheck < Math.abs(this.x - x);xCheck++){
				if(board.getNodeXY(this.x+(xCheck*xDir),this.y).occupied == true){
					//System.out.println("Invalid Move OBS");
					return false;
				}
			}
			return true;
		}
		else if (Math.abs(this.y - y) < 8 && this.x == x) { //moving on Y axis

			int yDir = 0;

			if(this.y > y)
				yDir = -1; //moving down y axis
			else
				yDir = 1; //moving up y axis

			for(int yCheck = 1; yCheck < Math.abs(this.y - y);yCheck++){
				if(board.getNodeXY(this.x,this.y+(yCheck*yDir)).occupied == true){
					//System.out.println("Invalid Move OBS");
					return false;
				}
			}
			return true;
		}
		else {
			//System.out.println("Invalid Move");
			return false;
		}

	}

	/**
	 * Moves piece across
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @return True if move is valid
	 */
	//change in x y
	public boolean across(int x, int y){

		if (Math.abs(this.x - x) < 8 && this.y == y) { //moving on X axis

			int xDir = 0;

			if(this.x > x)
				xDir = -1; //moving down x axis
			else
				xDir = 1; //moving up x axis


			for(int xCheck = 1; xCheck < Math.abs(this.x - x);xCheck++){
				if(board.getNodeXY(this.x+(xCheck*xDir),this.y).occupied == true){
					//System.out.println("Invalid Move OBS");
					return false;
				}
			}
			this.x = x;
			return true;
		}
		else if (Math.abs(this.y - y) < 8 && this.x == x) { //moving on Y axis

			int yDir = 0;

			if(this.y > y)
				yDir = -1; //moving down y axis
			else
				yDir = 1; //moving up y axis

			for(int yCheck = 1; yCheck < Math.abs(this.y - y);yCheck++){
				if(board.getNodeXY(this.x,this.y+(yCheck*yDir)).occupied == true){
					//System.out.println("Invalid Move OBS");
					return false;
				}
			}
			this.y = y;
			return true;
		}
		else {
			//System.out.println("Invalid Move");
			return false;
		}

	}

	/**
	 * Checks if move is valid for Piece
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @return True if move is valid
	 */
	public boolean moveCheck(int x, int y) {
		return false; 
	}

	/**
	 * Moves piece to X Y Coordinate
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @return True if move is successful
	 */
	public boolean move(int x, int y) {
		return false;
	}

	/**
	 * Promotes piece to a certain piece
	 * @param piece Piece to promote to
	 * @return New piece that is generated on promotion
	 */
	public Piece promote(Piece piece) {
		Piece promoted = new Piece(piece.x, piece.y, piece.color, true);
		
		return promoted;
	}

	
}
