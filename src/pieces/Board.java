package pieces;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** The Board class hosts all the pieces and Nodes where the game is played
 * @author Eric Martz
 * @author Minh Huynh
 */
public class Board {

	/**
	 * Nodes which represent spots on the board and can hold Pieces
	 */
	public class Node {
		public boolean occupied;
		public boolean safe;
		public Piece piece; 
		public int x;
		public int y;
		public String text;
		public String node;
		public Node next;
		Node prev;

		/**
		 * Node constructor to create a Node
		 * @param occupied True if Node is filled with a piece
		 * @param safe True if Node is not under threat by attacking piece
		 * @param piece Piece object inside of Node
		 * @param x X Coordinate
		 * @param y Y Coordinate
		 * @param text Text to display for printing of board
		 * @param node String label which is used to get Node from player command (i.e a6)
		 * @param next Points to next Node for traversal
		 */
		public Node(boolean occupied, boolean safe, Piece piece, int x, int y, String text, String node, Node next) {
			this.occupied = occupied;
			this.safe = safe;
			this.piece = piece;
			this.x = x;
			this.y = y; 
			this.text = text;
			this.next = next;
			this.node = node;
		}

		/**
		 * Creates a deep copy of a Node for testing
		 * @param otherNode Node to be copied
		 */
		public Node(Node otherNode){
			this.text = otherNode.text;
			this.x = otherNode.x;
			this.y = otherNode.y;
			this.occupied = otherNode.occupied;
			this.safe = otherNode.safe;
			this.next = otherNode.next;
			this.piece = otherNode.piece;
		}
	}

	/**
	 * Represents a spot on the chess board
	 */
	public static Node  a8, a7, a6, a5, a4, a3, a2, a1, a0,
					    b8, b7, b6, b5, b4, b3, b2, b1, b0,
					    c8, c7, c6, c5, c4, c3, c2, c1, c0,
					    d8, d7, d6, d5, d4, d3, d2, d1, d0,
					    e8, e7, e6, e5, e4, e3, e2, e1, e0,
					    f8, f7, f6, f5, f4, f3, f2, f1, f0,
					    g8, g7, g6, g5, g4, g3, g2, g1, g0,
					    h8, h7, h6, h5, h4, h3, h2, h1, h0,
					    i8, i7, i6, i5, i4, i3, i2, i1, i0, nullNode;

	/**
	 * Initializes all Nodes with appropriate pieces for start of game
	 */
	public Board() {

		//nullNode is used with getNode and GetPiece
		NullPiece nullPiece = new NullPiece(100,100,"null",false);
		nullNode = new Node(true,true,nullPiece,100,100,"null","null",null);

		//Row 8
		Rook   bR  = new Rook  (0,0, "Black", false);a8 = new Node(true, true, bR,  0, 0, "bR ", "a8", null); 
		Knight bN  = new Knight(1,0, "Black", false);b8 = new Node(true, true, bN,  1, 0, "bN ", "b8", null); a8.next = b8;
		Bishop bB  = new Bishop(2,0, "Black", false);c8 = new Node(true, true, bB,  2, 0, "bB ", "c8", null); b8.next = c8;
		Queen  bQ  = new Queen (3,0, "Black", false);d8 = new Node(true, true, bQ,  3, 0, "bQ ", "d8", null); c8.next = d8; 
		King   bK  = new King  (4,0, "Black", false);e8 = new Node(true, true, bK,  4, 0, "bK ", "e8", null); d8.next = e8;
		Bishop bB2 = new Bishop(5,0, "Black", false);f8 = new Node(true, true, bB2, 5, 0, "bB ", "f8", null); e8.next = f8;
		Knight bN2 = new Knight(6,0, "Black", false);g8 = new Node(true, true, bN2, 6, 0, "bN ", "g8", null); f8.next = g8; 
		Rook   bR2 = new Rook  (7,0, "Black", false);h8 = new Node(true, true, bR2, 7, 0, "bR ", "h8", null); g8.next = h8; 
		i8 = new Node(false, false, null, 8, 0, "8\n", "i8", h8); h8.next = i8;
		//Row 7
		Pawn bp1 = new Pawn(0,1, "Black", false);a7 = new Node(true, true, bp1, 0, 1,"bp ", "a7", null); i8.next = a7;
		Pawn bp2 = new Pawn(1,1, "Black", false);b7 = new Node(true, true, bp2, 1, 1,"bp ", "b7", null); a7.next = b7; 
		Pawn bp3 = new Pawn(2,1, "Black", false);c7 = new Node(true, true, bp3, 2, 1,"bp ", "c7", null); b7.next = c7;
		Pawn bp4 = new Pawn(3,1, "Black", false);d7 = new Node(true, true, bp4, 3, 1,"bp ", "d7", null); c7.next = d7; 
		Pawn bp5 = new Pawn(4,1, "Black", false);e7 = new Node(true, true, bp5, 4, 1,"bp ", "e7", null); d7.next = e7; 
		Pawn bp6 = new Pawn(5,1, "Black", false);f7 = new Node(true, true, bp6, 5, 1,"bp ", "f7", null); e7.next = f7; 
		Pawn bp7 = new Pawn(6,1, "Black", false);g7 = new Node(true, true, bp7, 6, 1,"bp ", "g7", null); f7.next = g7;
		Pawn bp8 = new Pawn(7,1, "Black", false);h7 = new Node(true, true, bp8, 7, 1,"bp ", "h7", null); g7.next = h7; 
		i7 = new Node(false, false, null, 8, 1, "7\n", "i7", null); h7.next = i7;
		//Row 6
		a6 = new Node(false, true, null, 0, 2, "   ", "a6", null); i7.next = a6;	
		b6 = new Node(false, true, null, 1, 2, "## ", "b6", null); a6.next = b6;	
		c6 = new Node(false, true, null, 2, 2, "   ", "c6", null); b6.next = c6;	
		d6 = new Node(false, true, null, 3, 2, "## ", "d6", null); c6.next = d6;	
		e6 = new Node(false, true, null, 4, 2, "   ", "e6", null); d6.next = e6;	
		f6 = new Node(false, true, null, 5, 2, "## ", "f6", null); e6.next = f6;	
		g6= new Node(false,  true, null, 6, 2, "   ", "g6", null); f6.next = g6;	
		h6 = new Node(false, true, null, 7, 2, "## ", "h6", null); g6.next = h6;	
		i6= new Node(false,  true, null, 8, 2, "6\n", "i6", null); h6.next = i6;
		//Row 5
		a5 = new Node(false, true, null, 0, 3, "## ", "a5", null); i6.next = a5;	
		b5 = new Node(false, true, null, 1, 3, "   ", "b5", null); a5.next = b5;	
		c5 = new Node(false, true, null, 2, 3, "## ", "c5", null); b5.next = c5;	
		d5 = new Node(false, true, null, 3, 3, "   ", "d5", null); c5.next = d5;	
		e5 = new Node(false, true, null, 4, 3, "## ", "e5", null); d5.next = e5;	
		f5 = new Node(false, true, null, 5, 3, "   ", "f5", null); e5.next = f5;	
		g5 = new Node(false, true, null, 6, 3, "## ", "g5", null); f5.next = g5;	
		h5 = new Node(false, true, null, 7, 3, "   ", "h5", null); g5.next = h5;	
		i5 = new Node(false, true, null, 8, 3, "5\n" , "i5", null); h5.next = i5;	
		//Row 4
		a4 = new Node(false, true, null, 0, 4, "   ", "a4", null); i5.next = a4;		
		b4 = new Node(false, true, null, 1, 4, "## ", "b4", null); a4.next = b4;		
		c4 = new Node(false, true, null, 2, 4, "   ", "c4", null); b4.next = c4;		
		d4 = new Node(false, true, null, 3, 4, "## ", "d4", null); c4.next = d4;		
		e4 = new Node(false, true, null, 4, 4, "   ", "e4", null); d4.next = e4;		
		f4 = new Node(false, true, null, 5, 4, "## ", "f4", null); e4.next = f4;		
		g4 = new Node(false, true, null, 6, 4, "   ", "g4", null); f4.next = g4;		
		h4 = new Node(false, true, null, 7, 4, "## ", "h4", null); g4.next = h4;		
		i4 = new Node(false, true, null, 8, 4, "4\n", "i4", null);  h4.next = i4;		
		//Row 
		a3 = new Node(false, true, null, 0, 5, "## ", "a3", null); i4.next = a3;		
		b3 = new Node(false, true, null, 1, 5, "   ", "b3", null); a3.next = b3;		
		c3 = new Node(false, true, null, 2, 5, "## ", "c3", null); b3.next = c3;		
		d3 = new Node(false, true, null, 3, 5, "   ", "d3", null); c3.next = d3;		
		e3 = new Node(false, true, null, 4, 5, "## ", "e3", null); d3.next = e3;		
		f3 = new Node(false, true, null, 5, 5, "   ", "f3", null); e3.next = f3;		
		g3 = new Node(false, true, null, 6, 5, "## ", "g3", null); f3.next = g3;		
		h3 = new Node(false, true, null, 7, 5, "   ", "h3", null); g3.next = h3;		
		i3 = new Node(false, true, null, 8, 5, "3\n", "i3", null);  h3.next = i3;		
		//Row 2
		Pawn wp1 = new Pawn(0, 6, "White", false); a2 = new Node(true, true, wp1, 0, 6,"wp ", "a2", null); i3.next = a2;	
		Pawn wp2 = new Pawn(1, 6, "White", false); b2 = new Node(true, true, wp2, 1, 6,"wp ", "b2", null); a2.next = b2;	 
		Pawn wp3 = new Pawn(2, 6, "White", false); c2 = new Node(true, true, wp3, 2, 6,"wp ", "c2", null); b2.next = c2;	
		Pawn wp4 = new Pawn(3, 6, "White", false); d2 = new Node(true, true, wp4, 3, 6,"wp ", "d2", null); c2.next = d2;	 
		Pawn wp5 = new Pawn(4, 6, "White", false); e2 = new Node(true, true, wp5, 4, 6,"wp ", "e2", null); d2.next = e2;	 
		Pawn wp6 = new Pawn(5, 6, "White", false); f2 = new Node(true, true, wp6, 5, 6,"wp ", "f2", null); e2.next = f2;	 
		Pawn wp7 = new Pawn(6, 6, "White", false); g2 = new Node(true, true, wp7, 6, 6,"wp ", "g2", null); f2.next = g2;	
		Pawn wp8 = new Pawn(7, 6, "White", false); h2 = new Node(true, true, wp8, 7, 6,"wp ", "h2", null); g2.next = h2;	 
		i2= new Node(false, true, null, 0, 0, "2\n", "i2", null); h2.next = i2;		
		//Row 1
		Rook   wR  = new Rook  (0, 7, "White", false); a1 = new Node(true, true, wR,  0, 7, "wR ", "a1", null); i2.next = a1;	
		Knight wN  = new Knight(1, 7, "White", false); b1 = new Node(true, true, wN,  1, 7, "wN ", "b1", null); a1.next = b1;	 
		Bishop wB  = new Bishop(2, 7, "White", false); c1 = new Node(true, true, wB,  2, 7, "wB ", "c1", null); b1.next = c1;	
		Queen  wQ  = new Queen (3, 7, "White", false); d1 = new Node(true, true, wQ,  3, 7, "wQ ", "d1", null); c1.next = d1;	
		King   wK  = new King  (4, 7, "White", false); e1 = new Node(true, true, wK,  4, 7, "wK ", "e1", null); d1.next = e1;	
		Bishop wB2 = new Bishop(5, 7, "White", false); f1 = new Node(true, true, wB2, 5, 7, "wB ", "f1", null); e1.next = f1;	
		Knight wN2 = new Knight(6, 7, "White", false); g1 = new Node(true, true, wN2, 6, 7, "wN ", "g1", null); f1.next = g1;	 
		Rook   wR2 = new Rook  (7, 7, "White", false); h1 = new Node(true, true, wR2, 7, 7, "wR ", "h1", null); g1.next = h1;	 
		i1  = new Node(false, true, null, 0, 0, "1\n", "i1", null); h1.next = i1;	
		//Row 0
		a0 = new Node(false, true, null, 0, 8, " a ", "a0", null); i1.next = a0;		
		b0 = new Node(false, true, null, 1, 8, " b ", "b0", null); a0.next = b0;		
		c0 = new Node(false, true, null, 2, 8, " c ", "c0", null); b0.next = c0;		
		d0 = new Node(false, true, null, 3, 8, " d ", "d0", null); c0.next = d0;		
		e0 = new Node(false, true, null, 4, 8, " e ", "e0", null); d0.next = e0;		
		f0 = new Node(false, true, null, 5, 8, " f ", "f0", null); e0.next = f0;		
		g0 = new Node(false, true, null, 6, 8, " g ", "g0", null); f0.next = g0;		
		h0 = new Node(false, true, null, 7, 8, " h ", "h0", null); g0.next = h0;		
		i0 = new Node(false, true, null, 8, 8, "", "i0", null); h0.next = i0;	
	}

	/**
	 * Creates the checkered pattern on the board
	 * @param empty Empty Node to display either a black or white space
	 */
	public void blackWhite(Node empty) {
		char[] text = empty.node.toCharArray();
		if(text[0] == 'a' || text[0] == 'c' || text[0] == 'e' || text[0] == 'g' ) {
			if(Character.getNumericValue(text[1]) % 2 == 0 ) {
				empty.text = "   ";
			}
			else {
				empty.text = "## ";
			}
		}
		if(text[0] == 'b' || text[0] == 'd' || text[0] == 'f' || text[0] == 'h' ) {
			if(Character.getNumericValue(text[1]) % 2 == 0 ) {
				empty.text = "## ";
			}
			else {
				empty.text = "   ";
			}
		}
		empty.occupied = false;
		empty.piece = null;
	}

	/**
	 * Searches Nodes for matching Node from String input
	 * @param node String value to find
	 * @param head Starting node to search from
	 * @return Node matching String node
	 */
	//takes a String input and finds the appropriate node
	public static Node stringNode(String node, Node head) {
		Node current = head;
		while(current.next!=null) {
			
			if(current.node.equals(node)) {
				return current;
			}
			current = current.next;
		}
		if(current.node.equals(node)) {
			return current;
		}
		return current;
	}

	/**
	 * Checks if a move is valid according to piece's movement and if it causes a check
	 * @param start Node to be moved from
	 * @param end Node to be moved to
	 * @return True if move is valid
	 */
	public boolean moveCheck(Node start, Node end) {

		if(end.x > 7 || end.x < 0 || end.y > 7 || end.y < 0){
			return false;
		}

		if(start.occupied == false) {
			//System.out.println("Invalid Move");
			return false;
		}
		if(end.occupied == true) {
			if(start.piece.color == end.piece.color ) {
				//System.out.println("Invalid Move");
				return false;
			}
		}
		if(!(start.text.contains("wK") || start.text.contains("bK"))) { //if not king, check normally
			if (start.piece.moveCheck(end.x, end.y)) { //Checks if move is legal
				if(testCheck(start,end)){ //Checks if move results in a check.
					//System.out.println("Cannot move, puts king into check");
					return false;
				}
				else {
					return true;
				}
			}
			else{
				return false;
			}
		}
		else if(start.piece.moved == false && 
				(end.node.contains("c1") || end.node.contains("g1") || end.node.contains("c8") || end.node.contains("g8"))) { //trying to castle
			return true; //return true to be checked in KingRook for conditions
			
		}
		else { //is king, but is not trying to castle
			if (start.piece.moveCheck(end.x, end.y)) { //Checks if move is legal
				if(testCheck(start,end)){ //Checks if move results in a check.
					//System.out.println("Cannot move, puts king into check");
					return false;
				}
				else {
					return true;
				}
			}
			else{
				return false;
			}
		}
		
	}

	/**
	 * Determines castling validity between pieces
	 * @param start Node to start from
	 * @param end Node to move to
	 * @return True if castle move is valid
	 */
	public boolean castle(Node start, Node end) {
		while(!start.equals(end)) {
			if (start.safe == false || start.occupied == true) {
				return false;
			}
			start=start.next;
		}
		return true;
	}

	/**
	 * Moves node on the board
	 * @param start Node to be moved from
	 * @param end Node to be moved to
	 */
	public void move(Node start, Node end) {//move pieces

			if(start.piece instanceof Pawn){
				start.piece.move(end.x,end.y);
			}

			start.occupied = false;
			end.occupied = true;
			end.text = start.text;		
			end.piece = start.piece;
			end.piece.x = end.x;
			end.piece.y = end.y;
			blackWhite(start);
	}

	/**
	 * Move for king movement for castling
	 * @param king Node which contains king piece
	 * @param end Node to move king into
	 */
	public void kingMove(Node king, Node end) {
		end.occupied = true;
		end.text = king.text;
		end.piece = king.piece;
		end.piece.x = end.x;
		end.piece.y = end.y;		
		end.piece.moved = true;
		blackWhite(king);
	}

	/**
	 * Move for rook movement for castling
	 * @param rook Node which contains rook piece
	 * @param end Node to move rook into
	 */
	public void rookMove(Node rook, Node end) {
		end.occupied = true;
		end.text = rook.text;
		end.piece = rook.piece;
		end.piece.x = end.x;
		end.piece.y = end.y;		
		end.piece.moved = true;
		blackWhite(rook);
	}

	/**
	 * Prints text in all nodes in board
	 * @param head First node in board
	 */
	public void printBoard(Node head) {
		while(head.next!=null) {
			System.out.print(head.text);
			head=head.next;
		}
		System.out.println('\n');
	}

	/**
	 * Prints is occupied in all nodes in board (for testing)
	 * @param head First node in board
	 */
	public void printOccupied(Node head) { // temporary (delete later)
		while(head.next!=null){
			if(head.occupied == true) {
				System.out.print("x ");
			}
			else {
				if(head.node.contains("i")){
					//System.out.println();
				}
				else if( head.node.contains("0") ) {
					
				}
				else {
				System.out.print("o ");
				}
			}
			head=head.next;
		}
		if(head.occupied == true) {
			System.out.print("x ");
		}
		else {
			if(head.node.contains("i")) {
				//System.out.println();
			}
			else {
			//System.out.println("o ");
			}
		}
	}

	/**
	 * Returns the node which contains a king piece of the specified color
	 * @param color Color of the king piece
	 * @return Node which contains king of such color
	 */
	public Node getKing(String color){

		//gets Node that contains white or black king

		Node head = a8;

		while(head.next != null){

			if(head.occupied == true){
				if(head.piece.color == color && head.piece instanceof King) {
					return head;
				}
			}

			head = head.next;
		}
		return null;

	}

	/**
	 * Retrieves the Node according to the specified X Y coordinates
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @return Node that matches X Y coordinate, No match returns a NullNode
	 */
	public Node getNodeXY(int x, int y){
		//gets Node from XY cordinate
		Node head = a8;
		while(head.next != null){

			if(head.x == x && head.y == y){
				////System.out.println("head is " + head.piece);
				return head;
			}
			head = head.next;
		}
		return nullNode;
	}

	/**
	 * Retrieves the Piece according to the specified X Y coordinates
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @return Piece that matches X Y coordinate, No match returns null
	 */
	public Piece getPieceXY(int x, int y){
		//gets Piece from XY cordinates
		Node head = a8;
		while(head.next != null){

			if(head.x == x && head.y == y){
				if(head.piece != null){
					return head.piece;
				}
			}
			head = head.next;
		}
		return null;
	}

	/**
	 * Tests if a given move will cause the king to go into check
	 * @param start Node to be moved from
	 * @param end Node to be moved to
	 * @return True if move will cause a check
	 */
	//Checks if a given move will cause the king to go into check;
	public boolean testCheck(Node start, Node end){

		String color = start.piece.color;
//		//System.out.println("1");

		//moveTest(start, end);

		Node tempStart = new Node(start);
		Node tempEnd = new Node(end);

		end.occupied = true;
		end.text = start.text;
		end.piece = start.piece;
		//end.piece.x = end.x;
		//end.piece.y = end.y;

		start.piece = null;
		start.text = "null";
		start.occupied = false;


		//System.out.println("HELLOOOOO");
		//System.out.println(getNodeXY(5,6).text +" " + getNodeXY(4,7).text);

		if(findCheck(color) != null){
//			//System.out.println("2");

			end.occupied = tempEnd.occupied;
			end.text = tempEnd.text;
			end.piece = tempEnd.piece;
			//end.piece.x = end.x;
			//end.piece.y = end.y;

			start.piece = tempStart.piece;
			start.text = tempStart.text;
			start.occupied = true;


			//moveTest(end,start);
			//blackWhite(end);
			return true; //move causes a check.

		}else{
	//		//System.out.println("3");
			end.occupied = tempEnd.occupied;
			end.text = tempEnd.text;
			end.piece = tempEnd.piece;
			//end.piece.x = end.x;
			//end.piece.y = end.y;

			start.piece = tempStart.piece;
			start.text = tempStart.text;
			start.occupied = true;

//			moveTest(end,start);
			return false;

		}
	}

	/**
	 * Determines if the player's king is in check
	 * @param color Color of the player
	 * @return Node which is causing the king to go into check, returns null if there is no check
	 */
	public Node findCheck(String color){

		Node myKing = getKing(color);

		//find Knights
		int myX = myKing.x;
		int myY = myKing.y;

		int xAdd = 1;
		int yAdd = 1;



		for(int y = 0; y<2;y++){ //Goes through each quadrant to find knights
			xAdd = 1;
			for(int x=0;x<2;x++){

				Node node1 = getNodeXY(myX+(2*xAdd),myY+(1*yAdd));
				if(node1 != null){
					if(node1.piece != null){
						if(node1.piece.color != myKing.piece.color && node1.piece instanceof Knight)
							return node1;
					}
				}

				Node node2 = getNodeXY(myX+(1*xAdd),myY+(2*yAdd));
				if(node2 != null){
					if(node2.piece != null){
						if(node2.piece.color != myKing.piece.color && node2.piece instanceof Knight)
							return node2;
					}
				}

				xAdd = -1;
			}
			yAdd = -1;
		}



		//Checks for pawns

		//only need to check lower Y values for white
		if(myKing.piece.color == "White"){

			Node temp1 = getNodeXY(myKing.x -1,myKing.y -1);
			Node temp2 = getNodeXY(myKing.x +1,myKing.y -1);

			if(temp1.occupied){
				if(getNodeXY(myKing.x -1,myKing.y -1).piece.color != myKing.piece.color && (getNodeXY(myKing.x -1,myKing.y -1).piece instanceof Pawn)){
					return temp1;
				}
			}

			if(temp2.occupied){
				if(getNodeXY(myKing.x +1,myKing.y -1).piece.color != myKing.piece.color && (getNodeXY(myKing.x + 1,myKing.y -1).piece instanceof Pawn)){
					return temp2;
				}
			}
		}
		else{ //if it is black we only check higher values of Y for pawns

			Node temp1 = getNodeXY(myKing.x -1,myKing.y +1);
			Node temp2 = getNodeXY(myKing.x +1,myKing.y +1);

			if(temp1.occupied){
				if(getNodeXY(myKing.x -1,myKing.y + 1).piece.color != myKing.piece.color && (getNodeXY(myKing.x -1,myKing.y + 1).piece instanceof Pawn)){
					return temp1;
				}
			}

			if(temp2.occupied){
				if(getNodeXY(myKing.x +1,myKing.y + 1).piece.color != myKing.piece.color && (getNodeXY(myKing.x + 1,myKing.y + 1).piece instanceof Pawn)){
					return temp2;
				}
			}


		}

		//checks for nearby across attackers


		Node temp1 = getNodeXY(myX+0,myY+1);
		Node temp2 = getNodeXY(myX+0,myY-1);
		Node temp3 = getNodeXY(myX+1,myY+0);
		Node temp4 = getNodeXY(myX-1,myY+0);

		if(temp1.occupied && isAcrossAttacker(temp1, color)){
			return temp1;
		}
		if(temp2.occupied && isAcrossAttacker(temp2, color)){
			return temp2;
		}
		if(temp3.occupied && isAcrossAttacker(temp3, color)){
			return temp3;
		}
		if(temp4.occupied && isAcrossAttacker(temp4, color)){
			return temp4;
		}



		//check far reaching diag
		if(getFirstAcross(myKing) != null)
			return getFirstAcross(myKing);

		if(getFirstDiag(myKing) != null)
			return getFirstDiag(myKing);

//		//System.out.println("done");
		return null;

	}

	/**
	 * Resets En passant opportunities
	 * @param color Color of player
	 */
	public void resetEnPassant(String color){

		Node head = a8;
		while(head != null){
			if(head.occupied == true){
				if(head.piece.color != color && head.piece instanceof Pawn){
					((Pawn) head.piece).canEnpassant = false;
					((Pawn) head.piece).didEnpassant = false;
				}
			}

			head = head.next;
		}


	}

	/**
	 * Checks if Node contains piece of opposite color that can attack vertically or hhorizontally
	 * @param node Node in question
	 * @param color Color of player
	 * @return True if Node contains such piece
	 */
	public boolean isAcrossAttacker(Node node, String color){

		if(node.piece.color == color) //if it is on the same team
			return false;

		if(node.piece instanceof King || node.piece instanceof Queen || node.piece instanceof Rook ){
			return true;
		}
		return false;
	}

	/**
	 * Finds list of points between king and Node
	 * @param king Node that contains King
	 * @param attack Node that is causing King to be in check
	 * @return List of points between
	 */
	public ArrayList<String> getLineOfAttack(Node king, Node attack){

		ArrayList<String> points = new ArrayList<String>();

		//if(Math.abs(king.x-attack.x) > 0 && Math.abs(king.y-attack.y) > 0){ //if the piece is attacking diag

			int xDis = attack.x - king.x; // x distance
			int yDis = attack.y - king.y; // y distance (since slope 1 one we can use both);



			int xDir = (int) Math.copySign(1,xDis); //gets direction
			int yDir = (int) Math.copySign(1,yDis);


			if(xDis == 0 || yDis == 0){ //not diag
				xDis = xDis + yDis;
				if(xDis == 0)
					xDir = 0;
				if(yDis == 0)
					yDir = 0;
			}

			for(int z = 1; z < Math.abs(xDis); z++){

				int xTemp = king.x+(xDir*z);
				int yTemp = king.y+(yDir*z);
				points.add(xTemp + " " + yTemp);

			}

		//}

		//System.out.println(points.toString());

		return points;
//		gets list of quadnaites
//		checks every players peice if they can leagly move into those quardnaites to stop attack

	}

	/**
	 * Finds attacking Node vertically or horizontally that is in range of king
	 * @param king Node that contains King to check from
	 * @return returns Node that is attacking king, returns null if none is found
	 */
	public Node getFirstAcross(Node king){
		//Postive and Negative X dir (1+x 0+y) (-1+x, 0+y)
		//Postive and negative y dir (0+x, 1+y) (0+x,-1+y)

		int xAdd = 2;
		int yAdd = 0;

		int []xDir = {1,-1,0,0};
		int []yDir = {0,0,1,-1};

		for(int z = 0; z<xDir.length; z++){

			xAdd =  xDir[z]; //at zero to being, must me moved for initial check
			yAdd =  yDir[z];
			while(getNodeXY(king.x + xAdd,king.y + yAdd).occupied == false){
				xAdd = xAdd + xDir[z];
				yAdd = yAdd + yDir[z];
			}
			if(getNodeXY(king.x + xAdd,king.y + yAdd).piece.color != king.piece.color && (getNodeXY(king.x + xAdd,king.y + yAdd).piece instanceof Queen || getNodeXY(king.x + xAdd,king.y + yAdd).piece instanceof Rook ) ){
				//in check
				return getNodeXY(king.x + xAdd,king.y + yAdd);
			}



		}

		return null;

	}

	/**
	 * Finds attacking Node diagonally that is in range of king
	 * @param king Node that contains King to check from
	 * @return returns Node that is attacking king, returns null if none is found
	 */
	public Node getFirstDiag(Node king){

		//
		//1 1 , -1 1 , 1 -1 , -1 -1

		int [] xDir = {1,-1,1,-1};
		int [] yDir = {1,1,-1,-1};

		int xAdd = 0;
		int yAdd = 0;

		for(int z = 0; z<xDir.length; z++){ //Goes through each quadrant to bishops and queens

			xAdd =  xDir[z]; //at zero to being, must me moved for initial check
			yAdd =  yDir[z];
			while(getNodeXY(king.x + xAdd,king.y + yAdd).occupied == false){
				xAdd = xAdd + xDir[z];
				yAdd = yAdd + yDir[z];
			}

			if(xAdd == xDir[z] && yAdd == yDir[z]){
				if(getNodeXY(king.x + xAdd,king.y + yAdd).piece.color != king.piece.color && (getNodeXY(king.x + xAdd,king.y + yAdd).piece instanceof King)){
					return getNodeXY(king.x + xAdd,king.y + yAdd);
				}
			}

			if(getNodeXY(king.x + xAdd,king.y + yAdd).piece.color != king.piece.color && (getNodeXY(king.x + xAdd,king.y + yAdd).piece instanceof Queen || getNodeXY(king.x + xAdd,king.y + yAdd).piece instanceof Bishop ) ){
				//in check
				return getNodeXY(king.x + xAdd,king.y + yAdd);
			}


		}
		return  null;

	}

	/**
	 * Determines if the player is in checkmate
	 * @param color Color of player
	 * @return True if player is in checkmate
	 */
	public boolean isCheckMate(String color){



		Node king = getKing(color);
		Node attackNode = findCheck(color);


		if(attackNode == null) {
			return false;
		}

		//System.out.println("THIS IS ATTACKING " + attackNode.text);

		int [] xArPoints = {0,1,1,1,0,-1,-1,-1};
		int [] yArPoints = {1,1,0,-1,-1,-1,0,1};

		//try moving the king in any direction and check after each move
		for(int z = 0; z < xArPoints.length; z++){
			Node temper = getNodeXY(king.x+xArPoints[z],king.y+yArPoints[z]);
			////System.out.println(temper.text);
			if(moveCheck(king,temper)){ //if the move is legal
				//if the move result in the king not being in check
					//System.out.println("King can move " + (king.x+xArPoints[z]) + " " + (king.y+yArPoints[z]));
					return false; //return false, not checkmate

			}
			else{
				////System.out.println("invalid move");
			}
		}

		//Checks if any piece can take attacker legally.
		Node head = a8;
		while(head != null){
			if(head.occupied == true){
				if(head.piece.color == king.piece.color){
					if(moveCheck(head,attackNode)){
						return false;
					}
				}
			}

			head = head.next;
		}


		//Gets points to move to, if its not a Knight
		if(!(attackNode.piece instanceof Knight)){

			int x;
			int y;

			ArrayList<String> points = getLineOfAttack(king,attackNode);
			Pattern p = Pattern.compile("\\d+");
			Matcher m;
			for(String point : points){
				//System.out.println(point);
				m = p.matcher(point);
				m.find();
				x = Integer.valueOf(m.group());
				m.find();
				y = Integer.valueOf(m.group());
				//System.out.println("THE X " + x + " The y " + y);

				//Checks if any piece move into the spots
				head = a8;
				while(head != null){
					if(head.occupied == true){
						if(head.piece.color == king.piece.color && !(head.piece instanceof King)){
							//System.out.println(head.piece + " attempting" + head.x + head.y + head.piece.x + head.piece.y);
							if(head.piece.moveCheck(x,y)){
								//System.out.println(head.piece + " can move into " + " spot " + x + " " + y);
								return false;
							}
						}
					}

					head = head.next;
				}


			}


		}


		//System.out.println("Attack Piece " + attackNode.piece);




		//System.out.println("IN CHECKMATE");
		return true;
	}

	/**
	 * Determines if the player is in stalemate
	 * @param color Color of player
	 * @return True if player is in stalemate
	 */
	//Stalemate is when there are no legal moves that results in check
	public boolean isStalemate(String color){

		Node head = a8;

		while(head.next!=null) {
			if(head.occupied == true){
				if(head.piece.color == color){
					//try moving the piece in any direction, will it cause a check
					boolean m1 = moveCheck(head,getNodeXY(head.x+0,head.y+1)); //(0,1)
					boolean m2 = moveCheck(head,getNodeXY(head.x+1,head.y+1)); //(1,1)
					boolean m3 = moveCheck(head,getNodeXY(head.x+1,head.y+0)); //(1,0)
					boolean m4 = moveCheck(head,getNodeXY(head.x+1,head.y-1)); //(1,-1)
					boolean m5 = moveCheck(head,getNodeXY(head.x+0,head.y-1)); //(0,-1)
					boolean m6 = moveCheck(head,getNodeXY(head.x-1,head.y-1)); //(-1,-1)
					boolean m7 = moveCheck(head,getNodeXY(head.x-1,head.y+0)); //(-1,0)
					boolean m8 = moveCheck(head,getNodeXY(head.x-1,head.y-1)); //(-1,1)


					if(m1 || m2 || m3 || m4 || m5 || m6 || m7 || m8){
						return false;
					}
				}
			}

			head=head.next;
		}

		return true;


	}

	/**
	 * Testing function to customize board for specific scenarios
	 */
	//Testing Function for Stalemates
	public void testingFunction(){

		Node head = a8;

		while(head.next != null){
			if(head.occupied == true){
				head.piece = null;
				head.occupied = false;
				head.text = "   ";

			}
			head = head.next;
		}
		e5.piece = new King(4,3,"White",true);
		h1.piece = new Rook(7,7,"White",true);

		h5.piece = new King(7,3,"Black",true);

		e5.occupied = true;
		e5.text = "wK ";

		h1.occupied = true;
		h1.text = "wR ";

		h5.occupied = true;
		h5.text = "bK ";

	}

}
