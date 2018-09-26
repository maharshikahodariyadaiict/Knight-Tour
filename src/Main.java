import java.util.*;



public class Main {
	private static int value=1;
	

	private int SIZE;
	
	private int[][] board;
	
	
	public int[][] test(int size,int x,int y){
		SIZE = size;
		board = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; i < SIZE; i++){
				board[i][j] = 0;
			}
		}
		
		nextMove(--x,--y);
		value = 1;
		
		return board;
	}
	
	public boolean nextMove(int row, int column){
		
		board[row][column] = value;
		value++;
		
		ArrayList<Location> nextMovePossibilities = possibleMoves(row,column);
		
		if(nextMovePossibilities.size() == 0){
			displayBoard();
			System.out.println("FINISH....");
		}
		else{
			Location nextMove = findBest(nextMovePossibilities);	
			int nextRow = nextMove.getRow();
			int nextColumn = nextMove.getColumn();
		
			displayBoard();
			System.out.println();
			System.out.println();
		
			return nextMove(nextRow,nextColumn);
		}
		
		return true;
	}
	
	public ArrayList<Location> possibleMoves(int row, int column){
		
		ArrayList<Location> validMoves = new ArrayList<Location>();
		
		if( isValidMove(new Location(row+2, column +1)) ){
			validMoves.add(new Location(row+2, column +1));
		}
		
		if( isValidMove(new Location(row+2, column-1)) ){
			validMoves.add(new Location(row+2, column-1));
		}	
		
		if( isValidMove(new Location(row-2, column +1)) ){
			validMoves.add(new Location(row-2, column +1));
		}	
		
		if( isValidMove(new Location(row-2, column -1)) ){
			validMoves.add(new Location(row-2, column -1));
		}
		
		if( isValidMove(new Location(row+1, column +2)) ){
			validMoves.add(new Location(row+1, column +2));
		}	
			
		if( isValidMove(new Location(row+1, column -2)) ){
			validMoves.add(new Location(row+1, column -2));
		}	
		
		if( isValidMove(new Location(row-1, column +2)) ){
			validMoves.add(new Location(row-1, column +2));
		}	
		
		if( isValidMove(new Location(row-1, column -2)) ){
			validMoves.add(new Location(row-1, column-2));
		}	
		
		return validMoves;
	}
	
	public Location findBest(ArrayList<Location> locations){
		Location bestMove = null;
		
		int bestMoveScore = 64;	
		
		for(Location location : locations){
			
			ArrayList<Location> movesFromThisPos = possibleMoves(location.getRow(), location.getColumn());
			
			if(movesFromThisPos.size() < bestMoveScore){
				bestMove = new Location(location.getRow(), location.getColumn());
				bestMoveScore = movesFromThisPos.size();
			}
		}
		
		return bestMove;
	}
	
	public boolean isValidMove(Location loc){
		
		if(loc.getRow() >= SIZE || loc.getColumn() >= SIZE){
			return false;
		}
		
		if(loc.getRow() < 0 || loc.getColumn() < 0){
			return false;
		}
		
		if(board[loc.getRow()][loc.getColumn()] !=0){
			return false;
		}
		
		return true;
	}
	
	public void displayBoard(){
		
		for(int i = 0; i < SIZE; i++){	
			for(int j = 0; j < SIZE; j++){
				System.out.print(board[i][j]+" ");
			}
			
			System.out.println();
		}
	}
}