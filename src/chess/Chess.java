package chess;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import pieces.*;

/** The Chess class manages the Chess game between players
 * @author Eric Martz
 * @author Minh Huynh
 */
public class Chess {

	/**
	 * Board used throughout the game
	 */
    public static Board board = new Board();
    private static String src, dest;
	private static Board.Node start, end;
	private static boolean moved;

	/**
	 * Initializes moves of the game from inputs
	 * @param input Input string of moves
	 */
	public static void setUp(char[] input) {
    	src=input[0]+""+input[1];  dest=input[3]+""+input[4]; 
		start = Board.stringNode(src, Board.a8); end = Board.stringNode(dest, Board.a8);
    }

	/**
	 * Checks for castling conditions
	 * @param a Node to move from
	 * @param b Node to move to
	 * @return True if castling conditions are met
	 */
    public static boolean kingRook(Board.Node a, Board.Node b) {
		if(a.text.contains("wK") && a.piece.moved == false && a.safe == true) {//king, hasn't moved, safe
			if(b.node.contains("c1")) { //castling queen side
				if(Board.stringNode("a1", Board.a8).piece.moved == true || 
						!board.castle(Board.stringNode("b1", Board.a8), Board.stringNode("e1", Board.a8))) {
					return false;  // rook moved or unsafe nodes or obstruction
				} 
				else {
					board.kingMove(Board.e1, Board.c1);
					board.rookMove(Board.a1, Board.d1);
					moved = true;
					return true;
				}
			}
			else if(b.node.contains("g1")){ //castling king side
				if(Board.stringNode("h1", Board.a8).piece.moved == true ||
						!board.castle(Board.stringNode("f1", Board.a8), Board.stringNode("g1", Board.a8))) {
					return false;
				}
				else {
					board.kingMove(Board.e1, Board.g1);
					board.rookMove(Board.h1, Board.f1);
					moved = true;
					return true;
				}
			}
		}
		else if(a.text.contains("bK") && a.piece.moved == false && a.safe == true) {
			if(b.node.contains("c8")) {
				if(Board.stringNode("a8", Board.a8).piece.moved == true || 
						!board.castle(Board.stringNode("b8", Board.a8), Board.stringNode("e8", Board.a8))) {
					return false;
				}
				else {
					board.kingMove(Board.e8, Board.c8);
					board.rookMove(Board.a8, Board.d8);
					moved = true;
					return true;
				}
			}
			else if(b.node.contains("g8")){
				if(Board.stringNode("h8", Board.a8).piece.moved == true ||
						!board.castle(Board.stringNode("f8", Board.a8), Board.stringNode("g8", Board.a8))) {
					return false;
				}
				else {
					board.kingMove(Board.e8, Board.g8);
					board.rookMove(Board.h8, Board.f8);
					moved = true;
					return true;
				}
			}
		}
		return true;
    }

	/**
	 * Parses player move for promotion details
	 * @param a Piece to promote pawn into
	 * @param b Node of pawn
	 * @param color Color of player
	 */
	public static void promotion(char[] a, Board.Node b, String color) {
    	if(color.contains("White")) {
	    	if((b.text.contains("wp")) && b.node.contains("8")) { //promotion
				if(a.length == 7) {
					if(a[6] == 'N') {
						b.text = "wK";
						Knight promoted = new Knight(b.x,b.y, b.piece.color, true);
						b.piece = promoted;
					}
					else if (a[6]=='R') {
						b.text = "wR";
						Rook promoted = new Rook(b.x,b.y, b.piece.color, true);
						b.piece = promoted;
					}
					else if (a[6]=='B') {
						b.text = "wB";
						Bishop promoted = new Bishop(b.x,b.y, b.piece.color, true);
						b.piece = promoted;
					}
				}
				else {
					b.text = "wQ";
					Queen promoted = new Queen(b.x,b.y, b.piece.color, true);
					b.piece = promoted;
				}
			}
    	}
    	else if (color.contains("Black")){
    		if((b.text.contains("bp")) && b.node.contains("1")) {
				if(a.length == 7) {
					if(a[6]=='N') {
						b.text = "bK";
						Knight promoted = new Knight(b.x,b.y, b.piece.color, true);
						b.piece = promoted;
					}
					else if (a[6]=='R') {
						b.text = "bR";
						Rook promoted = new Rook(b.x,b.y, b.piece.color, true);
						b.piece = promoted;
						}
					else if (a[6]=='B') {
						b.text = "bB";
						Bishop promoted = new Bishop(b.x,b.y, b.piece.color, true);
						b.piece = promoted;
					}
				}
				else {
					b.text = "bQ";
					Queen promoted = new Queen(b.x,b.y, b.piece.color, true);
					b.piece = promoted;
				}
			}
    	}
    }

	/**
	 * Main method of Chess, Runs chess game
	 * @param args Standard main string args
	 */
	public static void main(String args[]) { //remove ioexception later
		Scanner player = new Scanner(System.in);
    	//File file = new File(System.getProperty("user.dir") + "/src/chess/test.txt"); //delete
		//BufferedReader player = new BufferedReader(new FileReader(file)); //delete
		//String temp; //delete
		char[] input; int count = 0; boolean goodMove;
		boolean drawWhite = false, drawBlack = false;
		String temp;
		System.out.println("Lets play Chess! White goes first!");

		//board.testingFunction();
		board.printBoard(Board.a8);
		System.out.print("White's move: ");
		while(player.hasNext()) {
	//	while((temp = player.readLine())!=null) {
			moved = false; 
			temp = player.nextLine();
			input = temp.toCharArray(); 
			if(temp.contains("draw?") && count%2==0) {
				drawWhite = true;
				setUp(Arrays.copyOfRange(input, 0, 5));	
			}
			else if(temp.contains("draw?") && count%2!=0) {
				drawBlack = true;
				setUp(Arrays.copyOfRange(input, 0, 5));	
			}
			else if(temp.contains("draw")){
				if(count%2==0 && drawBlack == true) {
					System.out.println("draw");
					System.exit(1);
				}
				else if(count%2!=0 && drawWhite == true) {
					System.out.println("draw");
					System.exit(1);
				}
			}
			else {
				setUp(input);
			}
			//input = temp.toCharArray(); setUp(input);
			if(count%2==0) { //White's turn
				if(temp.contains("resign")) {
					System.out.println("Black wins");
					System.exit(1);
				}
				if(board.findCheck("White") != null){
					System.out.println();
					System.out.println("Check");
				}
			
				goodMove = false;
				while(goodMove == false){ //keeps going until a proper move is made
					if(start.occupied == false){ //Condition 1: Piece chosen
						System.out.println();
						System.out.println("Illegal move, try again");
						System.out.println();
						System.out.print("White's move: ");
						temp = player.nextLine();
						input = temp.toCharArray();	
						if(temp.contains("draw?")) {
							drawWhite = true;
							setUp(Arrays.copyOfRange(input, 0, 5));
						}
						else if(temp.contains("draw") && drawBlack == true) {
							System.out.println("draw");
							System.exit(1);
						}
						else {
							drawWhite = false;
							setUp(input);
						}
					}
					else if(start.piece.color == "White") {//Condition 2: Piece is Correct Color
						goodMove = board.moveCheck(start, end);
						if(goodMove == true) {
							goodMove = kingRook(start, end);
						}
						while(goodMove == false){
							System.out.println();
							System.out.println("Illegal move, try again");
							System.out.println();
							System.out.print("White's move: ");
							temp=player.nextLine();
							input = temp.toCharArray(); 
							if(temp.contains("draw?")) {
								drawWhite = true;
								setUp(Arrays.copyOfRange(input, 0, 5));
							}
							else if(temp.contains("draw") && drawBlack == true) {
								System.out.println("draw");
								System.exit(1);
							}
							else {
								drawWhite = false;
								setUp(input);
							}
							goodMove = board.moveCheck(start, end);
							if(goodMove == true) {
								goodMove = kingRook(start, end);
							}
							//castle
						} 
						if(moved == false) { //no castling
							board.move(start, end);
							moved = true;
						}
						promotion(input, end, "White");
						System.out.println();
						board.printBoard(Board.a8);
						System.out.print("Black's move: ");
						
						count ++;
					}
					else {
						System.out.println();
						System.out.println("Illegal move, try again");
						System.out.println();
						System.out.print("White's move: ");
						temp = player.nextLine();
						input = temp.toCharArray(); 	
						if(temp.contains("draw?")) {
							drawWhite = true;
							setUp(Arrays.copyOfRange(input, 0, 5));
						}
						else if(temp.contains("draw") && drawBlack == true) {
							System.out.println();
							System.out.println("draw");
							System.exit(1);
						}
						else {
							setUp(input);
						}
					}
				}

				board.resetEnPassant("White");

				if(board.isCheckMate("Black")){
					System.out.println();
					System.out.println("Checkmate");
					System.out.println();
					System.out.println("White wins");
					System.exit(1);
				}
				if(board.findCheck("Black") == null){
					if(board.isStalemate("Black")){
						System.out.println();
						System.out.println("Stalemate");
						System.exit(1);
					}
				}

			}
		
			else {	//Black's turn

				if(temp.contains("resign")) {
					System.out.println();
					System.out.println("Black wins");
					System.exit(1);
				}
				
				if(board.findCheck("Black") != null){
					System.out.println();
					System.out.println("Check");
				}
				else{
					if(board.isStalemate("Black")){
						System.out.println();
						System.out.println("Stalemate");
						System.exit(1);
					}
				}

				goodMove = false;
				while(goodMove == false) { //keeps going until a proper move is made
					if (start.occupied == false) {
						System.out.println();
						System.out.println("Illegal move, try again");
						System.out.println();
						System.out.print("Black's move: ");
						System.out.println("");
						temp = player.nextLine();
						input = temp.toCharArray(); 
						if(temp.contains("draw?")) {
							drawBlack = true;
							setUp(Arrays.copyOfRange(input, 0, 5));
						}
						else if(temp.contains("draw") && drawWhite == true) {
							System.out.println();
							System.out.println("draw");
							System.exit(1);
						}
						else {
							drawBlack = false;
							setUp(input);
						}
					}
					else if(start.piece.color == "Black") {
						//System.out.println("NEW TEXT" + end.text);
						goodMove = board.moveCheck(start, end);
						if(goodMove == true) {
							goodMove = kingRook(start, end);
						}
						while(goodMove == false){
							System.out.println();
							System.out.println("Illegal move, try again");
							System.out.println();
							System.out.print("Black's move: ");
							temp = player.nextLine();
							input = temp.toCharArray(); 	
							if(temp.contains("draw?")) {
								drawBlack = true;
								setUp(Arrays.copyOfRange(input, 0, 5));
							}
							else if(temp.contains("draw") && drawWhite == true) {
								System.out.println("draw");
								System.exit(1);
							}
							else {
								drawBlack = false;
								setUp(input);
							}
							goodMove = board.moveCheck(start, end);
							if(goodMove == true) {
								goodMove = kingRook(start, end);
							}
						}
						if(moved == false) { //no castling
							board.move(start, end);
							moved = true;
						}
						promotion(input, end, "Black");
						System.out.println();
						board.printBoard(Board.a8);
						System.out.print("White's move: ");
						count ++;
					}
					else {
						System.out.println();
						System.out.println("Illegal move, try again");
						System.out.println();
						System.out.print("Black's move: ");
						temp = player.nextLine();
						input = temp.toCharArray(); 
						if(temp.contains("draw?")) {
							drawBlack = true;
							setUp(Arrays.copyOfRange(input, 0, 5));
						}
						else if(temp.contains("draw") && drawWhite == true) {
							System.out.println("draw");
							System.exit(1);
						}
						else {
							drawBlack = false;
							setUp(input);
						}
					}
				}

				board.resetEnPassant("Black");

				if(board.isCheckMate("White")){
					System.out.println();
					System.out.println("Checkmate");
					System.out.println();
					System.out.println("Black wins");
					System.exit(1);
				}
				if(board.findCheck("White") == null){
					if(board.isStalemate("White")){
						System.out.println();
						System.out.println("Stalemate");
						System.exit(1);
					}
				}

			}
		}
		player.close();
	}
}
